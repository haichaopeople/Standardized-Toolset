package morn.library.boot.log;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;
import morn.library.bean.BeanCache;
import morn.library.bean.support.Tags;
import morn.library.task.TaskExecutors;
import morn.library.util.BeanFunctionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import morn.library.log.OperateAction;
import morn.library.log.OperateArguments;
import morn.library.log.OperateGroup;
import morn.library.log.OperateMeta;
import morn.library.log.OperateMode;
import morn.library.log.Operation;
import morn.library.log.OperationAdapter;
import morn.library.log.OperationContext;
import morn.library.log.OperationConverter;
import morn.library.log.OperationProcessor;

/**
 * 操作切面
 */
@Aspect
@Slf4j
public class OperateAspect {

    private final BeanCache beanCache;

    /**
     * 操作日志配置项
     */
    private final OperateProperties properties;

    public OperateAspect(BeanCache beanCache, OperateProperties properties) {
        this.beanCache = beanCache;
        this.properties = properties;
    }

    /**
     * 日志切面
     */
    @Around("@annotation(morn.library.log.OperateAction)")
    public Object aroundOperate(ProceedingJoinPoint point) throws Throwable {
        OperateMeta meta = resolveOperatePoint(point);
        /* 起始时间 */
        long startTime = System.currentTimeMillis();
        try {
            // 执行目标方法，并记录执行结果
            Object returned = point.proceed();
            meta.setMethodReturned(returned);
            meta.setSuccess(true);
            return returned;
        } catch (Throwable throwable) {
            // 执行失败，记录异常信息
            meta.setSuccess(false);
            meta.setThrowable(throwable);
            throw throwable;
        } finally {
            /* 结束时间 */
            long endTime = System.currentTimeMillis();
            /* 耗时间 */
            long duration = endTime - startTime;
            // 读取操作日志参数
            meta.setStartTime(startTime);
            meta.setEndTime(endTime);
            meta.setDuration(duration);
            // 获取实时参数
            meta.setCodeArgs(OperateArguments.getAll().toArray());
            OperateArguments.clear();
            OperationContext context = buildContext(meta);
            if (properties.isAsync()) {
                extractOperationAsync(context);
            } else {
                extractOperation(context);
            }
        }
    }

    /**
     * 解析操作日志切点
     *
     * @param point 切点
     * @return 操作日志构建器
     */
    private OperateMeta resolveOperatePoint(ProceedingJoinPoint point) {
        Class<?> targetClass = point.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = targetClass.getName();
        String methodName = methodSignature.getName();
        log.debug("Operate|Method:{}#{}", className, methodName);
        // 获取操作组注解
        OperateGroup operateGroup = AnnotationUtils.findAnnotation(targetClass, OperateGroup.class);
        // 获取操作行为注解
        OperateAction operateAction = AnnotationUtils.findAnnotation(methodSignature.getMethod(), OperateAction.class);
        Assert.notNull(operateAction, "Operate|无法获取[@OperateAction]:" + targetClass.getSimpleName());
        // 构建操作日志元数据
        OperateMeta meta = new OperateMeta();
        meta.setSource(point);
        meta.setMethodArgs(point.getArgs());
        if (Objects.nonNull(operateGroup)) {
            meta.setGroupName(operateGroup.value());
            meta.setGroupArgs(operateGroup.args());
        }
        meta.setActionName(operateAction.value());
        meta.setActionArgs(operateAction.args());
        meta.setExcludeNames(operateAction.excludeNames());
        meta.setExcepts(operateAction.excepts());
        return meta;
    }

    /**
     * 构建操作日志上下文
     *
     * @param operateMeta 操作日志元数据
     * @return 操作日志上下文
     */
    private OperationContext buildContext(OperateMeta operateMeta) {
        // 将操作日志元数据，转换为操作日志实例
        String[] tags = Tags.from(OperateMode.class, properties.getMode()).toArray();
        Operation operation = BeanFunctionUtils
                .convert(OperationConverter.class, operateMeta, tags);
        // 对操作上下文进行场景适配
        OperationContext context = new OperationContext(operateMeta, operation);
        context = BeanFunctionUtils.adaptions(OperationAdapter.class, context, tags);
        return context;
    }

    /**
     * 提取操作信息-异步
     */
    private void extractOperationAsync(OperationContext context) {
        CompletableFuture<?> future = TaskExecutors
                .submit(() -> extractOperation(context));
        future.exceptionally(throwable -> {
            log.warn(
                    "Operate|Error|Async:" + throwable.getClass().getName() + ":" + throwable.getMessage(),
                    throwable);
            return null;
        });
    }

    /**
     * 提取操作信息
     */
    private void extractOperation(OperationContext context) {
        // 处理操作日志
        List<OperationProcessor> processors = beanCache.tagBeans(OperationProcessor.class);
        Assert.notEmpty(processors, "Operate|Error|请注册操作日志处理器:" + OperationProcessor.class.getName());
        for (OperationProcessor processor : processors) {
            processor.handle(context.getMeta(), context.getOperation());
        }
    }
}

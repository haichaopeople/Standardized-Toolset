package morn.library.boot.notify;

import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.support.BeanCaches;
import morn.library.constant.ApplicationConstants;
import morn.library.core.CriteriaMap;
import morn.library.notify.AnnotationNotifyMetaAdapter;
import morn.library.notify.NotifyDispatcher;
import morn.library.notify.NotifyMeta;
import morn.library.notify.annotation.Notify;
import morn.library.notify.annotation.NotifyReceiver;
import morn.library.notify.support.NotifyArguments;
import morn.library.template.TemplateMeta;
import morn.library.template.annotation.Template;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

/**
 * 通知切面
 */
@Slf4j
@Aspect
public class NotifyAspect {

  private final NotifyDispatcher notifyDispatcher;

  public NotifyAspect(NotifyDispatcher notifyDispatcher) {
    this.notifyDispatcher = notifyDispatcher;
  }

  /**
   * 创建切点
   */
  @Pointcut("@annotation(morn.library.notify.annotation.Notify)")
  public void pointcut() {
  }

  /**
   * 系统通知前置触发器
   *
   * @param point 切点
   */
  @Before("pointcut()")
  public void beforeTrigger(JoinPoint point) {
    Class<?> targetClass = point.getTarget().getClass();
    MethodSignature methodSignature = (MethodSignature) point.getSignature();
    // 获取通知注解
    Notify notify = AnnotationUtils.findAnnotation(methodSignature.getMethod(), Notify.class);
    Assert.notNull(notify, "无法获取通知注解：" + targetClass.getSimpleName());
    CriteriaMap builtinArgs = NotifyArguments.getBuiltinArgs();
    builtinArgs.put(ApplicationConstants.Notifies.BUILTIN_NOTIFY_ANNOTATION, notify);
    String notifyKey = notify.type() + "." + notify.value();
    builtinArgs.put(ApplicationConstants.Notifies.BUILTIN_NOTIFY_KEY, notifyKey);
  }

  /**
   * 触发系统通知
   * <p>必须配置通知注解{@link Notify}和接收人注解{@link NotifyReceiver}</p>
   *
   * @param point 切点
   */
  @AfterReturning("pointcut()")
  public void triggering(JoinPoint point) {
    Class<?> targetClass = point.getTarget().getClass();
    MethodSignature methodSignature = (MethodSignature) point.getSignature();
    // 获取通知注解
    Notify notify = AnnotationUtils.findAnnotation(methodSignature.getMethod(), Notify.class);
    Assert.notNull(notify, "无法获取通知注解：" + targetClass.getSimpleName());
    // 获取接收人注解
    NotifyReceiver receiver = AnnotationUtils
        .findAnnotation(methodSignature.getMethod(), NotifyReceiver.class);

    // 获取模板注解
    Template template = AnnotationUtils.findAnnotation(methodSignature.getMethod(), Template.class);
    boolean hasTemplateAnnotation = Objects.nonNull(template);
    // 初始化通知元数据
    NotifyMeta notifyMeta = new NotifyMeta();
    TemplateMeta templateMeta = new TemplateMeta();
    // 从元数据适配器中完善元数据
    List<AnnotationNotifyMetaAdapter> adapters = BeanCaches
        .beans(AnnotationNotifyMetaAdapter.class);
    for (AnnotationNotifyMetaAdapter adapter : adapters) {
      adapter.setNotify(notify);
      adapter.setReceiver(receiver);
      adapter.setNotifyMeta(notifyMeta);
      notifyMeta = adapter.adaptionNotifyMeta();
      if (hasTemplateAnnotation) {
        adapter.setTemplate(template);
        adapter.setTemplateMeta(templateMeta);
        templateMeta = adapter.adaptionTemplateMeta();
      }
    }
    // 分发通知
    if (hasTemplateAnnotation) {
      notifyDispatcher.setTemplateMeta(templateMeta);
    }
    notifyDispatcher.handle(notifyMeta);
    clear();
  }

  /**
   * 系统通知触发后
   */
  @AfterThrowing("pointcut()")
  public void triggered() {
    clear();
  }

  /**
   * 清除冗余数据
   */
  private void clear() {
    // 清空线程变量
    NotifyArguments.clear();
    // 同一线城多次执行通知，会造成参数存留
    notifyDispatcher.setTemplateMeta(null);
  }
}

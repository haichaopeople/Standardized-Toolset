package morn.library.boot.exception;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.BeanCache;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ExceptionInterpreter;
import morn.library.exception.ExceptionInterpreterService;

/**
 * 默认异常处理器
 */
@AllArgsConstructor
@Slf4j
public class DefaultExceptionInterpreterServiceImpl implements ExceptionInterpreterService {

  /**
   * 异常解释器缓存
   */
  private final BeanCache beanCache;

  @Override
  public <T extends Exception> ApplicationMessage interpret(T exception) {
    // 从缓存中获取异常解释器
    ExceptionInterpreter exceptionInterpreter = beanCache
        .targetBean(ExceptionInterpreter.class, exception.getClass());
    if (Objects.isNull(exceptionInterpreter)) {
      log.debug("异常处理失败：尚未发现处理{}的异常解释器", exception.getClass().getSimpleName());
      return null;
    }
    return exceptionInterpreter.interpret(exception);
  }
}

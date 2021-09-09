package morn.library.boot.exception.interpreter;

import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationException;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ExceptionInterpreter;

/**
 * 应用异常解释器
 *
 * <p>处理Morn相关异常
 */
@Target(ApplicationException.class)
public class ApplicationExceptionInterpreter implements ExceptionInterpreter {

  @Override
  public ApplicationMessage interpret(Throwable throwable, Object... args) {
    ApplicationException applicationException = (ApplicationException) throwable;
    return applicationException.getApplicationMessage();
  }
}

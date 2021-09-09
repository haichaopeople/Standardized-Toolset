package morn.library.boot.exception.interpreter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.exception.ExceptionInterpreter;

/**
 * Shiro异常解释器
 *
 * <p>处理Shiro相关异常
 *
 */
@Slf4j
@Target(AuthenticationException.class)
public class ShiroExceptionInterpreter implements ExceptionInterpreter {

  @Override
  public ApplicationMessage interpret(Throwable throwable, Object... args) {
    AuthenticationException exception = (AuthenticationException) throwable;
    if (exception instanceof UnknownAccountException) {
      log.debug("登录失败，账号不存在。");
      return ApplicationMessages.translateMessage(InterpreterConstants.Errors.LOGIN_UNKNOWN_ACCOUNT);
    }
    if (exception instanceof IncorrectCredentialsException) {
      log.debug("登录失败，密码错误。");
      return ApplicationMessages.translateMessage(InterpreterConstants.Errors.LOGIN_PASSWORD_INCORRECT);
    }
    if (exception instanceof LockedAccountException) {
      log.debug("登录失败，账号锁定。");
      return ApplicationMessages.translateMessage(InterpreterConstants.Errors.LOGIN_ACCOUNT_LOCKED);
    }
    return ApplicationMessages.translateMessage(InterpreterConstants.Errors.LOGIN_FAILURE);
  }
}

package morn.library.boot.exception.interpreter;

import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.exception.ExceptionInterpreter;

/**
 * 数据校验异常解释器
 *
 * <p>处理Spring validation 相关异常
 */
@Target(BindException.class)
public class ValidateExceptionInterpreter implements ExceptionInterpreter {

  @Override
  public ApplicationMessage interpret(Throwable throwable, Object... args) {
    BindException bindException = (BindException) throwable;
    // 生成错误消息文本
    List<FieldError> errors = bindException.getFieldErrors();
    String message = ExceptionInterpreterUtils.generateMessages(errors);
    // 构建异常消息
    return ApplicationMessages.buildMessage("validate", message, null);
  }
}

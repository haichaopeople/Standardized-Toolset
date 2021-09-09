package morn.library.boot.exception.interpreter;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.exception.ExceptionInterpreter;
import morn.library.util.GenericUtils;

/**
 * 校验异常解释器
 */
@Slf4j
@Target(MethodArgumentNotValidException.class)
public class MethodValidateExceptionInterpreter implements ExceptionInterpreter {

  @Override
  public ApplicationMessage interpret(Throwable throwable, Object... args) {
    MethodArgumentNotValidException exception = GenericUtils.castFrom(throwable);
    // 生成错误消息文本
    List<FieldError> errors = exception.getBindingResult().getFieldErrors();
    String message = ExceptionInterpreterUtils.generateMessages(errors);
    // 构建异常消息
    return ApplicationMessages.buildMessage("validate", message, null);
  }
}

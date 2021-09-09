package morn.library.boot.exception.interpreter;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.exception.ExceptionInterpreter;
import morn.library.util.GenericUtils;

/**
 * 校验异常解释器
 */
@Slf4j
@Target(ConstraintViolationException.class)
public class ValidationExceptionInterpreter implements ExceptionInterpreter {

  @Override
  public ApplicationMessage interpret(Throwable throwable, Object... args) {
    ConstraintViolationException exception = GenericUtils.castFrom(throwable);
    List<String> messages = exception.getConstraintViolations().stream()
        .map(constraintViolation -> {
          String objectName = constraintViolation.getRootBean().getClass().getSimpleName(); // 对象名称
          String propertyName = constraintViolation.getPropertyPath().toString(); // 属性名称
          String message = constraintViolation.getMessage(); // 校验消息
          return String.format("%s.%s%s", objectName, propertyName, message);
        }).collect(Collectors.toList());
    return ApplicationMessages
        .buildMessage("validation.reject", StringUtils.collectionToCommaDelimitedString(messages),
            "");
  }
}

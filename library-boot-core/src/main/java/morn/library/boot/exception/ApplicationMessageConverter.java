package morn.library.boot.exception;

import morn.library.bean.annotation.Source;
import morn.library.bean.annotation.Target;
import morn.library.boot.rest.RestBuilders;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.rest.RestMessage;
import morn.library.rest.RestMessageConverter;

/**
 * 应用消息转换器
 */
@Source(RestMessage.class)
@Target(ApplicationMessage.class)
public class ApplicationMessageConverter implements RestMessageConverter<ApplicationMessage> {

  @Override
  public ApplicationMessage convert(RestMessage restMessage) {
    return ApplicationMessages.buildMessage(restMessage.getCode(), restMessage.getMessage(), null);
  }

  @Override
  public RestMessage revert(ApplicationMessage applicationMessage) {
    return RestBuilders.errorBuilder().code(applicationMessage.getCode())
        .message(applicationMessage.getMessage()).build();
  }
}

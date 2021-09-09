package morn.library.exception;

import morn.library.bean.annotation.Target;
import morn.library.translate.Transfer;
import morn.library.translate.TranslateConverter;

/**
 * 测试应用消息转换器
 */
//@Component
@Target(ApplicationMessage.class)
public class TestApplicationMessageConverter implements TranslateConverter<ApplicationMessage> {

  @Override
  public ApplicationMessage convert(Transfer transfer) {
    // 构建应用消息
    return ApplicationMessages
        .buildMessage(transfer.getName(), "This is message.", "This is solution.");
  }
}

package morn.library.boot.message.rocket.support;

import morn.library.bean.annotation.Tag;
import morn.library.boot.message.MessageResult;
import morn.library.boot.message.constant.MessageConstant;
import morn.library.boot.message.rocket.RocketConstants;
import morn.library.boot.message.rocket.RocketMessageResultConverter;
import morn.library.boot.message.support.SimpleMessageResult;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * Rocket消息结果转换器
 */
@Tag(RocketConstants.ROCKET)
public class SimpleRocketMessageResultConverter implements RocketMessageResultConverter {

  @Override
  public MessageResult convert(SendResult source) {
    SimpleMessageResult messageResult = new SimpleMessageResult();
    messageResult.setOriginalResult(source);
    switch (source.getSendStatus()) {
      case SEND_OK:
        messageResult.setStatus(MessageConstant.MessageResultStatus.SUCCESS);
        break;
      case SLAVE_NOT_AVAILABLE:
        messageResult.setStatus(MessageConstant.MessageResultStatus.UNKNOWN);
        break;
      default:
        messageResult.setStatus(MessageConstant.MessageResultStatus.FAILURE);
        break;
    }
    return messageResult;
  }
}

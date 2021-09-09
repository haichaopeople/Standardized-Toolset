package morn.library.boot.message.rocket.support;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.annotation.Objective;
import morn.library.boot.message.rocket.RocketMessagePayloadResolver;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.Assert;
import morn.library.boot.json.util.JsonParsers;

/**
 * 默认Rocket消息体解析器
 */
@Slf4j
@Objective
public class SimpleRocketMessagePayloadResolver implements RocketMessagePayloadResolver<Object> {

  private Type payloadType;

  @Override
  public Object convert(MessageExt source) {
    Assert.notNull(payloadType, "Message|Resolver failure:尚未获取消息体类型");
    String payloadString = new String(source.getBody(), StandardCharsets.UTF_8);
    return JsonParsers.convertObject(payloadString, payloadType);
  }

  @Override
  public void setPayloadType(Type type) {
    this.payloadType = type;
  }
}

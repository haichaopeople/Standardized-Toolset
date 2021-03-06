package morn.library.boot.message.rocket;

import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import morn.library.boot.message.BroadcastMessageHeaders;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;
import morn.library.boot.json.util.JsonParsers;
import morn.library.test.TestUser;

/**
 * Rocket消息处理单元测试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketMessageHandlerTest {

  @Autowired
  private RocketMessageHandler messageHandler;

  @Test
  public void handleMessage() {
    // 创建消息数据
    TestUser testUser = new TestUser();
    testUser.setUsername("timely-rain");
    testUser.setPassword("password");
    MessageExt messageExt = new MessageExt();
    messageExt.setTopic("userData");
    messageExt.putUserProperty(BroadcastMessageHeaders.TYPE, "add");
    messageExt.putUserProperty(CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
    String json = JsonParsers.convertStr(testUser);
    byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
    messageExt.setBody(bytes);
    try {
      messageHandler.handleMessage(messageExt);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      Assert.fail();
    }
  }
}
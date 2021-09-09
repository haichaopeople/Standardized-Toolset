package morn.library.boot.message;


import java.util.List;
import lombok.extern.slf4j.Slf4j;
import morn.library.boot.message.annotation.MessageTopic;
import morn.library.boot.message.annotation.MessageType;
import org.junit.Assert;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import morn.library.test.TestUser;

@Slf4j
@Component
@MessageTopic("userData")
public class TestUserMessageHandler {

  @MessageType("add")
  public void addUser(@Payload TestUser user, BroadcastMessageHeaders headers) {
    Assert.assertNotNull(headers);
    log.info("Message id {}", headers.getId());
    Assert.assertNotNull(user);
    log.info("Add user: {}", user.getUsername());
  }

  @MessageType("update")
  public void updateUser(@Payload List<TestUser> users, BroadcastMessageHeaders headers) {
    Assert.assertNotNull(headers);
    log.info("Message id {}", headers.getId());
  }
}

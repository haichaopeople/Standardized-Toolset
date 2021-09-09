package morn.library.boot.message.rocket;

import lombok.extern.slf4j.Slf4j;
import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.support.BroadcastMessageBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Rocket目标单元测试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketDestinationConverterTest {

  @Autowired
  private RocketDestinationConverter destinationConverter;

  @Test
  public void convert() {
    BroadcastMessage<Object> message = BroadcastMessageBuilder.withPayload(new Object())
        .setTopic("testTopic").build();
    String destination = destinationConverter.convert(message);
    Assert.assertEquals("testTopic", destination);
  }
}
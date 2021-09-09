package morn.library.boot.autoconfigure.message;

import java.util.List;

import morn.library.boot.message.BroadcastMessageHeaderResolver;
import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.boot.message.rocket.RocketDestinationConverter;
import morn.library.boot.message.rocket.RocketMessageHandler;
import morn.library.boot.message.rocket.RocketMessageHeaderResolver;
import morn.library.boot.message.rocket.RocketMessagePayloadResolver;
import morn.library.boot.message.rocket.RocketMessageResultConverter;
import morn.library.boot.message.rocket.RocketResolvingOperations;
import morn.library.boot.message.rocket.RocketSendingOperations;
import morn.library.boot.message.rocket.support.SimpleRocketDestinationConverter;
import morn.library.boot.message.rocket.support.SimpleRocketMessageHandler;
import morn.library.boot.message.rocket.support.SimpleRocketMessageHeaderResolver;
import morn.library.boot.message.rocket.support.SimpleRocketMessagePayloadResolver;
import morn.library.boot.message.rocket.support.SimpleRocketMessageResultConverter;
import morn.library.boot.message.rocket.support.SimpleRocketResolvingOperations;
import morn.library.boot.message.rocket.support.SimpleRocketSendingOperations;

/**
 * Rocket自动化配置
 */
@Configuration
@ConditionalOnClass({MQAdmin.class, RocketMessageHandler.class})
@ConditionalOnProperty(prefix = "rocketmq", value = "name-server", matchIfMissing = true)
public class RocketAutoConfiguration {

  /**
   * 注册发送目标转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public RocketDestinationConverter rocketDestinationConverter() {
    return new SimpleRocketDestinationConverter();
  }

  /**
   * 注册注解消息处理者
   */
  @Bean
  public RocketMessageHandler rocketMessageHandler() {
    return new SimpleRocketMessageHandler();
  }

  /**
   * 注册消息头解析器
   */
  @Bean
  @ConditionalOnMissingBean
  public RocketMessageHeaderResolver rocketMessageHeaderResolver() {
    return new SimpleRocketMessageHeaderResolver();
  }

  /**
   * 注册消息结果转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public RocketMessageResultConverter rocketMessageResultConverter() {
    return new SimpleRocketMessageResultConverter();
  }

  /**
   * 注册消息体解析器
   */
  @Bean
  @ConditionalOnMissingBean
  public RocketMessagePayloadResolver<Object> rocketMessagePayloadResolver() {
    return new SimpleRocketMessagePayloadResolver();
  }

  /**
   * 注册消息接收操作类
   */
  @Bean
  @ConditionalOnMissingBean
  public RocketResolvingOperations rocketResolvingOperations(RocketMessageHandler messageHandler,
      List<BroadcastMessageHeaderResolver<?>> headerResolvers) {
    return new SimpleRocketResolvingOperations(messageHandler, headerResolvers);
  }

  /**
   * 注册消息发送操作类
   */
  @Bean
  @ConditionalOnBean(RocketMQTemplate.class)
  @ConditionalOnMissingBean
  public RocketSendingOperations rocketSendingOperations(RocketMQTemplate template) {
    return new SimpleRocketSendingOperations(template);
  }
}

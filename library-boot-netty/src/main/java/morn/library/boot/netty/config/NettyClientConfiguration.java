package morn.library.boot.netty.config;

import io.netty.bootstrap.Bootstrap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.boot.netty.support.RemoteAddressClientProducer;

/**
 * Netty客户端自动化配置
 */
@Configuration
@ConditionalOnClass(Bootstrap.class)
@EnableConfigurationProperties(NettyClientProperties.class)
public class NettyClientConfiguration {

  /**
   * 注册远程连接客户端生产者
   */
  @Bean
  public RemoteAddressClientProducer remoteAddressClientProducer() {
    return new RemoteAddressClientProducer();
  }
}

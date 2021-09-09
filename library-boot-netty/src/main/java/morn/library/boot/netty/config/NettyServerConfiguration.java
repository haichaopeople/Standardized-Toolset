package morn.library.boot.netty.config;

import io.netty.bootstrap.ServerBootstrap;
import lombok.extern.slf4j.Slf4j;
import morn.library.boot.netty.NettyServer;
import morn.library.boot.netty.adapter.NettyCacheHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Netty服务端自动化配置
 */
@Slf4j
@Configuration
@ConditionalOnClass(ServerBootstrap.class)
@EnableConfigurationProperties(NettyServerProperties.class)
public class NettyServerConfiguration {

  /**
   * 注册服务端
   *
   * @return Netty服务端
   */
  @Bean
  public NettyServer nettyServer(NettyServerProperties properties) {
    return new NettyServer(properties);
  }

  /**
   * 注册缓存适配器
   *
   * @return 缓存适配器
   */
  @Bean
  public NettyCacheHandler nettyCacheHandler() {
    return new NettyCacheHandler();
  }
}

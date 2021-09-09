package morn.library.boot.netty.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Netty服务端配置项
 */
@Getter
@Setter
@ConfigurationProperties("morn.netty.server")
public class NettyServerProperties {

  /**
   * Listening port.
   *
   * @apiNote 监听端口
   */
  private int port = NettyConfigConstants.DEFAULT_PORT;

  /**
   * Automatic start.
   *
   * @apiNote 自动启动
   */
  private boolean autoStart = true;
}

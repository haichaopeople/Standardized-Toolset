package morn.library.boot.netty.config;

import static morn.library.boot.netty.config.NettyConfigConstants.DEFAULT_HOST;
import static morn.library.boot.netty.config.NettyConfigConstants.DEFAULT_PORT;

import lombok.Getter;
import lombok.Setter;

/**
 * Netty客户端配置
 */
@Getter
@Setter
public class NettyClientConfig {

  /**
   * Server host.
   *
   * @apiNote 服务端地址
   */
  private String serverHost = DEFAULT_HOST;

  /**
   * Server port.
   *
   * @apiNote 服务端端口
   */
  private int serverPort = DEFAULT_PORT;

  /**
   * Reconnection interval.
   *
   * @apiNote 重连间隔
   */
  private long connectDelay = 4L;

  /**
   * 最大连接数
   */
  private int maxConnections = 32;
}

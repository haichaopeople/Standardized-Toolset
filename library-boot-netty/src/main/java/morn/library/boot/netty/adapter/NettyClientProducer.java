package morn.library.boot.netty.adapter;

import morn.library.boot.netty.ChannelPoolIdentify;
import morn.library.boot.netty.NettyClient;

/**
 * Netty客户端生产者
 *
 * @param <I> 连接池标识类型
 */
public interface NettyClientProducer<I extends ChannelPoolIdentify> {

  /**
   * 创建客户端
   *
   * @param identify 连接池标识
   * @return 客户端
   */
  NettyClient create(I identify);
}

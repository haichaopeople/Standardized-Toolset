package morn.library.boot.netty.adapter;

import io.netty.channel.ChannelHandler;
import morn.library.core.BeanProducers;

/**
 * 通道处理器生产者
 */
public interface ChannelHandlerProducer extends BeanProducers<ChannelHandler> {

}

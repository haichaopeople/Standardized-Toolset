package morn.library.boot.netty.coder;

import io.netty.channel.ChannelHandler;
import morn.library.boot.netty.adapter.ChannelHandlerProducer;
import morn.library.boot.netty.annotation.NettyHandler;
import morn.library.boot.netty.annotation.NettyTerminal;
import morn.library.boot.netty.constant.HandlerTypeConstants;
import morn.library.boot.netty.constant.TerminalTypeConstants;

/**
 * Hex消息解码器生产者
 */
@NettyHandler(HandlerTypeConstants.DECODER)
@NettyTerminal(TerminalTypeConstants.BOTH)
public class HexMessageStringDecoderProducer implements ChannelHandlerProducer {

  @Override
  public ChannelHandler[] products() {
    return new ChannelHandler[]{
        // ByteBuf(String)转HexMessage
        new HexMessageStringDecoder()
    };
  }
}

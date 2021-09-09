package morn.library.boot.netty.coder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import morn.library.boot.netty.adapter.HexMessage;

/**
 * 十六进制消息编码器
 */
@Sharable
public class HexMessageBytesEncoder extends MessageToMessageEncoder<HexMessage> {


  @Override
  protected void encode(ChannelHandlerContext ctx, HexMessage msg, List<Object> out) {
    out.add(Unpooled.wrappedBuffer(msg.getArray()));
  }
}

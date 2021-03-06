package morn.library.boot.netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import morn.library.boot.netty.adapter.HexMessage;

/**
 * 十六进制消息解码器
 */
@Sharable
public class HexMessageBytesDecoder extends MessageToMessageDecoder<ByteBuf> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    // copy the ByteBuf content to a byte array
    byte[] bytes = ByteBufUtil.getBytes(msg);
    out.add(new HexMessage(bytes));
  }
}

package morn.library.boot.netty.coder;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import morn.library.boot.netty.adapter.HexMessage;

/**
 * Hex消息编码器
 */
@Sharable
public class HexMessageStringEncoder extends MessageToMessageEncoder<HexMessage> {

  /**
   * 字符编码
   */
  private final Charset charset;

  public HexMessageStringEncoder() {
    this(Charset.defaultCharset());
  }

  public HexMessageStringEncoder(Charset charset) {
    Assert.notNull(charset, "charset");
    this.charset = charset;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, HexMessage msg, List<Object> out) {
    if (Objects.isNull(msg) || StringUtils.isEmpty(msg.getMessage())) {
      return;
    }
    out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg.getMessage()), charset));
  }
}

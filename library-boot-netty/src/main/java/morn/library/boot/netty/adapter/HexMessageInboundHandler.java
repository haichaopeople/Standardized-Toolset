package morn.library.boot.netty.adapter;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import morn.library.boot.netty.annotation.NettyHandler;
import morn.library.boot.netty.annotation.NettyTerminal;
import morn.library.boot.netty.constant.HandlerTypeConstants;
import morn.library.boot.netty.constant.TerminalTypeConstants;

/**
 * 十六进制消息日志
 */
@Slf4j
@Sharable
@NettyHandler(HandlerTypeConstants.HANDLER)
@NettyTerminal(TerminalTypeConstants.BOTH)
public class HexMessageInboundHandler extends SimpleChannelInboundHandler<HexMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HexMessage hexMessage) {
    log.info("Netty|In|Hex：{}", hexMessage.getMessage());
    ctx.fireChannelRead(hexMessage);
  }
}

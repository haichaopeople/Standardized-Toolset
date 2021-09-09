package morn.library.boot.netty.adapter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import morn.library.boot.netty.cache.ChannelIdentify;
import morn.library.boot.netty.cache.NettyCaches;
import morn.library.boot.netty.annotation.NettyHandler;
import morn.library.boot.netty.annotation.NettyTerminal;
import morn.library.boot.netty.constant.HandlerTypeConstants;
import morn.library.boot.netty.constant.TerminalTypeConstants;

/**
 * 缓存处理者
 */
@Slf4j
@Sharable
@NettyHandler(HandlerTypeConstants.HANDLER)
@NettyTerminal(TerminalTypeConstants.SERVER)
public class NettyCacheHandler extends SimpleChannelInboundHandler<ChannelIdentify> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ChannelIdentify identify) {
    Channel channel = ctx.channel();
    NettyCaches.putChannel(identify, channel);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
    log.info("NettyServer|注册：{}", ctx.channel().id());
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    super.channelUnregistered(ctx);
    ChannelId id = ctx.channel().id();
    log.info("NettyServer|注销：{}", id);
    NettyCaches.removeAll(id);
  }
}

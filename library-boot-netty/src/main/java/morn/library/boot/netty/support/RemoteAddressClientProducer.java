package morn.library.boot.netty.support;

import morn.library.bean.annotation.Target;
import morn.library.boot.netty.NettyClient;
import morn.library.boot.netty.adapter.NettyClientProducer;
import morn.library.boot.netty.adapter.TerminalChannelAdapters;
import morn.library.boot.netty.config.NettyClientConfig;
import morn.library.boot.netty.constant.TerminalTypeConstants;

/**
 * 远程连接客户端生产者
 */
@Target(RemoteAddressChannelPoolIdentify.class)
public class RemoteAddressClientProducer implements
        NettyClientProducer<RemoteAddressChannelPoolIdentify> {

  @Override
  public NettyClient create(RemoteAddressChannelPoolIdentify identify) {
    NettyClientConfig clientConfig = new NettyClientConfig();
    clientConfig.setServerHost(identify.getRemoteHost());
    clientConfig.setServerPort(identify.getRemotePort());
    TerminalChannelAdapters.TerminalChannelPoolHandler channelPoolHandler = new TerminalChannelAdapters.TerminalChannelPoolHandler(
        TerminalTypeConstants.CLIENT);
    return new NettyClient(clientConfig, channelPoolHandler);
  }
}


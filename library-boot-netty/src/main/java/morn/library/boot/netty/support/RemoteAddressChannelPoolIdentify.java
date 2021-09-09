package morn.library.boot.netty.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import morn.library.boot.netty.ChannelPoolIdentify;

/**
 * 远程连接标识
 */
@Data
@AllArgsConstructor
public class RemoteAddressChannelPoolIdentify implements ChannelPoolIdentify {

  /**
   * 远程地址
   */
  private String remoteHost;

  /**
   * 远程端口
   */
  private int remotePort;
}

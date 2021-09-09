package morn.library.boot.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Netty客户端配置项
 */
@ConfigurationProperties("morn.netty.client")
public class NettyClientProperties extends NettyClientConfig {

}

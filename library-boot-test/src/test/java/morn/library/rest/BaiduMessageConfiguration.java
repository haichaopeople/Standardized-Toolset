package morn.library.rest;

import org.springframework.context.annotation.Configuration;

/**
 * 百度消息配置
 *
 */
@Configuration
public class BaiduMessageConfiguration implements RestConfigurer {

  @Override
  public void addSerialMessageClass(SerialMessageRegistry registry) {
    registry.register(BaiduMessage.class);
  }
}

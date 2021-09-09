package morn.library.boot.rest;

import lombok.Getter;
import lombok.Setter;
import morn.library.rest.RestConverter;
import morn.library.rest.RestMessage;
import morn.library.rest.SerialMessage;
import morn.library.rest.annotation.RestResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * REST配置项
 */
@Getter
@Setter
@ConfigurationProperties("morn.rest")
public class RestProperties {

  /**
   * 前缀
   */
  private String prefix = "rest";

  /**
   * 消息后缀
   */
  private String messageSuffix = "message";

  /**
   * 成功编码
   */
  private String successCode = "success";

  /**
   * 失败编码
   */
  private String failureCode = "failure";

  /**
   * 全局响应类型
   *
   * @see RestConverter
   */
  private Class<?> responseClass = RestMessage.class;

  /**
   * 强制序列
   * <p>全局使用{@link SerialMessage}作为响应消息，包括未使用{@link RestResponse}的REST方法</p>
   */
  private boolean forceSerial = false;
}

package morn.library.boot.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异常配置项
 */
@Getter
@Setter
@ConfigurationProperties("morn.exception")
public class ExceptionProperties {

  /**
   * 前缀
   */
  private String prefix = "error";

  /**
   * 消息编码后缀
   */
  private String codeSuffix = "code";

  /**
   * 消息后缀
   */
  private String messageSuffix = "message";

  /**
   * 解决方案后缀
   */
  private String solutionSuffix = "solution";


}

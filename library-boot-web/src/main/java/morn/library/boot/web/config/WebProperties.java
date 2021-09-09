package morn.library.boot.web.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Web配置
 */
@Getter
@Setter
@ConfigurationProperties("morn.web")
public class WebProperties {

  /**
   * 白名单
   */
  private List<String> whiteList;
}

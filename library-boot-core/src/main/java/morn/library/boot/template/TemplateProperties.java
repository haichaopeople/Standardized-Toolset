package morn.library.boot.template;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 模板配置项
 */
@Getter
@Setter
@ConfigurationProperties("morn.template")
public class TemplateProperties {

  private String prefix;

  public TemplateProperties() {
    this.prefix = "tpl";
  }
}

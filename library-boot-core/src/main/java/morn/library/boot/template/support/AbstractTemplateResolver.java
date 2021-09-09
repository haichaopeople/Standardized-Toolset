package morn.library.boot.template.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import morn.library.boot.template.TemplateProperties;
import morn.library.template.TemplateMeta;
import morn.library.template.TemplateResolver;
import org.springframework.util.Assert;

/**
 * 抽象模板解析器
 *
 * @param <T> 模板内容类型
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractTemplateResolver<T> implements TemplateResolver<T> {

  /**
   * 模板配置
   */
  private final TemplateProperties templateProperties;

  /**
   * 模板元数据
   */
  private TemplateMeta templateMeta;

  @Override
  public void setTemplateMeta(TemplateMeta templateMeta) {
    this.templateMeta = templateMeta;
  }

  /**
   * 获取模板路径
   *
   * @return 模板路径
   */
  public String getTemplateCode() {
    Assert.notNull(templateMeta, "无法获取模板元数据");
    return templateProperties.getPrefix() + "." + templateMeta.getName();
  }
}

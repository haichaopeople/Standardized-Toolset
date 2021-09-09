package morn.library.boot.template.support;

import java.util.Map.Entry;

import morn.library.boot.template.TemplateProperties;
import morn.library.core.CriteriaMap;
import morn.library.translate.Translator;
import org.springframework.util.StringUtils;

/**
 * 抽象资源模板解析器
 * <p>模板数据源为{@link org.springframework.context.MessageSource}</p>
 */
public class AbstractResourceTemplateResolver extends AbstractTemplateResolver<String> {

  /**
   * 国际化翻译器
   */
  private final Translator translator;

  public AbstractResourceTemplateResolver(TemplateProperties templateProperties,
                                          Translator translator) {
    super(templateProperties);
    this.translator = translator;
  }

  @Override
  public String resolve() {
    String templatePath = getTemplateCode();
    String templateContent = translator.translate(templatePath);
    CriteriaMap args = getTemplateMeta().getArgs();
    for (Entry<String, Object> entry : args.entrySet()) {
      String name = entry.getKey();
      String value = String.valueOf(entry.getValue());
      templateContent = StringUtils.replace(templateContent, getPlaceholder(name), value);
    }
    return templateContent;
  }

  /**
   * 生成占位符
   *
   * @param name 属性名称
   * @return 占位符
   */
  private String getPlaceholder(String name) {
    return String.format("${%s}", name);
  }
}

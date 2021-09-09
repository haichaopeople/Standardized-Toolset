package morn.library.boot.template.support;

import morn.library.bean.annotation.Target;
import morn.library.boot.template.TemplateProperties;
import morn.library.constant.ApplicationConstants;
import morn.library.template.annotation.TemplateType;
import morn.library.translate.Translator;

/**
 * 资源模板解析器
 * <p>模板数据源为{@link org.springframework.context.MessageSource}</p>
 */
@Target(String.class)
@TemplateType(ApplicationConstants.TemplateTypes.RESOURCE)
public class ResourceTemplateResolver extends AbstractResourceTemplateResolver {

  public ResourceTemplateResolver(TemplateProperties templateProperties, Translator translator) {
    super(templateProperties, translator);
  }
}

package morn.library.boot.notify.template;

import morn.library.bean.annotation.Target;
import morn.library.boot.template.TemplateProperties;
import morn.library.constant.ApplicationConstants;
import morn.library.notify.NotifyMeta;
import morn.library.notify.template.NotifyTemplateResolver;
import morn.library.template.annotation.TemplateType;
import morn.library.translate.Translator;
import morn.library.boot.template.support.AbstractResourceTemplateResolver;

/**
 * 资源模板解析器
 * <p>模板数据源为{@link org.springframework.context.MessageSource}</p>

 */
@Target(String.class)
@TemplateType(ApplicationConstants.TemplateTypes.RESOURCE)
public class ResourceNotifyTemplateResolver extends AbstractResourceTemplateResolver implements
        NotifyTemplateResolver<String> {

  /**
   * 通知元数据
   */
  private NotifyMeta notifyMeta;

  public ResourceNotifyTemplateResolver(TemplateProperties templateProperties,
                                        Translator translator) {
    super(templateProperties, translator);
  }

  @Override
  public String getTemplateCode() {
    return getTemplateProperties().getPrefix() + "." + notifyMeta.getNotifyType() + "."
        + getTemplateMeta().getName();
  }

  @Override
  public void setNotifyMeta(NotifyMeta notifyMeta) {
    this.notifyMeta = notifyMeta;
  }
}

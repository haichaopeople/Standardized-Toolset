package morn.library.boot.notify.template;

import lombok.Getter;
import morn.library.notify.NotifyMeta;
import morn.library.notify.template.NotifyTemplateResolver;
import morn.library.boot.template.TemplateProperties;
import morn.library.boot.template.support.AbstractTemplateResolver;

/**
 * 抽象通知模板解析器
 *
 * @param <T> 模板内容类型
 */
@Getter
public abstract class AbstractNotifyTemplateResolver<T> extends
    AbstractTemplateResolver<T> implements NotifyTemplateResolver<T> {

  /**
   * 通知元数据
   */
  private NotifyMeta notifyMeta;

  public AbstractNotifyTemplateResolver(TemplateProperties templateProperties) {
    super(templateProperties);
  }

  @Override
  public void setNotifyMeta(NotifyMeta notifyMeta) {
    this.notifyMeta = notifyMeta;
  }
}

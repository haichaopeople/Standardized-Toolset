package morn.library.notify.template;

import morn.library.notify.NotifyMeta;
import morn.library.template.TemplateResolver;

/**
 * 通知模板解析器
 *
 * @param <T> 模板数据类型
 */
public interface NotifyTemplateResolver<T> extends TemplateResolver<T> {

  /**
   * 设置通知元数据
   *
   * @param notifyMeta 通知元数据
   */
  void setNotifyMeta(NotifyMeta notifyMeta);
}

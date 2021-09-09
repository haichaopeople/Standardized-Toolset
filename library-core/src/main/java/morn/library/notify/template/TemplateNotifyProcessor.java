package morn.library.notify.template;

import morn.library.core.BeanProcessor;
import morn.library.notify.NotifyMeta;
import morn.library.template.TemplateMeta;

/**
 * 模板通知处理者
 *
 */
public interface TemplateNotifyProcessor<T> extends BeanProcessor<NotifyMeta> {

  /**
   * 设置模板元数据
   *
   * @param templateMeta 模板元数据
   */
  void setTemplateMeta(TemplateMeta templateMeta);

  /***
   * 设置模板内容
   * @param content 模板内容
   */
  void setTemplateContent(T content);
}

package morn.library.notify;

import morn.library.notify.annotation.Notify;
import morn.library.notify.annotation.NotifyReceiver;
import morn.library.template.annotation.Template;

/**
 * 注解式通知元数据适配器
 */
public interface AnnotationNotifyMetaAdapter extends NotifyMetaAdapter {

  /**
   * 设置通知
   *
   * @param notify 通知
   */
  void setNotify(Notify notify);

  /**
   * 设置通知接收人
   *
   * @param receiver 通知接收人
   */
  void setReceiver(NotifyReceiver receiver);

  /**
   * 设置通知模板
   *
   * @param template 通知模板
   */
  void setTemplate(Template template);
}

package morn.library.notify.support;

import lombok.Getter;
import morn.library.notify.AnnotationNotifyMetaAdapter;
import morn.library.notify.NotifyMeta;
import morn.library.notify.annotation.Notify;
import morn.library.notify.annotation.NotifyReceiver;
import morn.library.template.TemplateMeta;
import morn.library.template.annotation.Template;

/**
 * 注解通知适配器
 */
@Getter
public abstract class AbstractAnnotationNotifyMetaAdapter implements AnnotationNotifyMetaAdapter {

  /**
   * 通知
   */
  private Notify notify;

  /**
   * 通知接收人
   */
  private NotifyReceiver receiver;

  /**
   * 通知模板
   */
  private Template template;

  /**
   * 通知元数据
   */
  private NotifyMeta notifyMeta;

  /**
   * 模板元数据
   */
  private TemplateMeta templateMeta;

  @Override
  public void setNotify(Notify notify) {
    this.notify = notify;
  }

  @Override
  public void setReceiver(NotifyReceiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void setTemplate(Template template) {
    this.template = template;
  }

  @Override
  public void setNotifyMeta(NotifyMeta notifyMeta) {
    this.notifyMeta = notifyMeta;
  }

  @Override
  public void setTemplateMeta(TemplateMeta templateMeta) {
    this.templateMeta = templateMeta;
  }
}

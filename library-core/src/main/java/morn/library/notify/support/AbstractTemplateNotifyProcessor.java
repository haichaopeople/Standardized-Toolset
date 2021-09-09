package morn.library.notify.support;

import lombok.Getter;
import morn.library.notify.template.TemplateNotifyProcessor;
import morn.library.template.TemplateMeta;


/**
 * 抽象模板通知处理者
 */
@Getter
public abstract class AbstractTemplateNotifyProcessor<T> implements TemplateNotifyProcessor<T> {

  private TemplateMeta templateMeta;

  private T templateContent;

  @Override
  public void setTemplateMeta(TemplateMeta templateMeta) {
    this.templateMeta = templateMeta;
  }

  @Override
  public void setTemplateContent(T content) {
    this.templateContent = content;
  }
}

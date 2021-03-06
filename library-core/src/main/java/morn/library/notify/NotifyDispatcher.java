package morn.library.notify;

import morn.library.core.BeanProcessor;
import morn.library.template.TemplateMeta;

/**
 * 通知调度者
 * <p>{@link NotifyDispatcher}负责选择并组织{@link NotifyProcessor}完成通知的处理工作</p>
 */
public interface NotifyDispatcher extends BeanProcessor<NotifyMeta> {

  /**
   * 设置模板元数据
   *
   * @param templateMeta 模板元数据
   */
  void setTemplateMeta(TemplateMeta templateMeta);
}

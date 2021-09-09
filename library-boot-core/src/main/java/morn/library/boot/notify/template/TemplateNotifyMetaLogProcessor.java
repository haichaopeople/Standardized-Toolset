package morn.library.boot.notify.template;

import lombok.extern.slf4j.Slf4j;
import morn.library.notify.NotifyMeta;
import morn.library.notify.annotation.NotifyType;
import morn.library.notify.support.AbstractTemplateNotifyProcessor;

/**
 * 通知日志处理者

 */
@Slf4j
@NotifyType
public class TemplateNotifyMetaLogProcessor extends AbstractTemplateNotifyProcessor<String> {

  @Override
  public void handle(NotifyMeta meta) {
    log.info("Notify|{}, Content:{}", meta, getTemplateContent());
  }
}

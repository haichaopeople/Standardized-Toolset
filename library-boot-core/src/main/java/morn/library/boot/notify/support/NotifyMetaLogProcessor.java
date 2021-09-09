package morn.library.boot.notify.support;

import lombok.extern.slf4j.Slf4j;
import morn.library.notify.NotifyMeta;
import morn.library.notify.NotifyProcessor;
import morn.library.notify.annotation.NotifyType;

/**
 * 通知日志处理者
 */
@Slf4j
@NotifyType
public class NotifyMetaLogProcessor implements NotifyProcessor {

  @Override
  public void handle(NotifyMeta meta) {
    log.info("Notify|{}", meta);
  }
}

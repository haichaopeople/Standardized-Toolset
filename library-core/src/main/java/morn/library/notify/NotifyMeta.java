package morn.library.notify;

import java.util.List;
import lombok.Data;

/**
 * 通知元数据
 */
@Data
public class NotifyMeta {

  /**
   * 通知来源，通常是切点
   */
  private Object source;

  /**
   * 通知类型
   */
  private String notifyType;

  /**
   * 通知名称
   */
  private String notifyName;

  /**
   * 通知接收人类型
   */
  private String receiverType;

  /**
   * 通知接收人唯一标识
   */
  private List<String> receiverValues;
}

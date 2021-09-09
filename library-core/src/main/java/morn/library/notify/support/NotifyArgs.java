package morn.library.notify.support;

import static morn.library.constant.ApplicationConstants.Notifies.NOTIFY_RECEIVER_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import morn.library.core.CriteriaMap;
import org.springframework.util.CollectionUtils;

/**
 * 通知参数
 *
 */
public class NotifyArgs extends CriteriaMap {

  public NotifyArgs() {
  }

  public NotifyArgs(Map<String, Object> map) {
    super(map);
  }

  /**
   * 新增接收人标识
   *
   * @param receiverValue 接收人标识
   */
  public void addReceiverValue(String receiverValue) {
    List<String> list = getReceiverValues();
    list.add(receiverValue);
  }

  /**
   * 获取接收人标识集合
   *
   * @return 接收人标识集合
   */
  public List<String> getReceiverValues() {
    List<String> list = getExpect(NOTIFY_RECEIVER_VALUE);
    if (CollectionUtils.isEmpty(list)) {
      list = new ArrayList<>();
      set(NOTIFY_RECEIVER_VALUE, list);
    }
    return list;
  }
}

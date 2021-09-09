package morn.library.data;

/**
 * 可显示的
 *
 * <p>默认为显示状态
 *
 */
public interface Displayable {

  /**
   * 获取显示状态
   */
  Boolean getDisplay();

  /**
   * 设置显示状态
   */
  void setDisplay(Boolean isDisplay);
}

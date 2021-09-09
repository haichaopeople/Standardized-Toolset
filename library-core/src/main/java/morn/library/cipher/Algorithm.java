package morn.library.cipher;

/**
 * 算法
 *
 */
public interface Algorithm {

  /**
   * 算法名称
   */
  String getName();

  /**
   * 计算模式
   */
  String getMode();

  /**
   * 渲染风格
   */
  String getStyle();
}

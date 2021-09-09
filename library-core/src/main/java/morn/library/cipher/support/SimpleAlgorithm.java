package morn.library.cipher.support;

import lombok.Getter;
import lombok.ToString;
import morn.library.cipher.Algorithm;

/**
 * 算法摘要
 */
@Getter
@ToString
public class SimpleAlgorithm implements Algorithm {

  /**
   * 算法名称
   */
  private final String name;

  /**
   * 计算模式
   */
  private final String mode;

  /**
   * 渲染风格
   */
  private final String style;

  public SimpleAlgorithm(String name, String mode, String style) {
    this.name = name;
    this.mode = mode;
    this.style = style;
  }
}

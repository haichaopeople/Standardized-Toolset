package morn.library.cipher.support;

import morn.library.cipher.Algorithm;
import morn.library.cipher.AlgorithmHolder;

/**
 * 算法持有者实现
 */
public class SimpleAlgorithmHolder implements AlgorithmHolder {

  /**
   * 算法
   */
  protected Algorithm algorithm;

  public Algorithm algorithm() {
    return algorithm;
  }

  @Override
  public void setAlgorithm(Algorithm algorithm) {
    this.algorithm = algorithm;
  }
}

package morn.library.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 实例生产者
 */
@FunctionalInterface
public interface BeanProducers<T> {

  /**
   * 生产
   *
   * @return 实例数组
   */
  T[] products();

  /**
   * 生产
   *
   * @return 实例集合
   */
  default List<T> productList() {
    return Stream.of(products()).collect(Collectors.toList());
  }
}

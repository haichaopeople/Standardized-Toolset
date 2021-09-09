package morn.library.boot.data.jpa;

/**
 * JPA查询规格函数
 *
 */
@FunctionalInterface
public interface SpecificationFunction {

  /**
   * 查询规格
   *
   * @param reference 引用对象
   * @param restrain 约束构建
   * @param condition 条件构建
   */
  void predicate(JpaReference<?> reference, JpaPredicate restrain, JpaBatchCondition condition);
}

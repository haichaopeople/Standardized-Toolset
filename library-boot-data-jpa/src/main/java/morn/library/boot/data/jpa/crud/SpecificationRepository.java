package morn.library.boot.data.jpa.crud;

import java.io.Serializable;
import java.util.List;

import morn.library.boot.data.jpa.SpecificationFunction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import morn.library.boot.data.jpa.support.SpecificationBuilder.SimpleFunction;
import morn.library.core.CriteriaMap;

/**
 * 基础数据访问
 *
 */
@NoRepositoryBean
public interface SpecificationRepository<T, I extends Serializable> extends PagingAndSortingRepository<T, I>,
    JpaSpecificationExecutor<T> {

  /**
   * 查询唯一数据, 精确匹配
   *
   * @param model 查询参数
   * @return 唯一实例
   */
  T findOne(T model);

  /**
   * 查询唯一数据
   *
   * @param simpleFunction 查询条件
   * @return 唯一实例
   */
  T findOne(SimpleFunction<T> simpleFunction);

  /**
   * 查询唯一数据
   *
   * @param model 查询参数
   * @param specificationFunction 查询条件
   * @return 唯一实例
   */
  T findOne(T model, SpecificationFunction specificationFunction);

  /**
   * 查询唯一数据
   *
   * @param model 查询参数
   * @param attach 附加数据
   * @param specificationFunction 查询条件
   * @return 唯一实例
   */
  T findOne(T model, CriteriaMap attach, SpecificationFunction specificationFunction);

  /**
   * 查询所有数据, 精确匹配
   *
   * @param model 查询参数
   * @return 实例集合
   */
  List<T> findAll(T model);
}

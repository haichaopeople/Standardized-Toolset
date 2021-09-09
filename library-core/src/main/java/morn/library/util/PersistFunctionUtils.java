package morn.library.util;

import java.util.List;
import lombok.experimental.UtilityClass;
import morn.library.bean.support.BeanCaches;
import morn.library.data.persistent.AddProcessor;
import morn.library.data.persistent.AddValidator;
import morn.library.data.persistent.DeleteProcessor;
import morn.library.data.persistent.DeleteValidator;
import morn.library.data.persistent.PersistProcessor;
import morn.library.data.persistent.PersistValidator;
import morn.library.data.persistent.UpdateProcessor;
import morn.library.data.persistent.UpdateValidator;

/**
 * 持久化工具类
 *
 */
@UtilityClass
public class PersistFunctionUtils {

  /**
   * 新增处理
   *
   * @param data 数据
   * @param <T> 数据类型
   * @see PersistProcessor#handle(T)
   */
  @SuppressWarnings("unchecked")
  public static <T> void handleAdd(T data) {
    handle(AddProcessor.class, data);
  }

  /**
   * 删除处理
   *
   * @param data 数据
   * @param <T> 数据类型
   * @see PersistProcessor#handle(T)
   */
  @SuppressWarnings("unchecked")
  public static <T> void handleDelete(T data) {
    handle(DeleteProcessor.class, data);
  }

  /**
   * 更新处理
   *
   * @param data 数据
   * @param <T> 数据类型
   * @see PersistProcessor#handle(T)
   */
  @SuppressWarnings("unchecked")
  public static <T> void handleUpdate(T data) {
    handle(UpdateProcessor.class, data);
  }

  /**
   * 后置持久化操作处理
   *
   * @param functionClass 处理类
   * @param data 数据
   * @param <V> 处理类型
   * @param <T> 数据类型
   */
  public static <V extends PersistProcessor<T>, T> void handle(Class<V> functionClass, T data) {
    List<V> processes = BeanCaches.sourceBeans(functionClass, data.getClass());
    for (V process : processes) {
      process.handle(data);
    }
  }

  /**
   * 数据新增校验
   *
   * @param data 校验数据
   * @param <T> 数据类型
   * @return 是否通过验证
   */
  @SuppressWarnings("unchecked")
  public static <T> boolean validateAdd(T data) {
    return validate(AddValidator.class, data);
  }

  /**
   * 数据删除校验
   *
   * @param data 校验数据
   * @param <T> 数据类型
   * @return 是否通过验证
   */
  @SuppressWarnings("unchecked")
  public static <T> boolean validateDelete(T data) {
    return validate(DeleteValidator.class, data);
  }

  /**
   * 数据修改校验
   *
   * @param data 校验数据
   * @param <T> 数据类型
   * @return 是否通过验证
   */
  @SuppressWarnings("unchecked")
  public static <T> boolean validateUpdate(T data) {
    return validate(UpdateValidator.class, data);
  }

  /**
   * 验证持久化操作
   *
   * @param validateClass 校验类
   * @param data 校验数据
   * @param <V> 校验类型
   * @param <T> 数据类型
   * @return 是否通过验证
   */
  public static <V extends PersistValidator<T>, T> boolean validate(Class<V> validateClass,
      T data) {
    boolean valid = true;
    List<V> validations = BeanCaches.sourceBeans(validateClass, data.getClass());
    for (V validation : validations) {
      if (!validation.validate(data)) {
        valid = false;
      }
    }
    return valid;
  }
}

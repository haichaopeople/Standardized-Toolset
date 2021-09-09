package morn.library.boot.json;

import java.lang.reflect.Type;

import morn.library.core.CriteriaMap;
import morn.library.type.TypeBuilder;
import org.springframework.core.ParameterizedTypeReference;

/**
 * JSON解析器
 */
public interface JsonParser {

  /**
   * 解析为Map
   *
   * @param object 任意对象
   * @return 标准字典
   */
  CriteriaMap convertMap(Object object);

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param cls    类型
   * @param <T>    类泛型
   * @return Java对象
   */
  <T> T convert(Object object, Class<T> cls);

  /**
   * 解析为泛型对象
   *
   * @param object    任意对象
   * @param reference 类型引用
   * @param <T>       类泛型
   * @return Java对象
   */
  <T> T convert(Object object, ParameterizedTypeReference<T> reference);

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param type   解析类型
   * @param <T>    类泛型
   * @return Java对象
   */
  <T> T convert(Object object, Type type);

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param typeBuilder 自定义解析类型
   * @param <T>    类泛型
   * @return Java对象
   */
  <T> T convert(Object object, TypeBuilder typeBuilder);


  /**
   * 解析为JSON字符串
   *
   * @param object 任意对象
   * @return JSON字符串
   */
  String convertStr(Object object);
}

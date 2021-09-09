package morn.library.boot.json.util;

import java.lang.reflect.Type;

import morn.library.bean.support.BeanCaches;
import morn.library.boot.json.JsonParser;
import morn.library.boot.json.constant.JsonParserConstants;
import morn.library.core.CriteriaMap;
import morn.library.type.TypeBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.Assert;

/**
 * JSON解析工具栏
 */
public class JsonParsers {

  private JsonParsers() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * 获取默认解析器
   *
   * @return JSON解析器
   */
  public static JsonParser getParser() {
    JsonParser parser = BeanCaches.bean(JsonParser.class);
    Assert.notNull(parser, "无法获取默认JSON解析器");
    return parser;
  }

  /**
   * 获取解析器
   *
   * @param type 解析器类型
   * @return JSON解析器
   * @see JsonParserConstants 解析器类型
   */
  public static JsonParser getParser(String type) {
    JsonParser parser = BeanCaches.tagBean(JsonParser.class, type);
    Assert.notNull(parser, "无法获取JSON解析器：" + type);
    return parser;
  }


  /**
   * 解析为Map
   *
   * @param object 任意对象
   * @return 标准字典
   */
  public static CriteriaMap convertMap(Object object) {
    return getParser().convertMap(object);
  }

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param cls    类型
   * @param <T>    类泛型
   * @return Java对象
   */
  public static <T> T convertObject(Object object, Class<T> cls) {
    return getParser().convert(object, cls);
  }

  /**
   * 解析为泛型对象
   *
   * @param object    任意对象
   * @param reference 类型引用
   * @param <T>       类泛型
   * @return Java对象
   */
  public static <T> T convertObject(Object object, ParameterizedTypeReference<T> reference) {
    return getParser().convert(object, reference);
  }

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param type   解析类型
   * @param <T>    类泛型
   * @return Java对象
   */
  public static <T> T convertObject(Object object, Type type) {
    return getParser().convert(object, type);
  }

  /**
   * 解析为泛型对象
   *
   * @param object 任意对象
   * @param typeBuilder   解析类型
   * @param <T>    类泛型
   * @return Java对象
   */
  public static <T> T convertObject(Object object, TypeBuilder typeBuilder) {
    return getParser().convert(object, typeBuilder.build());
  }


  /**
   * 解析为JSON字符串
   *
   * @param object 任意对象
   * @return JSON字符串
   */
  public static String convertStr(Object object) {
    return getParser().convertStr(object);
  }
}

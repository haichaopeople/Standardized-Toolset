package morn.library.test;

import morn.library.bean.annotation.Tag;
import morn.library.bean.annotation.Target;
import morn.library.core.BeanConverter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 转换器测试类
 */
@Component
public class TestConverterBeans {

  public static final String CONVERTER = "converter";

  public static final String LOWER_CASE = "lowerCase";

  /**
   * 测试转换器接口
   */
  public interface TestConverter extends BeanConverter<String, String> {

    @Override
    String convert(String source);
  }


  @Order(1)
  @Component
  @Tag({CONVERTER, LOWER_CASE})
  public static class LowerCaseConverter implements TestConverter {

    @Override
    public String convert(String source) {
      return source.toLowerCase();
    }
  }

  @Order(2)
  @Component
  @Tag(CONVERTER)
  @Target(String.class)
  public static class UpperCaseConverter implements TestConverter {

    @Override
    public String convert(String source) {
      return source.toUpperCase();
    }
  }
}

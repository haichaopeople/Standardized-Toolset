package morn.library.test;

import morn.library.bean.annotation.Tag;
import morn.library.bean.annotation.Target;
import morn.library.core.BeanProducer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 生产者测试类
 */
@Component
public class TestProducerBeans {

  public static final String BEAN_PRODUCER = "beanProducer";

  public static final String PRODUCT_ONE = "one";

  @Order(1)
  @Component
  @Tag({BEAN_PRODUCER, PRODUCT_ONE})
  public static class OneProducer implements BeanProducer<String> {

    @Override
    public String product() {
      return "1";
    }
  }

  @Order(2)
  @Component
  @Tag(BEAN_PRODUCER)
  @Target(String.class)
  public static class TwoProducer implements BeanProducer<String> {

    @Override
    public String product() {
      return "2";
    }
  }
}

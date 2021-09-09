package morn.library.util;

import java.util.List;

import morn.library.core.BeanProducer;
import morn.library.core.BeanValidator;
import morn.library.test.TestAdapterBeans;
import morn.library.test.TestConverterBeans;
import morn.library.test.TestProcessorBeans;
import morn.library.test.TestProducerBeans;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 函数工具测试类
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanFunctionUtilsTest {

  @Test
  public void adaption() {
    Integer result = BeanFunctionUtils.adaption(TestAdapterBeans.TestAdapter.class, 1, TestAdapterBeans.ADD);
    Assert.assertEquals(2, result.intValue());
  }

  @Test
  public void testAdaption() {
    Integer result = BeanFunctionUtils.adaption(TestAdapterBeans.TestAdapter.class, 2, TestAdapterBeans.Sub.class);
    Assert.assertEquals(0, result.intValue());
  }

  @Test
  public void adaptions() {
    Integer result = BeanFunctionUtils.adaptions(TestAdapterBeans.TestAdapter.class, 1, TestAdapterBeans.ARITHMETIC);
    Assert.assertEquals(0, result.intValue());
  }

  @Test
  public void convert() {
    String result = BeanFunctionUtils.convert(TestConverterBeans.TestConverter.class, "Hello", TestConverterBeans.LOWER_CASE);
    Assert.assertEquals("hello", result);
  }

  @Test
  public void testConvert() {
    String result = BeanFunctionUtils.convert(TestConverterBeans.TestConverter.class, "Hello", String.class);
    Assert.assertEquals("HELLO", result);
  }

  @Test
  public void converts() {
    List<String> result = BeanFunctionUtils.converts(TestConverterBeans.TestConverter.class, "Hello", TestConverterBeans.CONVERTER);
    Assert.assertArrayEquals(new String[]{"hello", "HELLO"}, result.toArray());
  }

  @Test
  public void process() {
    StringBuilder stringBuilder = new StringBuilder();
    BeanFunctionUtils.process(TestProcessorBeans.TestProcessor.class, stringBuilder, TestProcessorBeans.ProcessorTwo.class);
    Assert.assertEquals("2", stringBuilder.toString());
  }

  @Test
  public void processes() {
    StringBuilder stringBuilder = new StringBuilder();
    BeanFunctionUtils.processes(TestProcessorBeans.TestProcessor.class, stringBuilder, TestProcessorBeans.ProcessorTwo.class);
    Assert.assertEquals("2", stringBuilder.toString());
  }

  @Test
  public void testProcesses() {
    StringBuilder stringBuilder = new StringBuilder();
    BeanFunctionUtils.processes(TestProcessorBeans.TestProcessor.class, stringBuilder, TestProcessorBeans.BEAN_PROCESSOR);
    Assert.assertEquals("12", stringBuilder.toString());
  }

  @Test
  public void product() {
    String result = BeanFunctionUtils.product(BeanProducer.class, TestProducerBeans.PRODUCT_ONE);
    Assert.assertEquals("1", result);
  }

  @Test
  public void testProduct() {
    String result = BeanFunctionUtils.product(BeanProducer.class, String.class);
    Assert.assertEquals("2", result);
  }

  @Test
  public void validates() {
    boolean result = BeanFunctionUtils.validates(BeanValidator.class, "ab");
    Assert.assertTrue(result);
  }
}
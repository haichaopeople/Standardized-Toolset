package morn.library.bean;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.support.AnnotationFeatureBuilder;
import morn.library.bean.support.BeanCaches;
import morn.library.bean.support.FunctionHolderUtils;
import morn.library.bean.support.Tags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import morn.library.test.TestAnnotationBeans.Animal;
import morn.library.test.TestAnnotationBeans.Food;

/**
 * 实例函数单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionHolderUtilsTest {

  @Test
  public void catPlay() {
    String[] cats = Tags.from(Animal.class, "cat").toArray();
    AnnotationFeature beanId = AnnotationFeatureBuilder.withTags(cats).build();
    AnnotationFeature functionId = AnnotationFeatureBuilder.withName("play").build();
    List<FunctionHolder> functions = BeanCaches.functions(beanId, functionId);
    try {
      List<String> call = FunctionHolderUtils.call(functions);
      log.info(call.toString());
      Assert.assertEquals("catPlay", 2, call.size());
    } catch (Exception e) {
      Assert.fail("catPlay");
    }
  }

  @Test
  public void eat() {
    String meat = "meat";
    Food food = new Food("fish");

    AnnotationFeature functionId = AnnotationFeatureBuilder.withName("eat").build();
    List<FunctionHolder> functions = BeanCaches.functions(functionId);
    try {
      List<String> call = FunctionHolderUtils.call(functions, food, meat);
      log.info(call.toString());
      Assert.assertEquals("call", 4, call.size());
    } catch (Exception e) {
      Assert.fail("call");
    }
  }
}
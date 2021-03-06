package morn.library.bean;

import java.util.List;

import morn.library.bean.support.AnnotationFeatureBuilder;
import morn.library.bean.support.BeanCaches;
import morn.library.bean.support.Tags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import morn.library.test.TestAnnotationBeans.Animal;
import morn.library.test.TestAnnotationBeans.Cat;
import morn.library.test.TestAnnotationBeans.Color;
import morn.library.test.TestAnnotationBeans.Dog;
import morn.library.test.TestAnnotationBeans.Food;
import morn.library.test.TestAnnotationBeans.Mammal;
import morn.library.test.TestAnnotationBeans.Toy;

/**
 * 实例缓存单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanCachesTest {

  @Test
  public void bean() { // bean test
    String[] tags = Tags.from(Color.class, "yellow").add("big").toArray();
    AnnotationFeature annotationFeature = AnnotationFeatureBuilder.withTags(tags).target(Toy.class)
        .build();
    Dog dog = BeanCaches.bean(Dog.class, annotationFeature);
    Assert.assertNotNull("bean", dog);
  }

  @Test
  public void nameBean() { // bean test
    Cat caramel = BeanCaches.nameBean(Cat.class, "caramel");
    Assert.assertNotNull("nameBean", caramel);
  }

  @Test
  public void tagBean() { // bean test
    Object big = BeanCaches.tagBean(null, "big");
    Assert.assertNotNull("tagBean", big);
  }

  @Test
  public void targetBean() { // bean test
    Object o = BeanCaches.targetBean(null, Food.class);
    Assert.assertNotNull("targetBean", o);
  }

  @Test
  public void beans() { // bean test
    String[] tags = Tags.from(Animal.class).toArray();
    AnnotationFeature annotationFeature = AnnotationFeatureBuilder.withTags(tags).target(Food.class)
        .build();
    List<Cat> beans = BeanCaches.beans(null, annotationFeature);
    Assert.assertEquals("beans", 2, beans.size());
  }

  @Test
  public void tagBeans() { // bean test
    List<Mammal> small = BeanCaches.tagBeans(Mammal.class, "small");
    Assert.assertEquals("tagBeans", 3, small.size());
  }

  @Test
  public void targetBeans() { // bean test
    List<Object> objects = BeanCaches.targetBeans(null, Toy.class);
    Assert.assertEquals("targetBeans", 2, objects.size());
  }

  @Test
  public void targetCats() { // bean test
    List<Cat> cats = BeanCaches.targetBeans(Cat.class, Toy.class);
    Assert.assertEquals("targetCats", 1, cats.size());
  }

  @Test
  public void eat() { // function test
    AnnotationFeature annotationFeature = AnnotationFeatureBuilder.withName("eat").build();
    List<FunctionHolder> functions = BeanCaches.functions(annotationFeature);
    Assert.assertEquals("函数：" + functions.size(), 4, functions.size());
  }

  @Test
  public void eatMeat() { // function test
    String[] meats = Tags.from("meat").toArray();
    AnnotationFeature annotationFeature = AnnotationFeatureBuilder.withName("eat").tags(meats)
        .build();
    List<FunctionHolder> functions = BeanCaches.functions(annotationFeature);
    Assert.assertEquals("函数：" + functions.size(), 1, functions.size());
  }

  @Test
  public void play() { // function test
    AnnotationFeature annotationFeature = AnnotationFeatureBuilder.withName("play")
        .source(Cat.class).build();
    List<FunctionHolder> functions = BeanCaches.functions(annotationFeature);
    Assert.assertEquals("函数：" + functions.size(), 2, functions.size());
  }

  @Test
  public void dogEat() { // function test
    String[] beanTags = {"animal:dog"};
    AnnotationFeature beanFeature = AnnotationFeatureBuilder.withTags(beanTags).build();
    AnnotationFeature functionFeature = AnnotationFeatureBuilder.withName("eat").build();
    List<FunctionHolder> functions = BeanCaches.functions(beanFeature, functionFeature);
    Assert.assertEquals("函数：" + functions.size(), 1, functions.size());
  }
}
package morn.library.boot.bean;

import morn.library.bean.AnnotationField;
import morn.library.bean.AnnotationFieldRegistry;
import morn.library.bean.BeanConfigurer;
import org.springframework.context.annotation.Configuration;
import morn.library.test.TestAnnotationBeans.Animal;
import morn.library.test.TestAnnotationBeans.Color;

/**
 * 测试实例注解配置
 */
@Configuration
public class TestBeanAnnotationConfiguration implements BeanConfigurer {

  @Override
  public void addBeanAnnotations(AnnotationFieldRegistry registry) {
    AnnotationField animal = new AnnotationField(Animal.class);
    animal.setValueName("value");
    animal.setAnnotationName("animal");
    registry.add(animal);

    registry.add(Color.class);
  }
}

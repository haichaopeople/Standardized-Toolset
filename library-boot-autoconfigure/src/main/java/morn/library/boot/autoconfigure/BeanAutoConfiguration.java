package morn.library.boot.autoconfigure;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import morn.library.bean.AnnotationFieldRegistry;
import morn.library.bean.AnnotationFieldType;
import morn.library.bean.BeanCache;
import morn.library.bean.BeanConfigurer;
import morn.library.util.OptionalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.bean.annotation.Function;
import morn.library.bean.annotation.Name;
import morn.library.bean.annotation.Source;
import morn.library.bean.annotation.Tag;
import morn.library.bean.annotation.Target;
import morn.library.boot.bean.AnnotationBeanPostProcessor;
import morn.library.boot.bean.BeanCacheInitializer;
import morn.library.boot.bean.SimpleBeanCache;

/**
 * 实例自动化配置
 *
 */
@Configuration
@ConditionalOnClass(CacheManager.class)
public class BeanAutoConfiguration implements BeanConfigurer {

  @Override
  public void addBeanAnnotations(AnnotationFieldRegistry registry) {
    registry.add(Name.class, AnnotationFieldType.NAME);
    registry.add(Tag.class);
    registry.add(Source.class, AnnotationFieldType.SOURCE);
    registry.add(Target.class, AnnotationFieldType.TARGET);
  }

  @Override
  public void addFunctionAnnotations(AnnotationFieldRegistry registry) {
    registry.add(Function.class, AnnotationFieldType.NAME);
    registry.add(Tag.class);
    registry.add(Source.class, AnnotationFieldType.SOURCE);
    registry.add(Target.class, AnnotationFieldType.TARGET);
  }

  /**
   * 注册实例注解注册表
   *
   * @return 实例注解注册表
   */
  @Bean(AnnotationFieldRegistry.BEAN_ANNOTATION_REGISTRY)
  @ConditionalOnMissingBean(name = AnnotationFieldRegistry.BEAN_ANNOTATION_REGISTRY)
  public AnnotationFieldRegistry beanAnnotationRegistry() {
    return new AnnotationFieldRegistry();
  }

  /**
   * 注册函数注解注册表
   */
  @Bean(AnnotationFieldRegistry.FUNCTION_ANNOTATION_REGISTRY)
  @ConditionalOnMissingBean(name = AnnotationFieldRegistry.FUNCTION_ANNOTATION_REGISTRY)
  public AnnotationFieldRegistry functionAnnotationRegistry() {
    return new AnnotationFieldRegistry();
  }

  /**
   * 注册标识实例缓存
   *
   * <p>提供实例缓存功能
   *
   * @return 标识实例缓存
   */
  @Bean
  @ConditionalOnMissingBean
  public BeanCache identifiedBeanCache() {
    return new SimpleBeanCache();
  }

  /**
   * 注册标识实例后置处理器
   *
   * <p>识别实例注解并缓存
   *
   * @param configurers            实例配置
   * @param beanAnnotationRegistry 实例注解注册表
   * @param beanCache              标识实例缓存
   * @return 标识实例后置处理器
   */
  @Bean
  @ConditionalOnMissingBean
  public AnnotationBeanPostProcessor identifiedBeanPostProcessor(
      @Autowired(required = false) List<BeanConfigurer> configurers,
      @Qualifier(AnnotationFieldRegistry.BEAN_ANNOTATION_REGISTRY) AnnotationFieldRegistry beanAnnotationRegistry,
      @Qualifier(AnnotationFieldRegistry.FUNCTION_ANNOTATION_REGISTRY) AnnotationFieldRegistry functionAnnotationRegistry,
      BeanCache beanCache) {
    Collection<BeanConfigurer> collection = OptionalCollection.ofNullable(configurers)
        .orElse(Collections.emptyList());
    registeredAnnotations(collection, beanAnnotationRegistry, functionAnnotationRegistry);
    return new AnnotationBeanPostProcessor(beanAnnotationRegistry, functionAnnotationRegistry,
        beanCache);
  }

  /**
   * 注册实例缓存初始化器
   *
   * @param beanCache 实例缓存
   * @return 实例缓存初始化器
   */
  @Bean
  @ConditionalOnMissingBean
  public BeanCacheInitializer beanCacheInitializer(BeanCache beanCache) {
    return new BeanCacheInitializer(beanCache);
  }

  /**
   * 初始化实例注解
   *
   * @param configurers      实例配置
   * @param beanRegistry     实例注解注册表
   * @param functionRegistry 函数注解注册表
   */
  private void registeredAnnotations(Collection<BeanConfigurer> configurers,
      AnnotationFieldRegistry beanRegistry, AnnotationFieldRegistry functionRegistry) {
    // 注册自定义注解实例
    for (BeanConfigurer configurer : configurers) {
      configurer.addBeanAnnotations(beanRegistry);
      configurer.addFunctionAnnotations(functionRegistry);
    }
  }
}

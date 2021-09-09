package morn.library.boot.bean;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import morn.library.bean.BeanCache;
import morn.library.bean.support.BeanCaches;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实例缓存初始化
 */
@RequiredArgsConstructor
public class BeanCacheInitializer implements ApplicationContextAware {

  /**
   * 实例缓存
   */
  private final BeanCache beanCache;

  /**
   * 实例工厂
   */
  private BeanFactory beanFactory;

  @PostConstruct
  public void initialize() {
    BeanCaches.initialize(beanCache, beanFactory);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.beanFactory = applicationContext;
  }
}

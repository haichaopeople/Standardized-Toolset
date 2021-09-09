package morn.library.boot.log;

import morn.library.bean.AnnotationFieldRegistry;
import morn.library.bean.BeanCache;
import morn.library.bean.BeanConfigurer;
import morn.library.log.OperateMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志配置
 */
@Configuration
@EnableConfigurationProperties(OperateProperties.class)
public class OperateConfiguration implements BeanConfigurer {

  @Override
  public void addBeanAnnotations(AnnotationFieldRegistry registry) {
    registry.add(OperateMode.class);
  }

  /**
   * 注册操作日志切面
   *
   * @return 操作日志切面
   */
  @Bean
  @ConditionalOnMissingBean
  public OperateAspect operateAspect(BeanCache beanCache, OperateProperties properties) {
    return new OperateAspect(beanCache, properties);
  }

  /**
   * 注册日志转换器-简易模式
   */
  @Bean
  @ConditionalOnMissingBean
  public SimpleOperationConverter simpleOperationConverter(OperateProperties properties) {
    return new SimpleOperationConverter(properties);
  }

  /**
   * 注册基础操作日志适配器
   */
  @Bean
  @ConditionalOnMissingBean
  public SimpleOperationAdapter simpleOperationAdapter() {
    return new SimpleOperationAdapter();
  }
}

package morn.library.boot.autoconfigure;

import morn.library.bean.BeanCache;
import morn.library.rest.RestConverterService;
import morn.library.rest.support.SimpleRestConverterService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.boot.rest.RestInitializer;
import morn.library.boot.rest.RestProperties;
import morn.library.translate.Translator;

/**
 * REST自动化配置
 */
@Configuration
@ConditionalOnClass(CacheManager.class)
@EnableConfigurationProperties(RestProperties.class)
@AutoConfigureAfter(TranslatorAutoConfiguration.class)
public class RestAutoConfiguration {

  /**
   * 注册REST消息转换器服务
   */
  @Bean
  @ConditionalOnMissingBean
  public RestConverterService restConverterService() {
    return new SimpleRestConverterService();
  }

  /**
   * 注册REST初始化器
   *
   * @param beanCache  实例缓存
   * @param translator 翻译器
   * @return REST初始化器
   */
  @Bean
  @ConditionalOnBean(Translator.class)
  public RestInitializer restInitializer(BeanCache beanCache, Translator translator,
                                         RestProperties restProperties) {
    return new RestInitializer(beanCache, translator, restProperties);
  }
}

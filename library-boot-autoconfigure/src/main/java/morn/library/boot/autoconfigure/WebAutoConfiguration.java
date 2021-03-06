package morn.library.boot.autoconfigure;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import java.util.List;
import javax.servlet.Servlet;

import morn.library.boot.web.RestResponseAdvice;
import morn.library.boot.web.config.WebProperties;
import morn.library.rest.RestConfigurer;
import morn.library.rest.RestConverterService;
import morn.library.rest.SerialMessageRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import morn.library.boot.rest.RestProperties;
import morn.library.exception.ExceptionInterpreterService;


/**
 * Web自动化配置
 */
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class WebAutoConfiguration {

  /**
   * 注册序列消息注册表
   */
  @Bean
  @ConditionalOnMissingBean
  public SerialMessageRegistry serialMessageRegistry(List<RestConfigurer> configurers) {
    SerialMessageRegistry registry = new SerialMessageRegistry();
    for (RestConfigurer configurer : configurers) {
      configurer.addSerialMessageClass(registry);
    }
    return registry;
  }

  /**
   * Web模块配置
   */
  @Configuration
  @EnableConfigurationProperties(WebProperties.class)
  @AutoConfigureAfter(ExceptionAutoConfiguration.class)
  @ConditionalOnClass({WebProperties.class, RestResponseAdvice.class})
  public static class WebConfiguration {

    /**
     * 注册REST响应处理者
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ExceptionInterpreterService.class)
    public RestResponseAdvice restResponseAdvice(RestProperties properties,
                                                 RestConverterService converterService, ExceptionInterpreterService interpreterService,
                                                 SerialMessageRegistry registry) {
      return new RestResponseAdvice(properties, converterService, interpreterService, registry);
    }
  }

  /**
   * Jackson配置
   */
  @Configuration
  @ConditionalOnClass(Hibernate5Module.class)
  public static class JacksonConfiguration {

    /**
     * 注册Hibernate5Module模块
     *
     * @see JacksonAutoConfiguration
     * @see Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    @ConditionalOnMissingBean
    public Hibernate5Module hibernate5Module() {
      Hibernate5Module hibernate5Module = new Hibernate5Module();
      hibernate5Module.disable(Feature.USE_TRANSIENT_ANNOTATION);
      return hibernate5Module;
    }
  }
}

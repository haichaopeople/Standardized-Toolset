package morn.library.boot.autoconfigure;

import javax.servlet.Servlet;

import morn.library.boot.log.OperateConfiguration;
import morn.library.boot.security.SecurityOperationAdapter;
import morn.library.boot.web.config.WebProperties;
import morn.library.boot.web.log.WebOperationAdapter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import morn.library.boot.log.I18nOperationConverter;
import morn.library.boot.log.OperateAspect;
import morn.library.boot.log.OperateProperties;
import morn.library.translate.Translator;

/**
 * 操作日志自动化配置
 *
 */
@Configuration
@ConditionalOnBean(OperateAspect.class)
@AutoConfigureAfter(TransactionAutoConfiguration.class)
public class OperateAutoConfiguration {

  /**
   * 注册日志转换器-国际化模式
   */
  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(Translator.class)
  public I18nOperationConverter i18nOperationConverter(OperateProperties properties,
      Translator translator) {
    return new I18nOperationConverter(properties, translator);
  }

  /**
   * Web操作日志相关配置
   */
  @Configuration
  @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class,
      WebProperties.class})
  public static class WebOperateConfiguration {

    /**
     * 注册Web操作日志适配器
     */
    @Bean
    @ConditionalOnMissingBean
    public WebOperationAdapter webOperationAdapter() {
      return new WebOperationAdapter();
    }
  }

  /**
   * Security操作日志相关配置
   */
  @Configuration
  @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class,
      SecurityContext.class, SecurityOperationAdapter.class})
  public static class SecurityOperateConfiguration {

    /**
     * 注册Security操作日志适配器
     */
    @Bean
    @ConditionalOnMissingBean
    public SecurityOperationAdapter securityOperationAdapter() {
      return new SecurityOperationAdapter();
    }
  }
}

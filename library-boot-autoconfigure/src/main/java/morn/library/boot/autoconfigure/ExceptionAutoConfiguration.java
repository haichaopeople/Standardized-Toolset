package morn.library.boot.autoconfigure;

import morn.library.bean.BeanCache;
import morn.library.boot.exception.interpreter.MethodValidateExceptionInterpreter;
import morn.library.boot.exception.interpreter.ShiroExceptionInterpreter;
import morn.library.boot.exception.interpreter.ValidateExceptionInterpreter;
import morn.library.boot.exception.interpreter.ValidationExceptionInterpreter;
import morn.library.rest.RestMessage;
import morn.library.translate.Transfer;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.boot.exception.ApplicationMessageConverter;
import morn.library.boot.exception.DefaultApplicationMessageConverter;
import morn.library.boot.exception.DefaultExceptionInterpreterServiceImpl;
import morn.library.boot.exception.ExceptionProperties;
import morn.library.boot.exception.interpreter.ApplicationExceptionInterpreter;
import morn.library.exception.ApplicationException;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ExceptionInterpreter;
import morn.library.exception.ExceptionInterpreterService;
import morn.library.translate.TranslateConverter;
import morn.library.translate.Translator;

/**
 * 异常自动化配置
 */
@Configuration
@ConditionalOnClass(CacheManager.class)
@AutoConfigureAfter(TranslatorAutoConfiguration.class)
@EnableConfigurationProperties(ExceptionProperties.class)
@ConditionalOnProperty(prefix = "morn.exception", value = "enabled", havingValue = "true")
public class ExceptionAutoConfiguration {

  /**
   * 注册应用消息转换器
   *
   * <p>用于{@link Transfer}转{@link ApplicationMessage}</p>
   *
   * @param translator 翻译器
   * @return 应用消息转换器
   */
  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(Translator.class)
  public TranslateConverter<ApplicationMessage> applicationMessageTranslateConverter(
      Translator translator, ExceptionProperties exceptionProperties) {
    return new DefaultApplicationMessageConverter(translator, exceptionProperties);
  }

  /**
   * 注册应用消息转换器
   *
   * <p>用于{@link ApplicationMessage}和{@link RestMessage}互相转换</p>
   */
  @Bean
  @ConditionalOnMissingBean
  public ApplicationMessageConverter applicationMessageConverter() {
    return new ApplicationMessageConverter();
  }

  /**
   * 注册异常解释服务
   *
   * <p>按需调用{@link ExceptionInterpreter}，将{@link Exception}转为{@link ApplicationMessage}</p>
   */
  @Bean
  @ConditionalOnMissingBean
  public ExceptionInterpreterService exceptionInterpreterService(BeanCache beanCache) {
    return new DefaultExceptionInterpreterServiceImpl(beanCache);
  }

  /**
   * 注册应用异常解释器
   *
   * <p>{@link ApplicationException}转{@link ApplicationMessage}</p>
   *
   * @return 应用异常解释器
   */
  @Bean
  public ExceptionInterpreter applicationExceptionInterpreter() {
    return new ApplicationExceptionInterpreter();
  }

  /**
   * Shiro异常配置
   *
   * <p>Shiro相关异常转{@link ApplicationMessage}</p>
   */
  @Configuration
  @ConditionalOnClass({SecurityManager.class, ShiroExceptionInterpreter.class})
  public class ShiroConfiguration {

    /**
     * 注册Shiro异常解释器
     */
    @Bean
    public ExceptionInterpreter shiroExceptionInterpreter() {
      return new ShiroExceptionInterpreter();
    }
  }

  /**
   * 数据校验异常配置
   *
   * <p>数据校验相关异常转{@link ApplicationMessage}</p>
   */
  @Configuration
  @ConditionalOnClass({
      ValidateExceptionInterpreter.class,
      ValidationExceptionInterpreter.class,
      MethodValidateExceptionInterpreter.class})
  public class ValidateConfiguration {

    /**
     * 注册数据校验异常解释器
     */
    @Bean
    public ExceptionInterpreter validateExceptionInterpreter() {
      return new ValidateExceptionInterpreter();
    }

    /**
     * 注册数据校验异常解释器
     */
    @Bean
    public ExceptionInterpreter validationExceptionInterpreter() {
      return new ValidationExceptionInterpreter();
    }

    /**
     * 注册数据校验异常解释器
     */
    @Bean
    public ExceptionInterpreter methodValidateExceptionInterpreter() {
      return new MethodValidateExceptionInterpreter();
    }
  }
}

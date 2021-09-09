package morn.library.boot.autoconfigure;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morn.library.boot.json.JsonParser;
import morn.library.boot.json.support.FastJsonParser;
import morn.library.boot.json.support.JacksonParser;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * JSON自动化配置
 */
@Configuration
@ConditionalOnClass(JsonParser.class)
public class JsonAutoConfiguration {

  /**
   * FastJson自动化配置
   */
  @Configuration
  @ConditionalOnClass(JSON.class)
  public static class FastJsonAutoConfiguration {

    /**
     * 注册FastJson映射器
     */
    @Bean
    @ConditionalOnMissingBean
    public FastJsonParser fastJsonMapper() {
      return new FastJsonParser();
    }
  }

  /**
   * Jackson自动化配置
   */
  @Configuration
  @ConditionalOnClass(ObjectMapper.class)
  public static class JacksonAutoConfiguration {

    /**
     * 注册FastJson映射器
     */
    @Bean
    @ConditionalOnMissingBean
    public JacksonParser jacksonParser(ObjectMapper objectMapper) {
      return new JacksonParser(objectMapper);
    }
  }


  /**
   * Gson自动化配置  优化与增强json功能
   */

  @Configuration
  @ConditionalOnClass(GsonHttpMessageConverter.class)
  public static class GsonAutoConfiguration {

/*
    */
/**
     * 注册FastJson映射器
     *//*

    @Bean
    @ConditionalOnMissingBean
    public GsonJsonParser
*/

  }
}

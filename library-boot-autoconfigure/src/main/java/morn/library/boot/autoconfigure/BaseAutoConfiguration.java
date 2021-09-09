package morn.library.boot.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 基础自动化配置
 */
@Configuration
@PropertySource("classpath:default.properties")
public class BaseAutoConfiguration {

}

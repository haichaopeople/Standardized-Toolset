package morn.library.boot.autoconfigure;

import morn.library.bean.AnnotationFieldRegistry;
import morn.library.bean.BeanConfigurer;
import morn.library.boot.security.SecurityBCryptEncryption;
import morn.library.boot.security.SecurityBCryptMatcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import morn.library.boot.cipher.AESAlgorithms.AESAlgorithm;
import morn.library.boot.cipher.AESAlgorithms.ECBAlgorithm;
import morn.library.boot.cipher.AESAlgorithms.GCMAlgorithm;
import morn.library.boot.cipher.CipherProperties;
import morn.library.boot.cipher.MD5Algorithms.MD5Encryption;
import morn.library.boot.cipher.MD5Algorithms.MD5Matcher;
import morn.library.cipher.annotation.AlgorithmName;

/**
 * 数据加密自动化配置
 */
@Configuration
@EnableConfigurationProperties(CipherProperties.class)
public class CipherAutoConfiguration implements BeanConfigurer {

  /**
   * 注册加密算法注解
   *
   * @param registry 实例注解注册表
   */
  @Override
  public void addBeanAnnotations(AnnotationFieldRegistry registry) {
    registry.add(AlgorithmName.class);
  }

  /**
   * 注册AES加密算法
   */
  @Bean
  @ConditionalOnMissingBean
  public AESAlgorithm aesAlgorithm() {
    return new AESAlgorithm();
  }

  /**
   * 注册ECB加密算法
   */
  @Bean
  @ConditionalOnMissingBean
  public ECBAlgorithm ecbAlgorithm(CipherProperties properties) {
    return new ECBAlgorithm(properties);
  }

  /**
   * 注册GCM加密算法
   */
  @Bean
  @ConditionalOnMissingBean
  public GCMAlgorithm gcmAlgorithm(CipherProperties properties) {
    return new GCMAlgorithm(properties);
  }

  /**
   * 注册MD5加密算法
   */
  @Bean
  @ConditionalOnMissingBean
  public MD5Encryption md5Encryption() {
    return new MD5Encryption();
  }

  /**
   * 注册MD5校验算法
   */
  @Bean
  @ConditionalOnMissingBean
  public MD5Matcher md5Matcher() {
    return new MD5Matcher();
  }

  /**
   * SpringSecurityBCrypt加密配置
   */
  @Configuration
  @ConditionalOnClass({BCryptPasswordEncoder.class, SecurityBCryptMatcher.class})
  public static class SecurityBCryptConfiguration {

    /**
     * 注册BCrypt加密
     */
    @Bean
    @ConditionalOnMissingBean
    public SecurityBCryptEncryption securityBCryptEncryption() {
      return new SecurityBCryptEncryption();
    }

    /**
     * 注册BCrypt校验
     */
    @Bean
    @ConditionalOnMissingBean
    public SecurityBCryptMatcher securityBCryptMatcher() {
      return new SecurityBCryptMatcher();
    }
  }
}

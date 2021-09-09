package morn.library.boot.security;

import static morn.library.cipher.support.AlgorithmNameConstants.SPRING_B_CRYPT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import morn.library.cipher.AlgorithmMatcher;
import morn.library.cipher.annotation.AlgorithmName;
import morn.library.cipher.support.SimpleAlgorithmHolder;

/**
 * BCrypt加密
 *
 * <p>基于SpringSecurity加密，格式参考SpringSecurity官方文档
 */
@Slf4j
@AlgorithmName(SPRING_B_CRYPT)
public class SecurityBCryptMatcher extends SimpleAlgorithmHolder implements AlgorithmMatcher {

  @Override
  public boolean matches(CharSequence rawText, CharSequence encodedText) {
    try {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder()
          .matches(rawText, encodedText.toString());
    } catch (Exception e) {
      log.warn(e.getMessage(), e);
      return false;
    }
  }
}

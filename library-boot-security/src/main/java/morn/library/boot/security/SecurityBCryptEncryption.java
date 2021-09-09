package morn.library.boot.security;

import static morn.library.cipher.support.AlgorithmNameConstants.SPRING_B_CRYPT;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import morn.library.cipher.AlgorithmEncryption;
import morn.library.cipher.annotation.AlgorithmName;
import morn.library.cipher.support.SimpleAlgorithmHolder;

/**
 * BCrypt加密
 *
 * <p>基于SpringSecurity加密，格式参考SpringSecurity官方文档
 *
 */
@AlgorithmName(SPRING_B_CRYPT)
public class SecurityBCryptEncryption extends SimpleAlgorithmHolder implements AlgorithmEncryption {

  @Override
  public String encrypt(CharSequence text) {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(text);
  }
}

package morn.library.boot.cipher;

import java.util.Objects;

import morn.library.cipher.AlgorithmEncryption;
import morn.library.cipher.AlgorithmMatcher;
import morn.library.cipher.annotation.AlgorithmName;
import morn.library.cipher.support.AlgorithmNameConstants;
import morn.library.cipher.support.SimpleAlgorithmHolder;
import org.springframework.util.DigestUtils;

/**
 * MD5算法
 */
public class MD5Algorithms {

  private MD5Algorithms() {
  }

  /**
   * 加密
   *
   * @param text 明文
   * @return 密文
   */
  public static String encrypt(CharSequence text) {
    byte[] bytes = text.toString().getBytes();
    return DigestUtils.appendMd5DigestAsHex(bytes, new StringBuilder()).toString();
  }

  /**
   * MD5加密
   */
  @AlgorithmName(AlgorithmNameConstants.MD5)
  public static class MD5Encryption extends SimpleAlgorithmHolder implements AlgorithmEncryption {

    @Override
    public String encrypt(CharSequence text) {
      return MD5Algorithms.encrypt(text);
    }
  }

  /**
   * MD5校验
   */
  @AlgorithmName(AlgorithmNameConstants.MD5)
  public static class MD5Matcher extends SimpleAlgorithmHolder implements AlgorithmMatcher {

    @Override
    public boolean matches(CharSequence rawText, CharSequence encodedText) {
      String encrypt = encrypt(rawText);
      return Objects.equals(encrypt, encodedText.toString());
    }
  }
}

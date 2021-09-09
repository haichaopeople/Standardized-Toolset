package morn.library.cipher;

/**
 * 解密者
 *
 */
@FunctionalInterface
public interface Decryption {

  /**
   * 解密
   */
  String decrypt(CharSequence text);
}

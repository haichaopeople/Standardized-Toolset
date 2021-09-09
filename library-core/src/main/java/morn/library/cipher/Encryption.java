package morn.library.cipher;

/**
 * 加密者
 */
@FunctionalInterface
public interface Encryption {

  /**
   * 加密
   */
  String encrypt(CharSequence text);
}

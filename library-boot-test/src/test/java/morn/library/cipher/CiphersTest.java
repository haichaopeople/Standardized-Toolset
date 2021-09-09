package morn.library.cipher;

import lombok.extern.slf4j.Slf4j;
import morn.library.cipher.support.AlgorithmBuilder;
import morn.library.cipher.support.AlgorithmModeConstants;
import morn.library.cipher.support.AlgorithmNameConstants;
import morn.library.cipher.util.Ciphers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 数据加密单元测试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CiphersTest {

  /**
   * 原始文本
   */
  private static final String PLAIN_TEXT = "password";

  /**
   * ECB测试
   */
  @Test
  public void testAESForECB() {
    Algorithm algorithm = AlgorithmBuilder.withName(AlgorithmNameConstants.AES).mode(AlgorithmModeConstants.AES_ECB_PKCS5PADDING).build();
    String encrypt = Ciphers.encrypt(algorithm, PLAIN_TEXT);
    Assert.assertNotEquals(PLAIN_TEXT, encrypt);
    String decrypt = Ciphers.decrypt(algorithm, encrypt);
    Assert.assertEquals(PLAIN_TEXT, decrypt);
    boolean matches = Ciphers.matches(algorithm, PLAIN_TEXT, encrypt);
    Assert.assertTrue(matches);
  }

  /**
   * GCM测试
   */
  @Test
  public void testAESForGCM() {
    Algorithm algorithm = AlgorithmBuilder.withName(AlgorithmNameConstants.AES).mode(AlgorithmModeConstants.AES_GCM_NO_PADDING).build();
    String encrypt = Ciphers.encrypt(algorithm, PLAIN_TEXT);
    Assert.assertNotEquals(PLAIN_TEXT, encrypt);
    String decrypt = Ciphers.decrypt(algorithm, encrypt);
    Assert.assertEquals(PLAIN_TEXT, decrypt);
    boolean matches = Ciphers.matches(algorithm, PLAIN_TEXT, encrypt);
    Assert.assertTrue(matches);
  }

  @Test
  public void encryptBCrypt() {
    String password = Ciphers.encrypt(AlgorithmNameConstants.SPRING_B_CRYPT, PLAIN_TEXT);
    Assert.assertNotEquals(PLAIN_TEXT, password);
    boolean matches = Ciphers.matches(AlgorithmNameConstants.SPRING_B_CRYPT, PLAIN_TEXT,
        "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG");
    Assert.assertTrue(matches);
  }

  @Test
  public void encryptMD5() {
    String password = Ciphers.encrypt(AlgorithmNameConstants.MD5, PLAIN_TEXT);
    Assert.assertNotEquals(PLAIN_TEXT, password);
    boolean matches = Ciphers.matches(AlgorithmNameConstants.MD5, PLAIN_TEXT, "5f4dcc3b5aa765d61d8327deb882cf99");
    Assert.assertTrue(matches);
  }
}
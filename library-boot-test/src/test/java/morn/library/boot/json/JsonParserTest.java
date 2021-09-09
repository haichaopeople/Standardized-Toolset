package morn.library.boot.json;

import static morn.library.boot.json.constant.JsonParserConstants.FAST_JSON;
import static morn.library.boot.json.constant.JsonParserConstants.JACKSON;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import morn.library.core.CriteriaMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.TestContextManager;
import org.springframework.util.CollectionUtils;
import morn.library.boot.json.util.JsonParsers;
import morn.library.test.TestUser;

/**
 * JSON解析器单元测试
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@RunWith(Parameterized.class)
public class JsonParserTest {

  /**
   * JSON解析器类型
   */
  private final String parserType;

  /**
   * JSON解析器
   */
  private JsonParser parser;

  @Parameters
  public static Iterable<Object[]> testData() {
    return Arrays.asList(new Object[][]{
        {FAST_JSON}, {JACKSON}
    });
  }

  @Before
  public void setUp() throws Exception {
    TestContextManager testContextManager = new TestContextManager(getClass());
    testContextManager.prepareTestInstance(this);
    this.parser = JsonParsers.getParser(parserType);
  }

  @Test
  public void deserializeToMap() {
    CriteriaMap user = parser.convertMap(Constant.USER1_STRING);
    log.info("{}",user);
//    Assert.assertNotNull(user);
//    Assert.assertEquals(Constant.USER1.getId(), user.getLong("id"));
//    Assert.assertEquals(Constant.USER1.getUsername(), user.getString("username"));
  }

  @Test
  public void deserializeToObject1() {
    TestUser user = parser.convert(Constant.USER2_STRING, TestUser.class);
    log.info("{}",user);
    Assert.assertNotNull(user);
    Assert.assertEquals(Constant.USER2.getId(), user.getId());
    Assert.assertEquals(Constant.USER2.getUsername(), user.getUsername());
  }

  @Test
  public void deserializeToObject2() {
    List<TestUser> users = parser.convert(Constant.USERS_STRING, new ParameterizedTypeReference<List<TestUser>>() {});
    log.info("{}",JsonParsers.convertMap(users));
    Assert.assertFalse(CollectionUtils.isEmpty(users));
    TestUser user1 = users.get(0);
    Assert.assertEquals(Long.valueOf(1), user1.getId());
    Assert.assertEquals("Caramel", user1.getUsername());
    TestUser user2 = users.get(1);
    Assert.assertEquals(Long.valueOf(2), user2.getId());
    Assert.assertEquals("Mocha", user2.getUsername());
  }

  @Test
  public void serializeToString() {
    String userString = parser.convertStr(Constant.USER1);
    TestUser user1 = parser.convert(userString, TestUser.class);
    Assert.assertEquals(Long.valueOf(1), user1.getId());
    Assert.assertEquals("Caramel", user1.getUsername());
  }
}
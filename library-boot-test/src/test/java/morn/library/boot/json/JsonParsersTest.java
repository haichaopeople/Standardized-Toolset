package morn.library.boot.json;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import morn.library.core.CriteriaMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import morn.library.boot.json.util.JsonParsers;
import morn.library.test.TestUser;

/**
 * JSON解析工具类单元测试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonParsersTest {

  @Test
  public void deserializeToMap() {
    CriteriaMap user = JsonParsers.convertMap(Constant.USER1_STRING);
    Assert.assertNotNull(user);
    Assert.assertEquals(Constant.USER1.getId(), user.getLong("id"));
    Assert.assertEquals(Constant.USER1.getUsername(), user.getString("username"));
  }

  @Test
  public void deserializeToObject1() {
    TestUser user = JsonParsers.convertObject(Constant.USER2_STRING, TestUser.class);
    Assert.assertNotNull(user);
    Assert.assertEquals(Constant.USER2.getId(), user.getId());
    Assert.assertEquals(Constant.USER2.getUsername(), user.getUsername());
  }

  @Test
  public void deserializeToObject2() {
    List<TestUser> users = JsonParsers
        .convertObject(Constant.USERS_STRING, new ParameterizedTypeReference<List<TestUser>>() {
        });
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
    String userString = JsonParsers.convertStr(Constant.USER1);
    TestUser user1 = JsonParsers.convertObject(userString, TestUser.class);
    Assert.assertEquals(Long.valueOf(1), user1.getId());
    Assert.assertEquals("Caramel", user1.getUsername());
  }
}
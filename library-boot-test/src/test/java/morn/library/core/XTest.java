package morn.library.core;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import morn.library.test.TestUser;

/**
 * 任意类型单元测试
 */
@RunWith(JUnit4.class)
public class XTest {

  @Test
  public void asCollection() {
    ArrayList<String> list = new ArrayList<>();
    list.add("foo");
    list.add("bar");
    Generic generic = Generic.from(list);
    Collection<String> objects = generic.asCollection();
    Assert.assertEquals("Test x, cast success.", list, objects);
    Assert.assertTrue("Test x, asCollection success.", objects.containsAll(list));
  }

  @Test
  public void asLong() {
    Generic generic = Generic.from(1L);
    Long longNumber = generic.asLong();
    Assert.assertEquals(Long.valueOf(1L), longNumber);
  }

  @Test
  public void asString() {
    Generic generic = Generic.from("foo");
    String s = generic.asString();
    Assert.assertEquals("foo", s);
  }

  @Test
  public void value() {
    TestUser testUser = new TestUser();
    testUser.setUsername("timely-rain");
    Generic generic = Generic.from(testUser);
    TestUser value = generic.value(TestUser.class);
    Assert.assertEquals(testUser, value);
    Assert.assertEquals("timely-rain", value.getUsername());
  }
}
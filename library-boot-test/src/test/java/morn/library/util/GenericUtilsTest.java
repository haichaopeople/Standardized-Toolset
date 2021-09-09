package morn.library.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import morn.library.test.TestAnnotationBeans;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 类型工具单元测试
 *
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenericUtilsTest {

  @Test
  public void testCatToCat1() {
    TestAnnotationBeans.Cat source = new TestAnnotationBeans.RagDoll();
    TestAnnotationBeans.RagDoll target = GenericUtils.castFrom(source);
    Assert.assertNotNull(target);
  }

  @Test
  public void testCatToCat2() {
    TestAnnotationBeans.Cat source = new TestAnnotationBeans.RagDoll();
    TestAnnotationBeans.RagDoll target = GenericUtils.castFrom(source, TestAnnotationBeans.RagDoll.class);
    Assert.assertNotNull(target);
  }

  @Test
  public void testCatToDog() {
    TestAnnotationBeans.Cat source = new TestAnnotationBeans.BritishShortHair();
    try {
      TestAnnotationBeans.Dog target = GenericUtils.castFrom(source);
      Assert.assertNotNull(target);
      Assert.fail();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Test
  public void cloneNull() {
    Object o = GenericUtils.safeClone(null);
    Assert.assertNull(o);
  }

  @Test
  public void cloneCloneable() {
    CloneableObject source = new CloneableObject();
    source.setName("cloneCloneable");
    CloneableObject target = GenericUtils.safeClone(source);
    Assert.assertNotNull(target);
    Assert.assertEquals(source.getName(), target.getName());
    Assert.assertNotEquals(source, target);
  }

  @Test
  public void cloneCloneableHasException() {
    Cloneable source = new Cloneable() {
      @Override
      protected Cloneable clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("throws exception");
      }
    };
    Cloneable target = GenericUtils.safeClone(source);
    Assert.assertNotNull(target);
    Assert.assertEquals(source, target);
  }

  @Test
  public void cloneCloneableNoMethod() {
    CloneableObjectNoMethod source = new CloneableObjectNoMethod();
    source.setName("cloneCloneableNoMethod");
    CloneableObjectNoMethod target = GenericUtils.safeClone(source);
    Assert.assertNotNull(target);
    Assert.assertEquals(source, target);
  }

  @Test
  public void cloneNotCloneable() {
    NotCloneableObject source = new NotCloneableObject();
    source.setName("cloneNotCloneable");
    NotCloneableObject target = GenericUtils.safeClone(source);
    Assert.assertEquals(source, target);
  }

  /**
   * 可克隆测试类
   */
  @Getter
  @Setter
  public class CloneableObject implements Cloneable {

    private String name;

    @Override
    protected CloneableObject clone() throws CloneNotSupportedException {
      super.clone();
      CloneableObject cloneableObject = new CloneableObject();
      cloneableObject.setName(name);
      return cloneableObject;
    }
  }

  /**
   * 不可克隆测试类
   */
  @Getter
  @Setter
  public class CloneableObjectNoMethod implements Cloneable {

    private String name;
  }

  /**
   * 不可克隆测试类
   */
  @Getter
  @Setter
  public class NotCloneableObject {

    private String name;
  }
}
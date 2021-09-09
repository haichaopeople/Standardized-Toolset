package morn.library.test;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.annotation.Source;
import org.springframework.stereotype.Component;
import morn.library.data.persistent.AddProcessor;
import morn.library.data.persistent.AddValidator;
import morn.library.data.persistent.DeleteProcessor;
import morn.library.data.persistent.UpdateProcessor;
import morn.library.data.persistent.UpdateValidator;

/**
 * 部门测试实例
 */
@Slf4j
@Component
public class TestDepartmentBeans {

  /**
   * 新增验证
   */
  @Component
  @Source(TestDepartment.class)
  public static class DepartmentAddValidator implements AddValidator<TestDepartment> {

    @Override
    public boolean validate(TestDepartment source) {
      if (Objects.equals(source.getId(), 1L)) {
        log.warn("部门编号重复：{}", source.getId());
        return false;
      }
      return true;
    }
  }

  /**
   * 新增处理
   */
  @Component
  @Source(TestDepartment.class)
  public static class DepartmentAddProcessor implements AddProcessor<TestDepartment> {

    @Override
    public void handle(TestDepartment data) {
      data.setState(1);
    }
  }

  /**
   * 删除处理
   */
  @Component
  @Source(TestDepartment.class)
  public static class DepartmentDeleteProcessor implements DeleteProcessor<TestDepartment> {

    @Override
    public void handle(TestDepartment data) {
      data.setState(0);
    }
  }

  /**
   * 更新验证
   */
  @Component
  @Source(TestDepartment.class)
  public static class DepartmentUpdateValidator implements UpdateValidator<TestDepartment> {

    @Override
    public boolean validate(TestDepartment source) {
      if (Objects.equals(source.getId(), 1L)) {
        log.warn("部门编号不存在：{}", source.getId());
        return false;
      }
      return true;
    }
  }

  /**
   * 更新处理
   */
  @Component
  @Source(TestDepartment.class)
  public static class DepartmentUpdateProcessor implements UpdateProcessor<TestDepartment> {

    @Override
    public void handle(TestDepartment data) {
      data.setState(2);
    }
  }
}

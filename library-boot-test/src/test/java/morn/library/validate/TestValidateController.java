package morn.library.validate;

import morn.library.data.group.Add;
import morn.library.data.group.Update;
import morn.library.rest.RestModel;
import morn.library.rest.annotation.RestResponse;
import morn.library.test.TestUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据校验测试控制器
 */
@RestResponse
@RestController
@RequestMapping("test/validate")
public class TestValidateController {

  /**
   * 新增用户
   *
   * @param restModel REST模型
   */
  @PostMapping
  public void add(@Validated(Add.class) @RequestBody RestModel<TestUser> restModel) {
  }

  /**
   * 更新用户
   *
   * @param restModel REST模型
   */
  @PutMapping
  public void update(@Validated(Update.class) @RequestBody RestModel<TestUser> restModel) {
  }
}

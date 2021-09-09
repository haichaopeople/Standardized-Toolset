package morn.library.boot.log;

import java.util.Map;

import morn.library.log.OperateAction;
import morn.library.log.OperateArguments;
import morn.library.log.OperateGroup;
import morn.library.rest.RestMessage;
import org.springframework.stereotype.Component;
import morn.library.boot.rest.RestBuilders;

/**
 * 测试用户控制器
 */
@Component
@OperateGroup(value = "用户管理", args = "1")
public class TestOperateController {

  @OperateAction(value = "新增用户：{0}", args = "2")
  public RestMessage addUser(Map<String, Object> user) {
    OperateArguments.add(user.get("username"));
    return RestBuilders.successMessage();
  }

  @OperateAction(value = "更新用户：{1}，年龄：{0}", args = "3")
  public Map<String, Object> updateUser(Map<String, Object> user) {
    OperateArguments.add(user.get("age"));
    OperateArguments.add(user.get("username"));
    throw new RuntimeException("异常测试");
  }
}

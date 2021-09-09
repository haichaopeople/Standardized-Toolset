package morn.library.boot.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import morn.library.test.TestUser;

/**
 * 用户测试控制器
 */
@RestController
@RequestMapping("test/user")
public class TestUserController extends DisplayableController<TestUser, Long, TestUserService> {

}

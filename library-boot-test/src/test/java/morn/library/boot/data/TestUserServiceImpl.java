package morn.library.boot.data;

import morn.library.boot.data.jpa.crud.impl.SimpleDisplayableService;
import org.springframework.stereotype.Service;
import morn.library.test.TestUser;

/**
 * 用户测试服务实现
 */
@Service
public class TestUserServiceImpl extends
        SimpleDisplayableService<TestUser, Long, TestUserRepository> implements TestUserService {

}

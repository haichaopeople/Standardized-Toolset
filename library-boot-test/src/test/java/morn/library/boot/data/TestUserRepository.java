package morn.library.boot.data;

import morn.library.boot.data.jpa.crud.SpecificationRepository;
import org.springframework.stereotype.Repository;
import morn.library.test.TestUser;

/**
 * 测试用户访问对象
 */
@Repository
public interface TestUserRepository extends SpecificationRepository<TestUser, Long> {

}

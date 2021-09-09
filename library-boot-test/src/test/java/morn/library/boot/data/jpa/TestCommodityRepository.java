package morn.library.boot.data.jpa;

import morn.library.boot.data.jpa.crud.SpecificationRepository;
import org.springframework.stereotype.Repository;
import morn.library.test.TestCommodity;

/**
 * 测试商品访问对象
 */
@Repository
public interface TestCommodityRepository extends SpecificationRepository<TestCommodity, Long> {

}

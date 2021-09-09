package morn.library.boot.security;

import static morn.library.util.AnnotationFeatureUtils.WILDCARD;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import morn.library.bean.annotation.Tag;
import morn.library.log.OperateMeta;
import morn.library.log.Operation;
import morn.library.log.OperationAdapter;

/**
 * 安全操作适配器
 *
 */
@Order
@Tag(WILDCARD)
public class SecurityOperationAdapter implements OperationAdapter {

  @Override
  public void adaption(OperateMeta meta, Operation operation) {
    // 获取操作人
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      return;
    }
    operation.setOperatorName(authentication.getName());
  }
}

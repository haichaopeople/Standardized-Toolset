package morn.library.boot.log;

import java.util.Date;

import morn.library.bean.annotation.Tag;
import morn.library.log.OperateMeta;
import morn.library.log.Operation;
import morn.library.log.OperationAdapter;
import morn.library.util.AnnotationFeatureUtils;
import org.springframework.core.annotation.Order;

/**
 * 基础操作适配器
 */
@Order
@Tag(AnnotationFeatureUtils.WILDCARD)
public class SimpleOperationAdapter implements OperationAdapter {

  @Override
  public void adaption(OperateMeta meta, Operation operation) {
    operation.setSuccess(meta.isSuccess());
    operation.setModule(meta.getGroupName());
    operation.setName(meta.getActionName());
    operation.setDate(new Date(meta.getStartTime()));
    operation.setDuration((int) meta.getDuration());
  }
}

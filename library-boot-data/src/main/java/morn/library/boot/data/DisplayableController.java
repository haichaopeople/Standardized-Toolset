package morn.library.boot.data;

import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.List;

import morn.library.log.OperateAction;
import morn.library.log.OperateArguments;
import morn.library.rest.RestMessage;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import morn.library.boot.rest.RestBuilders;

/**
 * 显示/隐藏控制器
 */
public class DisplayableController<T, I extends Serializable, S extends CrudService<T, I> & DisplayableService<T, I>> extends
    CrudController<T, I, S> {

  /**
   * 显示
   */
  @ApiOperation("显示")
  @OperateAction("display")
  @PutMapping("display")
  public RestMessage display(@RequestBody List<I> ids) {
    OperateArguments.add(ids);
    service().displayAll(ids);
    return RestBuilders.successMessage();
  }

  /**
   * 隐藏
   */
  @ApiOperation("隐藏")
  @OperateAction("hide")
  @PutMapping("hide")
  public RestMessage hide(@RequestBody List<I> ids) {
    OperateArguments.add(ids);
    service().hideAll(ids);
    return RestBuilders.successMessage();
  }
}

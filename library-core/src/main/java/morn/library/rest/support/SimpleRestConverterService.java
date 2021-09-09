package morn.library.rest.support;

import morn.library.core.support.AbstractBeanConverterService;
import morn.library.rest.RestConverter;
import morn.library.rest.RestConverterService;

/**
 * 默认REST消息转换服务
 */
public class SimpleRestConverterService extends AbstractBeanConverterService implements
        RestConverterService {

  public SimpleRestConverterService() {
    super(RestConverter.class);
  }
}

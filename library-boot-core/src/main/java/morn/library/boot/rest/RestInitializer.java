package morn.library.boot.rest;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import morn.library.bean.BeanCache;
import morn.library.translate.Translator;

/**
 * REST初始化
 */
@RequiredArgsConstructor
public class RestInitializer {

  /**
   * 实例缓存
   */
  private final BeanCache beanCache;

  /**
   * 翻译器
   */
  private final Translator translator;

  /**
   * REST配置项
   */
  private final RestProperties restProperties;

  @PostConstruct
  public void initRest() {
    RestBuilder.initialize(beanCache, translator, restProperties);
  }
}

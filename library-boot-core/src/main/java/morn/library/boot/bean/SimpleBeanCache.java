package morn.library.boot.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import morn.library.bean.AnnotationFeature;
import morn.library.bean.BeanCache;
import morn.library.bean.BeanHolder;
import morn.library.bean.FunctionHolder;
import morn.library.constant.ApplicationConstants;
import morn.library.util.AnnotationFeatureUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 默认标识的实例缓存
 */
@CacheConfig(cacheManager = ApplicationConstants.Caches.CACHE_MANAGER_NAME_SIMPLE)
public class SimpleBeanCache implements BeanCache {

  /**
   * 实例持有者
   */
  private final List<BeanHolder<?>> holders = Collections
      .synchronizedList(new ArrayList<>());

  @Override
  public <T> void cache(BeanHolder<T> holder) {
    holders.add(holder);
  }

  @Cacheable(value = ApplicationConstants.Caches.BEAN_DEFAULT, key = "#limitIdentify.toString()")
  @Override
  public <T> List<T> beans(Class<T> suitType, AnnotationFeature limitIdentify) {
    Stream<BeanHolder<T>> stream = beanHolderStream(suitType, limitIdentify);
    // 提取实例集合
    return stream.map(BeanHolder::getBean).collect(Collectors.toList());
  }

  @Cacheable(value = ApplicationConstants.Caches.BEAN_HOLDER, key = "#limitIdentify.toString()")
  @Override
  public <T> List<BeanHolder<T>> beanHolders(Class<T> suitType,
      AnnotationFeature limitIdentify) {
    return beanHolderStream(suitType, limitIdentify).collect(Collectors.toList());
  }

  @Override
  public List<FunctionHolder> functions(AnnotationFeature beanIdentify,
                                        AnnotationFeature functionIdentify) {
    return functionHolderStream(beanIdentify, functionIdentify).collect(Collectors.toList());
  }

  @Override
  public <T> List<FunctionHolder> functions(List<BeanHolder<T>> holders,
      AnnotationFeature functionIdentify) {
    return functionHolderStream(holders.stream(), functionIdentify).collect(Collectors.toList());
  }

  /**
   * 获取实例持有流
   *
   * @param limitType 限制类型
   * @param limitIdentify 限制标识
   * @param <T> 实例类型
   * @return 实例持有流
   */
  @SuppressWarnings("unchecked")
  private <T> Stream<BeanHolder<T>> beanHolderStream(Class<T> limitType,
      AnnotationFeature limitIdentify) {
    return holders.stream().filter(
        holder -> AnnotationFeatureUtils.isInstance(holder.getBean().getClass(), limitType))
        .filter(holder -> AnnotationFeatureUtils.isSuitable(holder, limitIdentify))
        .map(BeanHolder.class::cast);
  }

  /**
   * 获取函数持有流
   *
   * @param beanIdentify 实例标识
   * @param functionIdentify 函数标识
   * @return 函数持有流
   */
  private Stream<FunctionHolder> functionHolderStream(AnnotationFeature beanIdentify,
      AnnotationFeature functionIdentify) {
    return functionHolderStream(beanHolderStream(null, beanIdentify), functionIdentify);
  }

  /**
   * 获取函数持有流
   *
   * @param stream 实例持有者流
   * @param functionIdentify 函数标识
   * @return 函数持有流
   */
  private <T> Stream<FunctionHolder> functionHolderStream(Stream<BeanHolder<T>> stream,
      AnnotationFeature functionIdentify) {
    return stream.flatMap(holder -> holder.getFunctionHolders().stream())
        .filter(holder -> AnnotationFeatureUtils.isSuitable(holder, functionIdentify));
  }
}

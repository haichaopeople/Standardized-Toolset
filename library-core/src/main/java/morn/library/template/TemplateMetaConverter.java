package morn.library.template;

import morn.library.core.BeanConverter;

/**
 * 模板元数据转换器
 */
@FunctionalInterface
public interface TemplateMetaConverter<T> extends BeanConverter<TemplateMeta, T> {

}

package morn.library.boot.template.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import morn.library.boot.template.TemplateConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 开启模板转义
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(TemplateConfiguration.class)
public @interface EnableTemplate {

}

package morn.library.boot.notify.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import morn.library.boot.notify.NotifyConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 开启系统通知
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(NotifyConfiguration.class)
public @interface EnableNotify {

}

package morn.library.boot.netty.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import morn.library.boot.netty.config.NettyServerConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 开启Netty服务端
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(NettyServerConfiguration.class)
public @interface EnableNettyServer {

}

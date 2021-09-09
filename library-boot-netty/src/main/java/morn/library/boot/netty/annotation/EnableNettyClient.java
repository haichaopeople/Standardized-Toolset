package morn.library.boot.netty.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import morn.library.boot.netty.config.NettyClientConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 开启Netty客户端
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(NettyClientConfiguration.class)
public @interface EnableNettyClient {

}

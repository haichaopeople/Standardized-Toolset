package morn.library.boot.notify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通知配置项
 */
@Getter
@Setter
@ConfigurationProperties("morn.notify")
public class NotifyProperties {

}

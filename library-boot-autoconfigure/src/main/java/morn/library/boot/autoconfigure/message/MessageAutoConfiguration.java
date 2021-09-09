package morn.library.boot.autoconfigure.message;

import morn.library.bean.AnnotationFieldRegistry;
import morn.library.bean.AnnotationFieldType;
import morn.library.bean.BeanConfigurer;
import morn.library.boot.message.BroadcastMessage;
import morn.library.boot.message.annotation.MessageTopic;
import morn.library.boot.message.annotation.MessageType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

/**
 * 消息自动化配置
 */
@Configuration
@ConditionalOnClass({Message.class, BroadcastMessage.class})
public class MessageAutoConfiguration implements BeanConfigurer {

  @Override
  public void addBeanAnnotations(AnnotationFieldRegistry registry) {
    registry.add(MessageTopic.class);
  }

  @Override
  public void addFunctionAnnotations(AnnotationFieldRegistry registry) {
    registry.add(MessageType.class, AnnotationFieldType.NAME);
  }
}

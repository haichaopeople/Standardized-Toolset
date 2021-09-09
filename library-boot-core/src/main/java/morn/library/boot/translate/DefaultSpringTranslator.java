package morn.library.boot.translate;

import java.util.Locale;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.BeanCache;
import morn.library.translate.Transfer;
import morn.library.translate.TranslateConverter;
import morn.library.translate.Translator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 默认Spring翻译器
 */
@Slf4j
public class DefaultSpringTranslator implements Translator {

  /**
   * Spring国际化
   */
  private final MessageSource messageSource;

  /**
   * 实例缓存
   */
  private final BeanCache beanCache;

  public DefaultSpringTranslator(MessageSource messageSource,
      BeanCache beanCache) {
    this.messageSource = messageSource;
    this.beanCache = beanCache;
  }

  @Override
  public String translate(String name, Object... args) {
    return messageSource.getMessage(name, args, name, LocaleContextHolder.getLocale());
  }

  @Override
  public String translate(String name, Object[] args, String defaultMessage) {
    return messageSource.getMessage(name, args, defaultMessage, LocaleContextHolder.getLocale());
  }

  @Override
  public String translate(Locale locale, String name, Object... args) {
    return messageSource.getMessage(name, args, name, locale);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T translate(Transfer transfer, Class<T> cls) {
    TranslateConverter<T> translateConverter = beanCache.targetBean(TranslateConverter.class, cls);
    if (Objects.isNull(translateConverter)) {
      log.debug("无法获取作用于'{}'的翻译器", cls.getSimpleName());
      return null;
    }
    return translateConverter.convert(transfer);
  }
}

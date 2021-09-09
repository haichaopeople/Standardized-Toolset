package morn.library.boot.translate;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import morn.library.translate.Translator;
import morn.library.translate.Translators;

/**
 * 翻译初始化
 */
@RequiredArgsConstructor
public class TranslateInitializer {

  /**
   * 翻译器
   */
  private final Translator translator;

  @PostConstruct
  public void initialize() {
    Translators.initialize(translator);
  }
}

package morn.library.boot.exception;

import lombok.RequiredArgsConstructor;
import morn.library.bean.annotation.Target;
import morn.library.exception.ApplicationMessage;
import morn.library.exception.ApplicationMessages;
import morn.library.translate.Transfer;
import morn.library.translate.TranslateConverter;
import morn.library.translate.Translator;
import morn.library.translate.Translators;
import org.springframework.util.StringUtils;

/**
 * 默认应用消息转换器
 */
@RequiredArgsConstructor
@Target(ApplicationMessage.class)
public class DefaultApplicationMessageConverter implements TranslateConverter<ApplicationMessage> {

    /**
     * 翻译器
     */
    private final Translator translator;

    /**
     * 异常配置项
     */
    private final ExceptionProperties properties;

    @Override
    public ApplicationMessage convert(Transfer transfer) {
        String transferName = StringUtils.isEmpty(transfer.getName()) ? ApplicationMessages.FAILURE_CODE : transfer.getName();
        // 格式化国际消息
        String messageCode = Translators.formatCode(properties.getPrefix(), transferName, properties.getMessageSuffix());
        // 格式化国际解决方案
        String solutionCode = Translators.formatCode(properties.getPrefix(), transferName, properties.getSolutionSuffix());
        // 格式化国际消息编码
        String codingCode = Translators.formatCode(properties.getPrefix(), transferName, properties.getCodeSuffix());

        // 翻译
        String message = translator.translate(messageCode, transfer.getArgs(), transfer.getDefaultMessage());
        String solution = translator.translate(solutionCode, transfer.getArgs(), transfer.getDefaultMessage());
        String code = translator.translate(codingCode, transfer.getArgs(), "0");
        // 构建应用消息
        return ApplicationMessages.buildMessage(code, message, solution);
    }
}

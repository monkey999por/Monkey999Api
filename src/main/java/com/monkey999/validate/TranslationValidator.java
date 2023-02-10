package com.monkey999.validate;

import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.validate.TranslationValidateResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

@Component
public class TranslationValidator {
    public TranslationValidateResult validate(TranslationReq req) {

        // 空の場合はエラー
        if (!StringUtils.hasText(req.getText())) {
            return reject();
        }

        // 文字数制限 3000バイト
        if (req.getText().getBytes(StandardCharsets.UTF_8).length > 3000) {
            return reject();
        }

        // no problem
        return resolve();
    }

    private TranslationValidateResult reject() {
        return new TranslationValidateResult() {{
            noProblem = false;
        }};
    }

    private TranslationValidateResult resolve() {
        return new TranslationValidateResult() {{
            noProblem = true;
        }};
    }
}

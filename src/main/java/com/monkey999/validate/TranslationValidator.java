package com.monkey999.validate;

import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.validate.TranslationValidateResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class TranslationValidator {
    public TranslationValidateResult validate(TranslationReq req) {

        if (! StringUtils.hasText(req.getText())) {
            return new TranslationValidateResult() {{
                noProblem = false;
            }};
        }

        // no problem
        return new TranslationValidateResult() {{
            noProblem = true;
        }};

    }

    ;
}

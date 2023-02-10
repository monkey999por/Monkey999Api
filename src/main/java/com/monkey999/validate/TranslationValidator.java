package com.monkey999.validate;

import com.monkey999.ent.interfaces.translation.TranslationReq;
import com.monkey999.ent.validate.TranslationValidateResult;
import org.springframework.stereotype.Component;

@Component
public class TranslationValidator {
    public TranslationValidateResult validate(TranslationReq req) {
        var result = new TranslationValidateResult();
        result.noProblem = true;
        return result;
    }

    ;
}

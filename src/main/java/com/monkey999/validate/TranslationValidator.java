package com.monkey999.validate;

import com.monkey999.ent.validate.TranslationValidateResult;
import org.springframework.stereotype.Component;

@Component
public class TranslationValidator {
    public TranslationValidateResult validate() {
        var result = new TranslationValidateResult();
        result.noProblem = true;
        return result;
    }

    ;
}

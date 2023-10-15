package com.github.atmsubcase.core.model;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.Validate;

/**
 * Value object. Models an identifier for a person holding a {@linkplain Account}.
 */
@Value
public class AccountHolder {

    public static AccountHolder of(String personId) {
        return AccountHolder.builder()
                .personId(personId)
                .build();
    }

    String personId;

    @Builder
    private AccountHolder(String personId) {
        // just an example, this obviously must be more
        this.personId = Validate.notBlank(personId);
    }
}

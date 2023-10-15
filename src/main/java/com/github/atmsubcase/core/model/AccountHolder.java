package com.github.atmsubcase.core.model;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.Validate;

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
        this.personId = Validate.notBlank(personId);
    }
}

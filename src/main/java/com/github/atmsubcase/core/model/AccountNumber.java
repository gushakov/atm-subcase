package com.github.atmsubcase.core.model;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.Validate;

/**
 * Value object. Models an identifier for a bank account.
 *
 * @see Account
 */
@Value
public class AccountNumber {

    public static AccountNumber of(String number) {
        return AccountNumber.builder()
                .number(number)
                .build();
    }

    String number;

    @Builder
    private AccountNumber(String number) {
        this.number = Validate.notBlank(number);
    }
}

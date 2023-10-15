package com.github.atmsubcase.core.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {

    AccountNumber accountNumber;
    AccountHolder accountHolder;

    @NonFinal
    BigDecimal currentAmount;

    @Builder
    public Account(AccountNumber accountNumber, AccountHolder accountHolder) {
        this.accountNumber = Validate.notNull(accountNumber);
        this.accountHolder = Validate.notNull(accountHolder);

        this.currentAmount = new BigDecimal(1000);
    }
}

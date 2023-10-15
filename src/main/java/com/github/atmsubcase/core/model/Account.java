package com.github.atmsubcase.core.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;

/**
 * Aggregate root. Models a back account.
 */
@Getter
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

        // we are starting every one at a $1000, why not
        this.currentAmount = new BigDecimal(1000);
    }
}

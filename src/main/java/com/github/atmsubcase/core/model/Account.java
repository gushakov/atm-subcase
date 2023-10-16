package com.github.atmsubcase.core.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
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

    BigDecimal currentAmount;

    boolean blocked;

    @Builder
    public Account(AccountNumber accountNumber, AccountHolder accountHolder, BigDecimal currentAmount, boolean blocked) {
        this.accountNumber = Validate.notNull(accountNumber);
        this.accountHolder = Validate.notNull(accountHolder);
        this.currentAmount = Validate.notNull(currentAmount);
        this.blocked = blocked;
    }

    /**
     * Returns {@code true} if a person with the given {@code personId}
     * can access this account.
     */
    public boolean canAccess(String personId) {
        // for this example we simply check if account holder is the same
        // person and that the account is not blocked
        return accountHolder.samePerson(personId) && !blocked;
    }

    public Account withdraw(BigDecimal amount) {
            return newAccount().currentAmount(currentAmount.subtract(amount)).build();
    }

    private AccountBuilder newAccount() {
        return Account.builder()
                .accountNumber(accountNumber)
                .accountHolder(accountHolder)
                .blocked(blocked)
                .currentAmount(currentAmount)
                ;
    }
}

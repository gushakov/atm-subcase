package com.github.atmsubcase.core.model;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.Validate;

/**
 * Value object. Models an identifier for a person holding an {@linkplain Account}.
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
        this.personId = Validate.notBlank(personId);
    }

    /**
     * Returns {@code true} if the given {@code aPersonId} identifies the same
     * person as the person referenced by this account holder.
     */
    public boolean samePerson(String aPersonId) {
        return this.personId.equals(aPersonId);
    }
}

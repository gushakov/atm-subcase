package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.AccountNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * This is a business exception thrown by the subcase when it
 * did not complete with a successful outcome.
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VerifyAccountFailedError extends RuntimeException {
    AccountNumber accountNumber;
}

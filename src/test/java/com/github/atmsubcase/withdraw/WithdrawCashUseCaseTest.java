package com.github.atmsubcase.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountHolder;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.distributor.CashDistributorOperationsOutputPort;
import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountFailedError;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcase;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashInputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashPresenterOutputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WithdrawCashUseCaseTest {

    @Mock
    WithdrawCashPresenterOutputPort withdrawCashPresenter;

    @Mock
    PersistenceOperationsOutputPort persistenceOps;

    @Mock
    SecurityOperationsOutputPort securityOps;

    @Mock
    CashDistributorOperationsOutputPort cashDistributorOps;

    @Test
    void withdraw_cash_successfully() {

        // given

        authenticatedUserWithPersonId("user1");
        anAccountWithNumberHolderAndBalance("account1", "user1", 1000);

        // when

        useCase().withdrawCash("account1", new BigDecimal(100));

        // then

        noErrorsWerePresented();

        // and

        debitedAccountWasSavedWithRemainingBalance("account1", 900);
        cashWasDistributed(100);
        resultOfSuccessfulCashWithdrawalWasPresented("account1");

    }

    @Test
    void error_presented_when_no_permission_to_access_account() {

        // given

        authenticatedUserWithPersonId("user1");
        anAccountWithNumberHolderAndBalance("account1", "user2", 1000);

        // when

        useCase().withdrawCash("account1", new BigDecimal(100));

        // then

        noPermissionToAccessAccountErrorWasPresented("user1", "account1");

        // and

        /*
            Update 18.10.2023 (bug fix):
            --------------------------
            We also need to check that the normal flow of the parent use case
            was effectively interrupted by an exception thrown from the
            subcase.
         */

        anErrorWasThrownSignalingVerificationFailureForAccount("account1");

        // and

        noCashWasDistributed();
        noResultOfSuccessfulCashWithdrawalWasPresented();
    }

    private void authenticatedUserWithPersonId(String personId) {
        lenient().when(securityOps.getAuthenticatedUserPersonId())
                .thenReturn(personId);
    }

    private void anAccountWithNumberHolderAndBalance(String accountNumber, String accountHolder, int startingAmount) {
        lenient().when(persistenceOps.loadAccount(any(AccountNumber.class)))
                .thenReturn(Account.builder()
                        .accountNumber(AccountNumber.of(accountNumber))
                        .accountHolder(AccountHolder.of(accountHolder))
                        .currentBalance(new BigDecimal(startingAmount))
                        .blocked(false)
                        .build());
    }

    private WithdrawCashInputPort useCase() {
        VerifyAccountSubcase subcase = new VerifyAccountSubcase(withdrawCashPresenter, persistenceOps, securityOps);
        return new WithdrawCashUseCase(withdrawCashPresenter, subcase, persistenceOps, cashDistributorOps);
    }


    private void noErrorsWerePresented() {
        verify(withdrawCashPresenter, times(0))
                .presentError(any(Exception.class));
        verify(withdrawCashPresenter, times(0))
                .presentErrorWhenUserDoesNotPermissionToAccessAccount(anyString(), any(Account.class));
    }

    private void debitedAccountWasSavedWithRemainingBalance(String accountNumber, int remainingBalance) {
        ArgumentCaptor<Account> accountArg = ArgumentCaptor.forClass(Account.class);
        verify(persistenceOps, times(1))
                .saveAccount(accountArg.capture());
        Account debitedAccount = accountArg.getValue();
        assertThat(debitedAccount)
                .extracting(Account::getAccountNumber, Account::getCurrentBalance)
                .containsExactly(AccountNumber.of(accountNumber), new BigDecimal(remainingBalance));
    }

    private void cashWasDistributed(int amountDistributed) {
        verify(cashDistributorOps, times(1))
                .distribute(new BigDecimal(amountDistributed));
    }

    private void resultOfSuccessfulCashWithdrawalWasPresented(String accountNumber) {
        ArgumentCaptor<Account> accountArg = ArgumentCaptor.forClass(Account.class);
        verify(withdrawCashPresenter, times(1))
                .presentResultOfSuccessfulCashWithdrawal(accountArg.capture());
        Account debitedAccount = accountArg.getValue();
        assertThat(debitedAccount)
                .extracting(Account::getAccountNumber)
                .isEqualTo(AccountNumber.of(accountNumber));
    }

    private void noPermissionToAccessAccountErrorWasPresented(String userPersonId, String accountNumber) {

        ArgumentCaptor<String> userPersonIdArg = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Account> accountArg = ArgumentCaptor.forClass(Account.class);
        verify(withdrawCashPresenter, times(1))
                .presentErrorWhenUserDoesNotPermissionToAccessAccount(userPersonIdArg.capture(), accountArg.capture());
        String personId = userPersonIdArg.getValue();
        Account account = accountArg.getValue();
        assertThat(personId).isEqualTo(userPersonId);
        assertThat(account.getAccountNumber()).isEqualTo(AccountNumber.of(accountNumber));
    }

    private void anErrorWasThrownSignalingVerificationFailureForAccount(String accountNumber) {
        ArgumentCaptor<VerifyAccountFailedError> errorArg = ArgumentCaptor.forClass(VerifyAccountFailedError.class);
        verify(withdrawCashPresenter, times(1))
                .presentError(errorArg.capture());
        assertThat(errorArg.getValue().getAccountNumber()).isEqualTo(AccountNumber.of(accountNumber));
    }

    private void noCashWasDistributed() {
        verify(cashDistributorOps, times(0))
                .distribute(any(BigDecimal.class));
    }

    private void noResultOfSuccessfulCashWithdrawalWasPresented() {
        verify(withdrawCashPresenter, times(0))
                .presentResultOfSuccessfulCashWithdrawal(any(Account.class));
    }
}

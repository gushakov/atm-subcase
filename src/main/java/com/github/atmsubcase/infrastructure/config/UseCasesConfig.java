package com.github.atmsubcase.infrastructure.config;

import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.distributor.CashDistributorOperationsOutputPort;
import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountPresenterOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcase;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcaseInputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashInputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashUseCase;
import com.github.atmsubcase.infrastructure.adapters.web.withdraw.WithdrawCashPresenter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Configuration
public class UseCasesConfig {

    // here are all our secondary adapters, they were instantiated by Spring

    PersistenceOperationsOutputPort persistenceOps;
    SecurityOperationsOutputPort securityOps;
    CashDistributorOperationsOutputPort cashDistributorOps;

    /*
        Here is the prototype bean for "verify account" subcase.
        It must take an implementation of "VerifyAccountPresenterOutputPort"
        interface as a parameter. This parameter will be an actual presenter
        created for each use case. The rest of the arguments to the constructor
        of the subcase are the injected secondary adapters. Just like for
        a parent use case.
     */

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public VerifyAccountSubcaseInputPort verifyAccountSubcase(@Autowired(required = false) VerifyAccountPresenterOutputPort presenter){
        return new VerifyAccountSubcase(presenter, persistenceOps, securityOps);
    }

    /*
        Here is the bean for a parent use case. It passes its presenter as a parameter
        to the bean creation method for subcase (above).
     */

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public WithdrawCashInputPort withdrawCashUseCase(){
        WithdrawCashPresenter presenter = new WithdrawCashPresenter();
        return new WithdrawCashUseCase(presenter, verifyAccountSubcase(presenter), persistenceOps, cashDistributorOps);
    }

}

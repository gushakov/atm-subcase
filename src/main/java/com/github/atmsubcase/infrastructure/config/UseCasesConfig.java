package com.github.atmsubcase.infrastructure.config;

import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashInputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashUseCase;
import com.github.atmsubcase.infrastructure.adapters.web.withdraw.WithdrawCashPresenter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Configuration
public class UseCasesConfig {

    PersistenceOperationsOutputPort persistenceOps;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public WithdrawCashInputPort withdrawCashUseCase(){
        return new WithdrawCashUseCase(new WithdrawCashPresenter(), persistenceOps);
    }


}

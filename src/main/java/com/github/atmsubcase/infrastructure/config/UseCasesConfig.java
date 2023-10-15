package com.github.atmsubcase.infrastructure.config;

import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashInputPort;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashUseCase;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    public WithdrawCashInputPort withdrawCashUseCase(){
        return new WithdrawCashUseCase();
    }


}

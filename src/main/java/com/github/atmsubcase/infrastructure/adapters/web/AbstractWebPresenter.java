package com.github.atmsubcase.infrastructure.adapters.web;

import com.github.atmsubcase.core.port.ErrorHandlingPresenterOutputPort;

public abstract class AbstractWebPresenter implements ErrorHandlingPresenterOutputPort {
    @Override
    public void presentError(Exception e) {
        // common error presentation for a web UI, show error message in the
        // current view
    }
}

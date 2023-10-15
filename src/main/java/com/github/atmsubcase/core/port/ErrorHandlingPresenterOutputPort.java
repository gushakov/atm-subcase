package com.github.atmsubcase.core.port;

/**
 * Output port for generic error handing.
 */
public interface ErrorHandlingPresenterOutputPort {

    void presentError(Exception e);

}

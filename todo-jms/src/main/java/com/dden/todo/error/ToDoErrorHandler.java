package com.dden.todo.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 * ToDoErrorHandler
 *
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public class ToDoErrorHandler implements ErrorHandler {
    private static Logger log = LoggerFactory.getLogger(ToDoErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        log.warn("ToDo error...");
        log.error(t.getCause().getMessage());

    }
}

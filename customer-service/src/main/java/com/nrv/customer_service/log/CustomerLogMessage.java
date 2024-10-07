package com.nrv.customer_service.log;

/**
 * Enum class for customer log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public enum CustomerLogMessage {
    CUSTOMER_LIST_GET("Get All Customer"),
    CUSTOMER_GET("Get a Customer by id: {}"),
    CUSTOMER_ADD("Add a Customer by id: {}"),
    CUSTOMER_UPDATE("Update a Customer by id: {}"),
    CUSTOMER_DELETE("Delete a Customer by id: {}");


    private final String message;

    CustomerLogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

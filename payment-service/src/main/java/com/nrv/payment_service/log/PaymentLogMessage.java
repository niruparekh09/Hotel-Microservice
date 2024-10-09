package com.nrv.payment_service.log;

/**
 * Enum class for customer log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public enum PaymentLogMessage {
    PAYMENT_LIST_GET("Get All Payment"),
    PAYMENT_GET("Get a Payment by id: {}"),
    PAYMENT_GET_BY_CUSTOMER("Get a Payment by customer id: {}"),
    PAYMENT_ADD("Add a Payment by id: {}");


    private final String message;

    PaymentLogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

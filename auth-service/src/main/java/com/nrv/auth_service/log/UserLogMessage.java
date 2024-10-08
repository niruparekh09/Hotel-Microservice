package com.nrv.auth_service.log;

/**
 * Enum class for customer log messages.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
public enum UserLogMessage {
    User_LIST_GET("Get All User"),
    USER_ADD("Add a Customer by id: {}"),
    USER_DELETE("User Delete with id: {}"),
    USER_UPDATE("User Updated with id: {}"),
    USER_LOGIN("User Logged In with id: {}");


    private final String message;

    UserLogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

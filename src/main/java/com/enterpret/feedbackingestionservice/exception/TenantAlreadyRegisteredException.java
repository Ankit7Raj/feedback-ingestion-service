package com.enterpret.feedbackingestionservice.exception;

public class TenantAlreadyRegisteredException extends ServerException {
    public TenantAlreadyRegisteredException(String username) {
        super("Tenant already registered with username: \"" + username + "\"");
    }
}

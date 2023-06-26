package com.enterpret.feedbackingestionservice.exception;

public class TenantNotFoundException extends ServerException {
    public TenantNotFoundException(String username) {
        super("Tenant Not Found with username: " + username);
    }
}

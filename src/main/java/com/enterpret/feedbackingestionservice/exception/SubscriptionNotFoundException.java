package com.enterpret.feedbackingestionservice.exception;

import com.enterpret.feedbackingestionservice.model.Source;

public class SubscriptionNotFoundException extends ServerException {
    public SubscriptionNotFoundException(String topic, String tenantUserName, Source source) {
        super("Topic: \"" + topic + "\" with source: \"" + source.toString() + "\" not found for tenant with username: \"" + tenantUserName + "\"");
    }
}

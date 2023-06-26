package com.enterpret.feedbackingestionservice.exception;

import com.enterpret.feedbackingestionservice.model.Source;

public class SubscriptionAlreadyPresentException extends ServerException {
    public SubscriptionAlreadyPresentException(String tenantUserName, String topic, Source source) {
        super("Topic: \"" + topic + "\" with source: \"" + source.toString() + "\" already present for tenant with userName: \"" + tenantUserName + "\"");
    }
}

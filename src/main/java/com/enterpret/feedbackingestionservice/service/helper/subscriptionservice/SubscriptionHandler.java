package com.enterpret.feedbackingestionservice.service.helper.subscriptionservice;

import com.enterpret.feedbackingestionservice.exception.InvalidInputException;
import com.enterpret.feedbackingestionservice.model.request.AddSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;

public interface SubscriptionHandler {
    public FeedbackSubscription getFeedbackSubscription(AddSubscriptionRequest addSubscriptionRequest) throws InvalidInputException;
}

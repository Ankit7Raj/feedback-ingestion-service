package com.enterpret.feedbackingestionservice.service.helper.subscriptionservice;

import com.enterpret.feedbackingestionservice.exception.InvalidInputException;
import com.enterpret.feedbackingestionservice.model.request.AddSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.subscription.DiscourseFeedbackSubscription;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DiscourseSubscriptionHandler implements SubscriptionHandler {

    @Override
    public FeedbackSubscription getFeedbackSubscription(AddSubscriptionRequest addSubscriptionRequest) throws InvalidInputException {
        String topic = addSubscriptionRequest.getTopic();
        String tenantUserName = addSubscriptionRequest.getTenantUserName();
        if (addSubscriptionRequest.getRequiredData() == null)
            throw new InvalidInputException("requiredData not present in the input");
        String searchString = addSubscriptionRequest.getRequiredData().get("searchString");
        if (searchString == null) {
            throw new InvalidInputException("requiredData.searchString not present in the input");
        }

        Date createdAt = new Date();
        DiscourseFeedbackSubscription discourseFeedbackSubscription = new DiscourseFeedbackSubscription(tenantUserName, topic, searchString, createdAt);
        return discourseFeedbackSubscription;
    }
}

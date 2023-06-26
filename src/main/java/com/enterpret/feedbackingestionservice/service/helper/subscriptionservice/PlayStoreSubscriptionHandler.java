package com.enterpret.feedbackingestionservice.service.helper.subscriptionservice;

import com.enterpret.feedbackingestionservice.exception.InvalidInputException;
import com.enterpret.feedbackingestionservice.model.request.AddSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.model.subscription.PlayStoreFeedbackSubscription;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayStoreSubscriptionHandler implements SubscriptionHandler {
    public FeedbackSubscription getFeedbackSubscription(AddSubscriptionRequest addSubscriptionRequest) throws InvalidInputException {
        String topic = addSubscriptionRequest.getTopic();
        String tenantUserName = addSubscriptionRequest.getTenantUserName();
        if (addSubscriptionRequest.getRequiredData() == null)
            throw new InvalidInputException("requiredData not present in the input");
        String playStoreAppId = addSubscriptionRequest.getRequiredData().get("playStoreAppId");
        if (playStoreAppId == null) {
            throw new InvalidInputException("requiredData.playStoreAppId not present in the input");
        }

        Date createdAt = new Date();
        PlayStoreFeedbackSubscription playStoreFeedbackTopic = new PlayStoreFeedbackSubscription(tenantUserName, topic, playStoreAppId, createdAt);
        return playStoreFeedbackTopic;
    }
}

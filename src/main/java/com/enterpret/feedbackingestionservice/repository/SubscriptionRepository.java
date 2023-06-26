package com.enterpret.feedbackingestionservice.repository;

import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SubscriptionRepository {
    List<FeedbackSubscription> feedbackSubscriptions;

    public void addSubscription(FeedbackSubscription feedbackSubscription) {
        feedbackSubscriptions.add(feedbackSubscription);
    }

    public FeedbackSubscription getSubscription(String tenantUserName, String topic, Source source) {
        FeedbackSubscription feedbackSubscription = null;
        for (FeedbackSubscription feedbackSubscriptionInDB : feedbackSubscriptions) {
            if (feedbackSubscriptionInDB.getTenantUserName().equals(tenantUserName) && feedbackSubscriptionInDB.getTopic().equals(topic) && source == feedbackSubscriptionInDB.getSource()) {
                feedbackSubscription = feedbackSubscriptionInDB;
                break;
            }
        }
        return feedbackSubscription;
    }

    public void removeSubscription(FeedbackSubscription feedbackSubscription) {
        feedbackSubscriptions.remove(feedbackSubscription);
    }

    public List<FeedbackSubscription> getAllFeedbackSubscription() {
        return feedbackSubscriptions;
    }
}

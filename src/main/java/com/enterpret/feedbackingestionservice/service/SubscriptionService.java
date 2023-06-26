package com.enterpret.feedbackingestionservice.service;

import com.enterpret.feedbackingestionservice.exception.InvalidInputException;
import com.enterpret.feedbackingestionservice.exception.SubscriptionAlreadyPresentException;
import com.enterpret.feedbackingestionservice.exception.SubscriptionNotFoundException;
import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.request.AddSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.request.RemoveSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.repository.SubscriptionRepository;
import com.enterpret.feedbackingestionservice.service.helper.subscriptionservice.SubscriptionHandler;
import com.enterpret.feedbackingestionservice.service.helper.subscriptionservice.SubscriptionHandlerFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;
    SubscriptionHandlerFactory subscriptionHandlerFactory;

    public FeedbackSubscription addSubscription(AddSubscriptionRequest addSubscriptionRequest) throws InvalidInputException, SubscriptionAlreadyPresentException {
        String tenantUserName = addSubscriptionRequest.getTenantUserName();
        String topic = addSubscriptionRequest.getTopic();
        Source source = addSubscriptionRequest.getSource();
        FeedbackSubscription feedbackSubscription = subscriptionRepository.getSubscription(tenantUserName, topic, source);
        if (feedbackSubscription != null)
            throw new SubscriptionAlreadyPresentException(tenantUserName, topic, source);

        SubscriptionHandler subscriptionHandler = subscriptionHandlerFactory.getSubscriptionHandler(source);
        feedbackSubscription = subscriptionHandler.getFeedbackSubscription(addSubscriptionRequest);

        subscriptionRepository.addSubscription(feedbackSubscription);
        return feedbackSubscription;
    }

    public List<FeedbackSubscription> getAllSubscriptionForATenant(String tenantUserName) {
        List<FeedbackSubscription> feedbackSubscriptions = subscriptionRepository.getAllFeedbackSubscription();
        List<FeedbackSubscription> feedbackSubscriptionForTenant = new ArrayList<>();

        for (FeedbackSubscription feedbackSubscription : feedbackSubscriptions) {
            if (feedbackSubscription.getTenantUserName().equals(tenantUserName))
                feedbackSubscriptionForTenant.add(feedbackSubscription);
        }

        return feedbackSubscriptionForTenant;
    }

    public List<FeedbackSubscription> getAllSubscription() {
        return subscriptionRepository.getAllFeedbackSubscription();
    }

    public FeedbackSubscription removeSubscription(RemoveSubscriptionRequest removeSubscriptionRequest) throws SubscriptionNotFoundException {
        String tenantUserName = removeSubscriptionRequest.getTenantUserName();
        String topic = removeSubscriptionRequest.getTopic();
        Source source = removeSubscriptionRequest.getSource();
        FeedbackSubscription feedbackSubscription = subscriptionRepository.getSubscription(tenantUserName, topic, source);
        if (feedbackSubscription == null)
            throw new SubscriptionNotFoundException(topic, tenantUserName, source);

        subscriptionRepository.removeSubscription(feedbackSubscription);
        return feedbackSubscription;
    }
}

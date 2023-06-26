package com.enterpret.feedbackingestionservice.service;

import com.enterpret.feedbackingestionservice.exception.SubscriptionNotFoundException;
import com.enterpret.feedbackingestionservice.exception.TenantNotFoundException;
import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.Tenant;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.repository.SubscriptionRepository;
import com.enterpret.feedbackingestionservice.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {

    TenantRepository tenantRepository;
    SubscriptionRepository subscriptionRepository;

    public void authorizeTenantUserName(String username) throws TenantNotFoundException {
        Tenant tenant = tenantRepository.getTenant(username);
        if (tenant == null)
            throw new TenantNotFoundException(username);
    }

    public void authorizeSubscription(String tenantUserName, String topic, Source source) throws SubscriptionNotFoundException {
        FeedbackSubscription feedbackSubscription = subscriptionRepository.getSubscription(tenantUserName, topic, source);
        if (feedbackSubscription == null)
            throw new SubscriptionNotFoundException(topic, tenantUserName, source);
    }
}

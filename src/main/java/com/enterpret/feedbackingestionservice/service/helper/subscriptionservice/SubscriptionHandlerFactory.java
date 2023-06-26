package com.enterpret.feedbackingestionservice.service.helper.subscriptionservice;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionHandlerFactory {

    PlayStoreSubscriptionHandler playStoreSubscriptionHandler;
    DiscourseSubscriptionHandler discourseSubscriptionHandler;
    TwitterSubscriptionHandler twitterSubscriptionHandler;

    public SubscriptionHandler getSubscriptionHandler(Source source) {
        if (source == Source.PLAY_STORE)
            return playStoreSubscriptionHandler;
        else if (source == Source.DISCOURSE)
            return discourseSubscriptionHandler;
        else if (source == Source.TWITTER)
            return twitterSubscriptionHandler;
        return null;
    }
}

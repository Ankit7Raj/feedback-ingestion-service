package com.enterpret.feedbackingestionservice.service.pullService;

import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackPullServiceFactory {

    DiscourseFeedbackPullService discourseFeedbackPullService;
    PlayStoreFeedbackPullService playStoreFeedbackPullService;
    TwitterFeedbackPullService twitterFeedbackPullService;

    public FeedbackPullService getFeedbackPullService(FeedbackSubscription feedbackSubscription) {
        Source source = feedbackSubscription.getSource();
        if (source == Source.DISCOURSE) {
            return discourseFeedbackPullService;
        } else if (source == Source.PLAY_STORE) {
            return playStoreFeedbackPullService;
        } else if (source == Source.TWITTER) {
            return twitterFeedbackPullService;
        }
        return null;
    }
}

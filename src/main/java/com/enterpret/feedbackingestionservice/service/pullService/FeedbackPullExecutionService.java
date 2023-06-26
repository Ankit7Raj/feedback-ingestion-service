package com.enterpret.feedbackingestionservice.service.pullService;

import com.enterpret.feedbackingestionservice.exception.FeedbackAlreadyPresentException;
import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.service.FeedbackService;
import com.enterpret.feedbackingestionservice.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackPullExecutionService {

    SubscriptionService subscriptionService;
    FeedbackService feedbackService;
    FeedbackPullServiceFactory feedbackPullServiceFactory;

    @Scheduled(cron = "0 0 6 * * ?")
    public void pullAndSaveFeedback() throws IOException {
        System.out.println(new Date());
        List<FeedbackSubscription> feedbackSubscriptions = subscriptionService.getAllSubscription();
        System.out.println(feedbackSubscriptions.size());
        for (FeedbackSubscription feedbackSubscription : feedbackSubscriptions) {
            FeedbackPullService feedbackPullService = feedbackPullServiceFactory.getFeedbackPullService(feedbackSubscription);
            List<Feedback> feedbacks = feedbackPullService.pull(feedbackSubscription);
            for (Feedback feedback : feedbacks) {
                try {
                    feedbackService.addFeedback(feedback);
                } catch (FeedbackAlreadyPresentException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }
}

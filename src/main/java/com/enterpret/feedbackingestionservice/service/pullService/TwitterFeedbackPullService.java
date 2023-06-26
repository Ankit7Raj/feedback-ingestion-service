package com.enterpret.feedbackingestionservice.service.pullService;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterFeedbackPullService implements FeedbackPullService {
    @Override
    public List<Feedback> pull(FeedbackSubscription feedbackSubscription) throws IOException {
        List<Feedback> feedbacks = new ArrayList<>();

        // TODO: Add logic to pull posts containing given searchString

        return feedbacks;
    }
}

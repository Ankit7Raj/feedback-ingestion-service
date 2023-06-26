package com.enterpret.feedbackingestionservice.service.pullService;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FeedbackPullService {
    public abstract List<Feedback> pull(FeedbackSubscription feedbackSubscription) throws IOException;
}

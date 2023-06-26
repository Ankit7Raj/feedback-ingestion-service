package com.enterpret.feedbackingestionservice.repository;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FeedbackRepository {
    List<Feedback> feedbacks;

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbacks;
    }
}

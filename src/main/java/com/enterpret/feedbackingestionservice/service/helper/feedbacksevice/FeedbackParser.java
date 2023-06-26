package com.enterpret.feedbackingestionservice.service.helper.feedbacksevice;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.FeedbackRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FeedbackParser {

    public abstract Feedback parse(String tenantUserName, String topic, FeedbackRequest feedbackRequest) throws JsonProcessingException;
}

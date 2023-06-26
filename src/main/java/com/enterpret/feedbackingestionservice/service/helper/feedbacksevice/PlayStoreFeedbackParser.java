package com.enterpret.feedbackingestionservice.service.helper.feedbacksevice;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.FeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.PlayStoreFeedbackRequest;
import com.enterpret.feedbackingestionservice.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayStoreFeedbackParser implements FeedbackParser {

    ObjectMapper objectMapper;

    @Override
    public Feedback parse(String tenantUserName, String topic, FeedbackRequest feedbackRequest) throws JsonProcessingException {
        PlayStoreFeedbackRequest playStoreFeedbackRequest = (PlayStoreFeedbackRequest) feedbackRequest;
        return Utility.getFeedbackFromPlayStoreFeedbackRequest(playStoreFeedbackRequest, tenantUserName, topic);
    }
}

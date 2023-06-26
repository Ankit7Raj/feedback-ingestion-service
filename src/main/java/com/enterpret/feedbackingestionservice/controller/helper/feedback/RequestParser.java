package com.enterpret.feedbackingestionservice.controller.helper.feedback;

import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.DiscourseFeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.FeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.PlayStoreFeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.TwitterFeedbackRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestParser {

    ObjectMapper objectMapper;

    public FeedbackRequest parse(String feedbackRequestString, Source source) throws JsonProcessingException {
        FeedbackRequest feedbackRequest = null;

        if (source == Source.DISCOURSE) {
            DiscourseFeedbackRequest discourseFeedbackRequest = objectMapper.readValue(feedbackRequestString, DiscourseFeedbackRequest.class);
            feedbackRequest = discourseFeedbackRequest;
        } else if (source == Source.PLAY_STORE) {
            PlayStoreFeedbackRequest playStoreFeedbackRequest = objectMapper.readValue(feedbackRequestString, PlayStoreFeedbackRequest.class);
            feedbackRequest = playStoreFeedbackRequest;
        } else if (source == Source.TWITTER) {
            TwitterFeedbackRequest twitterFeedbackRequest = objectMapper.readValue(feedbackRequestString, TwitterFeedbackRequest.class);
            feedbackRequest = twitterFeedbackRequest;
        }

        return feedbackRequest;
    }
}

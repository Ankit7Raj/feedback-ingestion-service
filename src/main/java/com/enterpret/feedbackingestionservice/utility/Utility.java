package com.enterpret.feedbackingestionservice.utility;

import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.feedback.DiscourseFeedbackDescription;
import com.enterpret.feedbackingestionservice.model.feedback.DiscourseFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedback.FeedbackType;
import com.enterpret.feedbackingestionservice.model.feedback.PlayStoreFeedbackDescription;
import com.enterpret.feedbackingestionservice.model.feedback.PlayStoreFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedback.TwitterFeedbackDescription;
import com.enterpret.feedbackingestionservice.model.feedback.TwitterFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.DiscourseFeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.PlayStoreFeedbackRequest;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.TwitterFeedbackRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Utility {
    public static Feedback getFeedbackFromDiscourseFeedbackRequest(DiscourseFeedbackRequest discourseFeedbackRequest, String tenantUserName, String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String discourseFeedbackRequestString = objectMapper.writeValueAsString(discourseFeedbackRequest);
        DiscourseFeedbackDescription discourseFeedbackDescription = objectMapper.readValue(discourseFeedbackRequestString, DiscourseFeedbackDescription.class);
        DiscourseFeedbackMetadata discourseFeedbackMetadata = objectMapper.readValue(discourseFeedbackRequestString, DiscourseFeedbackMetadata.class);
        Date date = new Date();

        return Feedback.builder()
                .tenantUserName(tenantUserName)
                .topic(topic)
                .source(Source.DISCOURSE)
                .feedbackType(FeedbackType.POST)
                .feedbackDescription(discourseFeedbackDescription)
                .recordCreatedAt(date)
                .metadata(discourseFeedbackMetadata)
                .build();
    }

    public static Feedback getFeedbackFromPlayStoreFeedbackRequest(PlayStoreFeedbackRequest playStoreFeedbackRequest, String tenantUserName, String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String playStoreFeedbackRequestString = objectMapper.writeValueAsString(playStoreFeedbackRequest);
        PlayStoreFeedbackDescription playStoreFeedbackDescription = objectMapper.readValue(playStoreFeedbackRequestString, PlayStoreFeedbackDescription.class);
        PlayStoreFeedbackMetadata playStoreFeedbackMetadata = objectMapper.readValue(playStoreFeedbackRequestString, PlayStoreFeedbackMetadata.class);
        Date date = new Date();

        return Feedback.builder()
                .tenantUserName(tenantUserName)
                .topic(topic)
                .source(Source.PLAY_STORE)
                .feedbackType(FeedbackType.REVIEW)
                .feedbackDescription(playStoreFeedbackDescription)
                .recordCreatedAt(date)
                .metadata(playStoreFeedbackMetadata)
                .build();
    }

    public static Feedback getFeedbackFromTwitterFeedbackRequest(TwitterFeedbackRequest twitterFeedbackRequest, String tenantUserName, String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String twitterFeedbackRequestString = objectMapper.writeValueAsString(twitterFeedbackRequest);
        TwitterFeedbackDescription twitterFeedbackDescription = objectMapper.readValue(twitterFeedbackRequestString, TwitterFeedbackDescription.class);
        TwitterFeedbackMetadata twitterFeedbackMetadata = objectMapper.readValue(twitterFeedbackRequestString, TwitterFeedbackMetadata.class);
        Date date = new Date();

        return Feedback.builder()
                .tenantUserName(tenantUserName)
                .topic(topic)
                .source(Source.TWITTER)
                .feedbackType(FeedbackType.POST)
                .feedbackDescription(twitterFeedbackDescription)
                .recordCreatedAt(date)
                .metadata(twitterFeedbackMetadata)
                .build();
    }
}

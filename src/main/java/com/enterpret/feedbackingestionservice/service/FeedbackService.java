package com.enterpret.feedbackingestionservice.service;

import com.enterpret.feedbackingestionservice.exception.FeedbackAlreadyPresentException;
import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.feedback.DiscourseFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedback.PlayStoreFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedback.TwitterFeedbackMetadata;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.FeedbackRequest;
import com.enterpret.feedbackingestionservice.repository.FeedbackRepository;
import com.enterpret.feedbackingestionservice.service.helper.feedbacksevice.FeedbackParser;
import com.enterpret.feedbackingestionservice.service.helper.feedbacksevice.FeedbackParserFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    FeedbackRepository feedbackRepository;
    FeedbackParserFactory feedbackParserFactory;

    public Feedback addFeedback(String tenantUserName, String topic, Source source, FeedbackRequest feedbackRequest) throws JsonProcessingException, FeedbackAlreadyPresentException {
        FeedbackParser feedbackParser = feedbackParserFactory.getParser(source);
        Feedback feedback = feedbackParser.parse(tenantUserName, topic, feedbackRequest);

        if (checkForIdenticalFeedbackPresent(feedback))
            throw new FeedbackAlreadyPresentException();

        feedbackRepository.addFeedback(feedback);
        return feedback;
    }

    public Feedback addFeedback(Feedback feedback) throws FeedbackAlreadyPresentException {
        if (checkForIdenticalFeedbackPresent(feedback))
            throw new FeedbackAlreadyPresentException();

        feedbackRepository.addFeedback(feedback);
        return feedback;
    }

    private boolean checkForIdenticalFeedbackPresent(Feedback feedback) {
        List<Feedback> feedbacks = feedbackRepository.getAllFeedback();
        for (Feedback feedbackInDB : feedbacks) {
            if (feedback.getSource() == Source.DISCOURSE && feedbackInDB.getSource() == Source.DISCOURSE) {
                DiscourseFeedbackMetadata discourseFeedbackMetadata = (DiscourseFeedbackMetadata) feedback.getMetadata();
                DiscourseFeedbackMetadata discourseFeedbackMetadataForFeedbackInDB = (DiscourseFeedbackMetadata) feedbackInDB.getMetadata();
                if (discourseFeedbackMetadataForFeedbackInDB.getId() == discourseFeedbackMetadata.getId())
                    return true;
            }

            if (feedback.getSource() == Source.PLAY_STORE && feedbackInDB.getSource() == Source.PLAY_STORE) {
                PlayStoreFeedbackMetadata playStoreFeedbackMetadata = (PlayStoreFeedbackMetadata) feedback.getMetadata();
                PlayStoreFeedbackMetadata playStoreFeedbackMetadataForFeedbackInDB = (PlayStoreFeedbackMetadata) feedbackInDB.getMetadata();
                if (playStoreFeedbackMetadataForFeedbackInDB.getId() == playStoreFeedbackMetadata.getId())
                    return true;
            }

            if (feedback.getSource() == Source.TWITTER && feedbackInDB.getSource() == Source.TWITTER) {
                TwitterFeedbackMetadata twitterFeedbackMetadata = (TwitterFeedbackMetadata) feedback.getMetadata();
                TwitterFeedbackMetadata twitterFeedbackMetadataForFeedbackInDB = (TwitterFeedbackMetadata) feedbackInDB.getMetadata();
                if (twitterFeedbackMetadataForFeedbackInDB.getId() == twitterFeedbackMetadata.getId())
                    return true;
            }
        }

        return false;
    }

    public List<Feedback> getAllFeedbackForATopic(String tenantUserName, String topic) {
        List<Feedback> feedbacks = feedbackRepository.getAllFeedback();
        List<Feedback> feedbacksForTopic = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            if (feedback.getTenantUserName().equals(tenantUserName) && feedback.getTopic().equals(topic)) {
                feedbacksForTopic.add(feedback);
            }
        }

        return feedbacksForTopic;
    }

    public List<Feedback> getFeedbacksForATopicFromASource(String tenantUserName, String topic, Source source) {
        List<Feedback> feedbacks = feedbackRepository.getAllFeedback();
        List<Feedback> feedbacksForTopicFromSource = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            if (feedback.getTenantUserName().equals(tenantUserName) && feedback.getTopic().equals(topic) && feedback.getSource() == source) {
                feedbacksForTopicFromSource.add(feedback);
            }
        }

        return feedbacksForTopicFromSource;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.getAllFeedback();
    }
}

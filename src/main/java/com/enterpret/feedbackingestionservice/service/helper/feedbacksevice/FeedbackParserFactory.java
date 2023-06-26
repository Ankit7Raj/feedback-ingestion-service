package com.enterpret.feedbackingestionservice.service.helper.feedbacksevice;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackParserFactory {

    DiscourseFeedbackParser discourseFeedbackParser;
    PlayStoreFeedbackParser playStoreFeedbackParser;
    TwitterFeedbackParser twitterFeedbackParser;

    public FeedbackParser getParser(Source source) {
        if (source == Source.DISCOURSE) {
            return discourseFeedbackParser;
        } else if (source == Source.PLAY_STORE) {
            return playStoreFeedbackParser;
        } else if (source == Source.TWITTER) {
            return twitterFeedbackParser;
        }
        return null;
    }
}

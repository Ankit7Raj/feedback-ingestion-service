package com.enterpret.feedbackingestionservice.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayStoreFeedbackDescription extends FeedbackDescription {
    int rating;
    String review;
}

package com.enterpret.feedbackingestionservice.model.pullFeedbackResponse;

import com.enterpret.feedbackingestionservice.model.feedbackRequest.DiscourseFeedbackRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscourseFeedbackSearchResponse {
    DiscourseFeedbackRequest[] posts;
}

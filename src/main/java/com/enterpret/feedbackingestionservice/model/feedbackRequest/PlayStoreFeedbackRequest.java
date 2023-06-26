package com.enterpret.feedbackingestionservice.model.feedbackRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayStoreFeedbackRequest extends FeedbackRequest {
    int id;
    String username;
    Date created_at;
    int rating;
    String review;
    int likeCount;
    String appVersion;
}

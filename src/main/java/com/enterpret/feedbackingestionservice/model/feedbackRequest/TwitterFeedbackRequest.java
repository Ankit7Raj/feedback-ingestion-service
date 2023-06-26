package com.enterpret.feedbackingestionservice.model.feedbackRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterFeedbackRequest extends FeedbackRequest {
    int id;
    String username;
    String tweet;
    Date createdAt;
    int likeCount;
    int retweetCount;
    int replyCount;
}

package com.enterpret.feedbackingestionservice.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwitterFeedbackMetadata extends Metadata {
    int id;
    String username;
    Date createdAt;
    int likeCount;
    int retweetCount;
    int replyCount;
}

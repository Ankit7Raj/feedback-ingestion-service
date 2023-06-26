package com.enterpret.feedbackingestionservice.model.feedback;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {
    String tenantUserName;
    String topic;
    Source source;
    FeedbackType feedbackType;
    FeedbackDescription feedbackDescription;
    Date recordCreatedAt;
    Metadata metadata;
}

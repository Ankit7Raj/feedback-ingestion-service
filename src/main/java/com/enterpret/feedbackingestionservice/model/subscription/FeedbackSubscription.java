package com.enterpret.feedbackingestionservice.model.subscription;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FeedbackSubscription {
    final String tenantUserName;
    final String topic;
    final Source source;
    final Date createdAt;
}

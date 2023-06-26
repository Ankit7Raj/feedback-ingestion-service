package com.enterpret.feedbackingestionservice.model.request;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveSubscriptionRequest {
    private String tenantUserName;
    private String topic;
    private Source source;
}

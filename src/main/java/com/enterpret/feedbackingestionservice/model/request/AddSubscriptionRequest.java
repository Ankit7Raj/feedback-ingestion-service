package com.enterpret.feedbackingestionservice.model.request;

import com.enterpret.feedbackingestionservice.model.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSubscriptionRequest {
    private String tenantUserName;
    private String topic;
    private Source source;
    private Map<String, String> requiredData;
}

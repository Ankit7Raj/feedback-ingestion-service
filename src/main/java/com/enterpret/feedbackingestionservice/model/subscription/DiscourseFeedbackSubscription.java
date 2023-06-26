package com.enterpret.feedbackingestionservice.model.subscription;

import com.enterpret.feedbackingestionservice.model.Source;

import java.util.Date;

public class DiscourseFeedbackSubscription extends FeedbackSubscription {
    final String searchString;

    public DiscourseFeedbackSubscription(String tenantUserName, String topic, String searchString, Date date) {
        super(tenantUserName, topic, Source.DISCOURSE, date);
        this.searchString = searchString;
    }

    public String getTopic() {
        return super.getTopic();
    }

    public String getSearchString() {
        return searchString;
    }

    @Override
    public Source getSource() {
        return super.getSource();
    }

    @Override
    public Date getCreatedAt() {
        return super.getCreatedAt();
    }
}

package com.enterpret.feedbackingestionservice.model.subscription;

import com.enterpret.feedbackingestionservice.model.Source;

import java.util.Date;

public class PlayStoreFeedbackSubscription extends FeedbackSubscription {
    final String playStoreAppId;

    public PlayStoreFeedbackSubscription(String tenantUserName, String topic, String playStoreAppId, Date createdAt) {
        super(tenantUserName, topic, Source.PLAY_STORE, createdAt);
        ;
        this.playStoreAppId = playStoreAppId;
    }

    public String getTopic() {
        return super.getTopic();
    }

    public String getPlayStoreAppId() {
        return playStoreAppId;
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

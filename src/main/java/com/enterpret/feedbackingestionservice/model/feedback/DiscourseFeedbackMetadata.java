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
public class DiscourseFeedbackMetadata extends Metadata {
    int id;
    String name;
    String username;
    String avatar_template;
    Date created_at;
    int like_count;
    int post_number;
    String topic_title_headline;
    String topic_id;
}

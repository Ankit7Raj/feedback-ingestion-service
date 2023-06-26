package com.enterpret.feedbackingestionservice.service.pullService;

import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.DiscourseFeedbackRequest;
import com.enterpret.feedbackingestionservice.model.pullFeedbackResponse.DiscourseFeedbackSearchResponse;
import com.enterpret.feedbackingestionservice.model.subscription.DiscourseFeedbackSubscription;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DiscourseFeedbackPullService implements FeedbackPullService {

    @Value("${feedback.pull.discourse.url}")
    public String url;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Feedback> pull(FeedbackSubscription feedbackSubscription) throws IOException, JsonProcessingException {
        DiscourseFeedbackSubscription discourseFeedbackSubscription = (DiscourseFeedbackSubscription) feedbackSubscription;
        String searchString = discourseFeedbackSubscription.getSearchString();

        int pageNumber = 1;
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String curDate = simpleDateFormat.format(new Date());
        long millisInADay = 1000 * 60 * 60 * 24;
        String prevDate = simpleDateFormat.format(new Date(new Date().getTime() - millisInADay));

        List<Feedback> discourseFeedbackList = new ArrayList<>();

        while (true) {
            String command = "curl --location --request GET " + url + "?page=" + pageNumber + "&q=after%3A" + prevDate + "+before%3A" + curDate;
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String response = "";
            while ((line = reader.readLine()) != null) {
                response = response.concat(line);
            }
            DiscourseFeedbackSearchResponse discourseFeedbackSearchResponse = objectMapper.readValue(response, DiscourseFeedbackSearchResponse.class);
            System.out.println(discourseFeedbackSearchResponse.getPosts().length);
            if (discourseFeedbackSearchResponse.getPosts().length == 0)
                break;

            for (DiscourseFeedbackRequest discourseFeedbackRequest : discourseFeedbackSearchResponse.getPosts()) {
                discourseFeedbackList.add(Utility.getFeedbackFromDiscourseFeedbackRequest(discourseFeedbackRequest, discourseFeedbackSubscription.getTenantUserName(), discourseFeedbackSubscription.getTopic()));
            }
            pageNumber++;
        }

        return discourseFeedbackList;
    }
}

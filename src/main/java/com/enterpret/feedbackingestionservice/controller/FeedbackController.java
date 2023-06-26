package com.enterpret.feedbackingestionservice.controller;

import com.enterpret.feedbackingestionservice.controller.helper.feedback.RequestParser;
import com.enterpret.feedbackingestionservice.model.Source;
import com.enterpret.feedbackingestionservice.model.feedback.Feedback;
import com.enterpret.feedbackingestionservice.model.feedbackRequest.FeedbackRequest;
import com.enterpret.feedbackingestionservice.service.AuthorizationService;
import com.enterpret.feedbackingestionservice.service.FeedbackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class FeedbackController {

    FeedbackService feedbackService;
    RequestParser requestParser;
    AuthorizationService authorizationService;

    @RequestMapping(path = "/feedback/add", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addFeedback(@RequestParam String tenantUserName, @RequestParam String topic, @RequestParam Source source, @RequestBody String addFeedbackRequestString) {
        log.info(addFeedbackRequestString);
        try {
            authorizationService.authorizeSubscription(tenantUserName, topic, source);
            FeedbackRequest feedbackRequest = requestParser.parse(addFeedbackRequestString, source);

            Feedback feedback = feedbackService.addFeedback(tenantUserName, topic, source, feedbackRequest);
            return ResponseEntity.status(HttpStatus.OK).body(feedback);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/feedback/getAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

    @RequestMapping(path = "/feedback/getAllForTopic", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<Feedback>> getAllFeedbackForATopic(@RequestParam String tenantUserName, @RequestParam String topic) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackForATopic(tenantUserName, topic);
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

    @RequestMapping(path = "/feedback/getAllForTopicFromSource", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<Feedback>> getFeedbacksForATopicFromASource(@RequestParam String tenantUserName, @RequestParam String topic, @RequestParam Source source) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksForATopicFromASource(tenantUserName, topic, source);
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }
}

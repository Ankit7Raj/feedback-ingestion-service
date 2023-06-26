package com.enterpret.feedbackingestionservice.controller;

import com.enterpret.feedbackingestionservice.exception.InvalidInputException;
import com.enterpret.feedbackingestionservice.model.request.AddSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.request.RemoveSubscriptionRequest;
import com.enterpret.feedbackingestionservice.model.subscription.FeedbackSubscription;
import com.enterpret.feedbackingestionservice.service.AuthorizationService;
import com.enterpret.feedbackingestionservice.service.SubscriptionService;
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
public class SubscriptionController {

    SubscriptionService subscriptionService;
    AuthorizationService authorizationService;

    @RequestMapping(path = "/subscription/add", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addSubscription(@RequestBody AddSubscriptionRequest addSubscriptionRequest) {
        try {
            if (addSubscriptionRequest.getTenantUserName() == null)
                throw new InvalidInputException("tenantUserName not present in input");
            if (addSubscriptionRequest.getTopic() == null)
                throw new InvalidInputException("topic not present in input");
            if (addSubscriptionRequest.getSource() == null)
                throw new InvalidInputException("source not present in input");

            log.info("{}", addSubscriptionRequest);
            String tenantUserName = addSubscriptionRequest.getTenantUserName();
            authorizationService.authorizeTenantUserName(tenantUserName);

            FeedbackSubscription feedbackSubscription = subscriptionService.addSubscription(addSubscriptionRequest);
            return ResponseEntity.status(HttpStatus.OK).body(feedbackSubscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/subscription/getAllForTenant", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> getAllSubscriptionForATenant(@RequestParam String tenantUserName) {
        try {
            authorizationService.authorizeTenantUserName(tenantUserName);
            List<FeedbackSubscription> feedbackSubscriptions = subscriptionService.getAllSubscriptionForATenant(tenantUserName);

            return ResponseEntity.status(HttpStatus.OK).body(feedbackSubscriptions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/subscription/getAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<FeedbackSubscription>> getAllSubscription() {
        List<FeedbackSubscription> feedbackSubscriptions = subscriptionService.getAllSubscription();
        return ResponseEntity.status(HttpStatus.OK).body(feedbackSubscriptions);
    }

    @RequestMapping(path = "/subscription/remove", method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> removeSubscription(@RequestBody RemoveSubscriptionRequest removeSubscriptionRequest) {
        try {
            if (removeSubscriptionRequest.getTenantUserName() == null)
                throw new InvalidInputException("tenantUserName not present in input");
            if (removeSubscriptionRequest.getTopic() == null)
                throw new InvalidInputException("topic not present in input");
            if (removeSubscriptionRequest.getSource() == null)
                throw new InvalidInputException("source not present in input");

            FeedbackSubscription feedbackSubscription = subscriptionService.removeSubscription(removeSubscriptionRequest);
            return ResponseEntity.status(HttpStatus.OK).body(feedbackSubscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

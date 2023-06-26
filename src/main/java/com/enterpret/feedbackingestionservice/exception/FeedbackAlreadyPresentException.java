package com.enterpret.feedbackingestionservice.exception;

public class FeedbackAlreadyPresentException extends ServerException {
    public FeedbackAlreadyPresentException() {
        super("Feedback already present");
    }
}

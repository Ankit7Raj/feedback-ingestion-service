package com.enterpret.feedbackingestionservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tenant {
    final String userName;
    final String name;
    final Date registrationDate;
}

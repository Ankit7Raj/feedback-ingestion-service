package com.enterpret.feedbackingestionservice.controller;

import com.enterpret.feedbackingestionservice.model.Tenant;
import com.enterpret.feedbackingestionservice.service.AuthorizationService;
import com.enterpret.feedbackingestionservice.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class TenantController {

    TenantService tenantService;
    AuthorizationService authorizationService;

    @RequestMapping(path = "/tenant/add", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addTenant(@RequestParam String userName, @RequestParam String name) {
        try {
            Tenant tenant = tenantService.addTenant(userName, name);
            return ResponseEntity.status(HttpStatus.OK).body(tenant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/tenant/getAll", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<Tenant>> getAllTenant() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.status(HttpStatus.OK).body(tenants);
    }

    @RequestMapping(path = "/tenant/getByUsername", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> getTenantByUserName(@RequestParam String userName) {
        try {
            authorizationService.authorizeTenantUserName(userName);
            Tenant tenant = tenantService.getTenant(userName);
            return ResponseEntity.status(HttpStatus.OK).body(tenant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

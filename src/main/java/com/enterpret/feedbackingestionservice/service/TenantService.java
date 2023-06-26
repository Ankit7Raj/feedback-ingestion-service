package com.enterpret.feedbackingestionservice.service;

import com.enterpret.feedbackingestionservice.exception.TenantAlreadyRegisteredException;
import com.enterpret.feedbackingestionservice.exception.TenantNotFoundException;
import com.enterpret.feedbackingestionservice.model.Tenant;
import com.enterpret.feedbackingestionservice.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TenantService {

    TenantRepository tenantRepository;

    public Tenant addTenant(String userName, String name) throws TenantAlreadyRegisteredException {
        Tenant tenant = tenantRepository.getTenant(userName);
        if (tenant != null)
            throw new TenantAlreadyRegisteredException(userName);

        Date registrationDate = new Date();
        tenant = new Tenant(userName, name, registrationDate);
        tenantRepository.addTenant(tenant);
        return tenant;
    }

    public Tenant getTenant(String userName) throws TenantNotFoundException {
        Tenant tenant = tenantRepository.getTenant(userName);
        if (tenant == null)
            throw new TenantNotFoundException(userName);

        return tenant;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.getAllTenant();
    }
}

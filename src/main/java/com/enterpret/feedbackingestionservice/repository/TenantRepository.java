package com.enterpret.feedbackingestionservice.repository;

import com.enterpret.feedbackingestionservice.model.Tenant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TenantRepository {
    List<Tenant> tenants;

    public void addTenant(Tenant tenant) {
        tenants.add(tenant);
    }

    public Tenant getTenant(String userName) {
        for (Tenant tenant : tenants) {
            if (tenant.getUserName().equals(userName))
                return tenant;
        }

        return null;
    }

    public List<Tenant> getAllTenant() {
        return tenants;
    }
}

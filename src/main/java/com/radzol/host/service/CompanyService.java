/**
 * 
 */
package com.radzol.host.service;

import java.util.List;

import com.radzol.host.model.Company;

/**
 * @author pradeepan
 *
 */
public interface CompanyService {
    List<Company> findAll();
    Company findByTenantAlias(String tenantAlias);
}

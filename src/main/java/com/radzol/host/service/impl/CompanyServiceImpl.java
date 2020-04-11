/**
 * 
 */
package com.radzol.host.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radzol.host.model.Company;
import com.radzol.host.repository.CompanyRepository;
import com.radzol.host.service.CompanyService;

/**
 * @author pradeepan
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
	this.companyRepository = companyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
	return companyRepository.findAll();
    }
}

/**
 * 
 */
package com.radzol.host.web.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radzol.host.model.Company;
import com.radzol.host.service.CompanyService;

/**
 * @author pradeepan
 *
 */
@RestController
@RequestMapping("/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
	this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAll() {
	return companyService.findAll();
    }

}

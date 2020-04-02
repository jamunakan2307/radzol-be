/**
 * 
 */
package com.radzol.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radzol.host.model.Company;

/**
 * @author pradeepan
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

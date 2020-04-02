/**
 * 
 */
package com.radzol.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radzol.host.model.User;

/**
 * @author pradeepan
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}

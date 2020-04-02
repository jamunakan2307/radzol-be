/**
 * 
 */
package com.radzol.host.service;

import com.radzol.host.model.User;

/**
 * @author pradeepan
 *
 */
public interface UserService {

	User findByEmail(String email);
}

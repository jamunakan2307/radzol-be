/**
 * 
 */
package com.radzol.host.web.authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.radzol.host.security.UserAuthentication;
import com.radzol.host.security.WebUserAuthenticationToken;

/**
 * @author pradeepan
 *
 */
@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDto login(@RequestBody LoginDto loginRequest) {
		WebUserAuthenticationToken auth = new WebUserAuthenticationToken(loginRequest.getUsername(), loginRequest);
		UserAuthentication user = (UserAuthentication) authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(user);

		// optional stuff
		// repository.saveContext(SecurityContextHolder.getContext(), request,
		// response);
		// rememberMeServices.loginSuccess(request, response, auth);

		return UserDtoMapper.INSTANCE.toDto(user);
	}

	@RequestMapping(value = "/clearSession", method = RequestMethod.PUT)
	public boolean clearSession(HttpServletRequest request, HttpServletResponse response) {
		// SecurityContextLogoutHandler does this
		// invalidate the session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// clear authentication
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();

		// CookieClearingLogoutHandler does this
		// clear the cookies
		clearCookie(request, response, "JSESSIONID");

		return true;
	}

	private void clearCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		String cookiePath = request.getContextPath();
		if (!StringUtils.hasLength(cookiePath)) {
			cookiePath = "/";
		}
		cookie.setPath(cookiePath);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}

package com.gmo.test.shared.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.gmo.test.shared.annotation.NoSessionCheck;
import com.gmo.test.shared.constatnt.AppConstants;
import com.gmo.test.shared.controller.InitController;

/**
 * リクエストをコントローラークラスに出す前、傍受してチェックするためのクラス。
 * 
 * @author Joydeep Dey
 */
public class RequestHandleInterceptor implements HandlerInterceptor {

	private static final Logger log = LogManager.getLogger(InitController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI()
				+ getParameters(request));
		boolean isValid = isValidSession(request, handler);

		return isValid;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Proxy
		response.setDateHeader("Expires", 0);
	}

	private static final String[] EXCLUSION_FILES = { ".html", ".ftl", ".js", ".css", ".map", ".gif", ".png", ".jpg" };

	private static boolean isValidSession(HttpServletRequest request, Object handler) throws Exception {
		String servletPath = request.getServletPath();

		// If request files has prefixed extensions, allow & return
		for (int i = 0; i < EXCLUSION_FILES.length; i++) {
			if (servletPath.endsWith(EXCLUSION_FILES[i]))
				return true;
		}

		// If requested for controller method
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			// And the controller doesn't require session check, allow & return
			if (method.getMethod().isAnnotationPresent(NoSessionCheck.class)
					|| method.getMethod().getDeclaringClass().isAnnotationPresent(NoSessionCheck.class)) {
				return true;
			}
		}

		// For the rest
		// Get User ID from from the session of this request
		Object sessionUserId = WebUtils.getSessionAttribute(request, "login-id");
		boolean isValid = (sessionUserId != null);

		// Set the session validation status for root(/) path redirection
		request.setAttribute("user-validation", isValid);

		if (isValid)
			return isValid;

		log.warn("Aceess to path : " + servletPath + " detected, without login.");

		// For any condition, root path is always allowed
		if (servletPath.equals("/"))
			return true;

		// For any other situation or request, do not allow
		return false;
	}

	private static String getParameters(HttpServletRequest request) {
		StringBuilder posted = new StringBuilder();
		Enumeration<?> e = request.getParameterNames();
		if (e != null) {
			posted.append("?");

			while (e.hasMoreElements()) {
				if (posted.length() > 1) {
					posted.append("&");
				}
				String curr = (String) e.nextElement();
				posted.append(curr + "=");
				posted.append(request.getParameter(curr));
			}
		}
		String ipAddr = null;
		for (int i = 0; i < AppConstants.REQ_HEADER_IP.length; i++) {
			ipAddr = request.getHeader(AppConstants.REQ_HEADER_IP[i]);
			if (ipAddr != null && ipAddr.length() > 0 && !ipAddr.equalsIgnoreCase("unknown"))
				break;
		}
		if (ipAddr == null || ipAddr.length() == 0 || ipAddr.equalsIgnoreCase("unknown")) {
			ipAddr = request.getRemoteAddr();
		}
		if (ipAddr != null && !ipAddr.equals("")) {
			posted.append("&ip=" + ipAddr);
		}
		return posted.toString();
	}
}

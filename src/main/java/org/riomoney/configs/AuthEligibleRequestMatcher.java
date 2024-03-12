package org.riomoney.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class AuthEligibleRequestMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/users") || path.contains("/groups/") || path.contains("/messages/");
    }
}

package org.riomoney.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class MyRequestMatcher  implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        String path = request.getServletPath(); // Get the servlet path of the request

        // Check if the request path matches "/users", "/groups/**", or "/messages/**"
        return path.equals("/users") || path.contains("/groups/") || path.contains("/messages/");
    }
}

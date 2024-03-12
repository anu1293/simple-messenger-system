package org.riomoney.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String createToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}

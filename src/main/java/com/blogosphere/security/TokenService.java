package com.blogosphere.security;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Instant;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private static final String DOMAIN = "com.blogosphere";

  private final JwtEncoder encoder;

  public String generateToken(Authentication authentication) {
    var tokenIssuedTime = Instant.now();
    var claims = claims(tokenIssuedTime, authentication);
    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  private JwtClaimsSet claims(Instant tokenIssuedTime, Authentication authentication) {
    return JwtClaimsSet.builder()
        .issuer(DOMAIN)
        .issuedAt(tokenIssuedTime)
        .expiresAt(tokenIssuedTime.plus(1, DAYS))
        .subject(authentication.getName())
        .claim("roles", roles(authentication))
        .build();
  }

  private String roles(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(", "));
  }
}

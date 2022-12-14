package com.blogosphere.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  private SecurityUtil() {
  }

  public static String currentLoggedInUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}

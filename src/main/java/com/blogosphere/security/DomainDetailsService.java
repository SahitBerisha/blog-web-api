package com.blogosphere.security;

import com.blogosphere.core.RoleName;
import com.blogosphere.users.Role;
import com.blogosphere.users.User;
import com.blogosphere.users.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainDetailsService implements UserDetailsService {

  private static final String ROLE = "ROLE_";
  private final UserRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    return repository.findByUsername(username)
        .map(this::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  private UserDetails toUserDetails(User user) {
    return new AuthenticatedUser(
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        authorities(user));
  }

  private List<SimpleGrantedAuthority> authorities(User user) {
    return user.getRoles().stream().map(Role::getName).map(this::map).toList();
  }

  private SimpleGrantedAuthority map(RoleName roleName) {
    return new SimpleGrantedAuthority(ROLE + roleName.toString());
  }
}

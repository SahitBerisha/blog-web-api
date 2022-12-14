package com.blogosphere.users;

import com.blogosphere.core.RoleName;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public User map(UserRequest request) {
    var user = new User();
    map(user, request);
    user.setPassword(encodedPassword(request));
    return user;
  }

  public UserResponse map(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .fullName(user.fullName())
        .username(user.getUsername())
        .email(user.getEmail())
        .dateOfBirth(user.getDateOfBirth())
        .gender(user.getGender())
        .phoneNumber(user.getPhoneNumber())
        .website(user.getWebsite())
        .address(user.getAddress())
        .roles(roles(user))
        .build();
  }

  public void map(User user, UserRequest request) {
    user.setFirstName(request.firstName());
    user.setLastName(request.lastName());
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setDateOfBirth(request.dateOfBirth());
    user.setGender(request.gender());
    user.setPhoneNumber(request.phoneNumber());
    user.setWebsite(request.website());
    user.setAddress(address(request));
  }

  private String encodedPassword(UserRequest request) {
    return passwordEncoder.encode(request.password());
  }

  private Address address(UserRequest request) {
    var address = new Address();
    address.setCountry(request.country());
    address.setCity(request.city());
    address.setZipCode(request.zipCode());
    return address;
  }

  private List<String> roles(User user) {
    return user.getRoles().stream()
        .map(Role::getName)
        .map(RoleName::toString)
        .toList();
  }
}

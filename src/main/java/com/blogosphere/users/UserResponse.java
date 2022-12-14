package com.blogosphere.users;

import com.blogosphere.core.Gender;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {

  String id;
  String fullName;
  String username;
  String email;
  LocalDate dateOfBirth;
  Gender gender;
  String phoneNumber;
  String website;
  Address address;
  List<String> roles;
}

record TokenResponse(String user, String token) {

}

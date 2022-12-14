package com.blogosphere.users;

import com.blogosphere.core.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String firstName,
                          @NotBlank String lastName,
                          @NotBlank String username,
                          @NotBlank @Email String email,
                          @NotBlank String password,
                          LocalDate dateOfBirth,
                          Gender gender,
                          String phoneNumber,
                          String website,
                          String country,
                          String city,
                          String zipCode) {

}

package com.blogosphere.users;

import com.blogosphere.security.TokenService;
import com.blogosphere.users.exception.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  private final DaoAuthenticationProvider authenticationManager;
  private final TokenService tokenService;
  private final UserRepository repository;
  private final UserMapper mapper;

  @Transactional(readOnly = true)
  public TokenResponse authenticate(HttpServletRequest request) {
    var username = request.getParameter(USERNAME);
    var password = request.getParameter(PASSWORD);
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
    var token = tokenService.generateToken(authentication);
    return new TokenResponse(username, token);
  }

  @Transactional
  public UserResponse create(UserRequest request) {
    var user = mapper.map(request);
    repository.save(user);
    return mapper.map(user);
  }

  @Transactional(readOnly = true)
  public UserResponse getOne(String id) {
    var user = findUser(id);
    return mapper.map(user);
  }

  @Transactional
  public UserResponse update(String id, UserRequest request) {
    var user = findUser(id);
    mapper.map(user, request);
    return mapper.map(user);
  }

  @Transactional
  public void delete(String id) {
    repository.deleteById(id);
  }

  private User findUser(String id) {
    return repository.findById(id)
        .orElseThrow(() -> UserNotFoundException.withId(id));
  }
}

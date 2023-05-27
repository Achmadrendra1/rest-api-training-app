package com.example.aeon.service;

import com.example.aeon.config.OAuth2AuthorizationConfig;
import com.example.aeon.models.Users;
import com.example.aeon.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  private final PasswordEncoder passwordEncoder = OAuth2AuthorizationConfig.passwordEncoder();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = this.userRepository.findByEmail(username);

    if (user == null)
      throw new UsernameNotFoundException(String.format("No user found with username '%s'", username));
    return user;
  }

  public Boolean hasProtectedAccess() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
        .contains(new SimpleGrantedAuthority("ADMIN"));
  }

  public Users saveUser(Users user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setAuthorities("ADMIN");
    user.setEnabled(true);
    return userRepository.save(user);
  }

}

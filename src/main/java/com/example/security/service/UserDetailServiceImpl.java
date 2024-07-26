package com.example.security.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.persistence.entity.UserEntity;
import com.example.security.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    userEntity.getRoles()
        .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

    userEntity.getRoles().stream()
        .flatMap(role -> role.getPermissions().stream())
        .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

    return new User(
        userEntity.getUsername(),
        userEntity.getPassword(),
        userEntity.isEnabled(),
        userEntity.isAccountNoExpired(),
        userEntity.isCredentialsNoExpired(),
        userEntity.isAccountNoLocked(),
        authorityList);

  }

}

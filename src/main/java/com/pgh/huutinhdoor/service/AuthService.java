package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.user.UserLogin;
import com.pgh.huutinhdoor.dto.response.AuthResponse;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.enums.UserRole;
import com.pgh.huutinhdoor.mapper.UserMapper;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final UserClientService userClientService;

    @Transactional
    public UserResponse register(UserCreateRequest req) {
        UserServiceBase.UserWithAvatar result = userClientService.create(req);

        return userMapper.toResponseAvatar(
                result.user(),
                result.avatarUrl()
        );
    }


    public AuthResponse login(UserLogin req) {

        User user = userRepository.findByPhone(req.getPhone())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        if (!user.getIsActive()) {
            throw new RuntimeException("User is inactive");
        }

        String token = jwtService.generateAccessToken(
                new org.springframework.security.core.userdetails.User(
                        user.getPhone(),
                        user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
                )
        );

        String refreshToken = refreshTokenService.createRefreshToken(user.getPhone());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .username(user.getPhone())
                .role(user.getRole())
                .build();
    }
}

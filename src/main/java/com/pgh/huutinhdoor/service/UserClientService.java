package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.UserUpdateRequest;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.enums.UserRole;
import com.pgh.huutinhdoor.exception.DuplicateResourceException;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.UserMapper;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserClientService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

// private String phone;
//    private String password;
//    private String email;


    @Transactional
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Phone already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        User user = new User();
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(UserRole.USER);
        user.setIsActive(true);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse update(UserUpdateRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found with id " + id));

        validateDuplicate(request, user);
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found with id " + id));
        user.setIsActive(false);
    }

    private void validateDuplicate(UserUpdateRequest request, User user) {

        if (request.getEmail() != null &&
                !request.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException("Email already exists");
        }
        if (request.getPhone() != null &&
                !request.getPhone().equals(user.getPhone()) &&
                userRepository.existsByPhone(request.getPhone())) {

            throw new DuplicateResourceException("Phone already exists");
        }
    }
}

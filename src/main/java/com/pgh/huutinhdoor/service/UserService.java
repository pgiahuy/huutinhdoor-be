package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.UserUpdateRequest;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.mapper.UserMapper;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
       return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findByIdOrThrow(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found with id " + id));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse update(UserUpdateRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found with id " + id));
        EntityUtil.copyNoNullProperties(request, user);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }







}

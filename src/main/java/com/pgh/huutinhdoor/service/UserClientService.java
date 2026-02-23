package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.user.ClientUserUpdateRequest;
import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.entity.Customer;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.exception.BadRequestException;
import com.pgh.huutinhdoor.mapper.UserMapper;
import com.pgh.huutinhdoor.repository.CustomerRepository;
import com.pgh.huutinhdoor.repository.ImageRepository;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserClientService extends UserServiceBase {

    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;

    public UserClientService(UserRepository userRepository, ImageRepository imageRepository,
                             ImageService imageService, PasswordEncoder passwordEncoder,
                             CustomerRepository customerRepository, UserMapper userMapper) {

        super(userRepository, imageRepository, imageService, passwordEncoder);
        this.customerRepository = customerRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserWithAvatar getWithAvatar(Long id) {
        return super.getWithAvatar(id);
    }


    @Transactional
    public UserWithAvatar create(UserCreateRequest request) {
        checkDuplicate(request.getEmail(), request.getPhone(), null);

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        Customer customer = customerRepository.findByPhone(request.getPhone())
                .orElseGet(() -> customerRepository.save(Customer.builder()
                        .phone(request.getPhone())
                        .build()));
        user.setCustomer(customer);
        User savedUser = userRepository.save(user);

        String avatarUrl = handleAvatar(savedUser, request.getAvatar());
        return new UserWithAvatar(savedUser, avatarUrl);
    }

    @Transactional
    public UserWithAvatar update(Long id, ClientUserUpdateRequest request) {
        User user = findByIdOrThrow(id);
        checkDuplicate(request.getEmail(), request.getPhone(), user);
        EntityUtil.copyNoNullProperties(request, user);
        userRepository.save(user);

        String avatarUrl = handleAvatar(user, request.getAvatar());
        return new UserWithAvatar(user, avatarUrl);
    }

    @Transactional
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = findByIdOrThrow(userId);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = findByIdOrThrow(id);
        user.setIsActive(false);
        userRepository.save(user);
    }
}
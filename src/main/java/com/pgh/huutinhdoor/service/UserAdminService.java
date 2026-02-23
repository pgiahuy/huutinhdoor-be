package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.user.UserUpdateRequest;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.mapper.UserMapper;
import com.pgh.huutinhdoor.repository.ImageRepository;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.GlobalConstants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminService extends UserServiceBase {

    private final UserMapper userMapper;

    public UserAdminService(UserRepository userRepository, ImageRepository imageRepository, ImageService imageService, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        super(userRepository, imageRepository, imageService, passwordEncoder);
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserWithAvatar getWithAvatar(Long id) {
        return super.getWithAvatar(id);
    }

    @Transactional(readOnly = true)
    public List<UserWithAvatar> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    String avatarUrl = imageRepository
                            .findByTargetIdAndTargetTypeAndIsPrimaryTrue(user.getId(), TargetType.USER)
                            .map(Image::getUrl)
                            .orElse(GlobalConstants.USER_AVATAR);
                    return new UserWithAvatar(user, avatarUrl);
                })
                .toList();
    }

    @Transactional
    public UserWithAvatar create(UserCreateRequest request) {
        checkDuplicate(request.getEmail(), request.getPhone(), null);

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        String avatarUrl = handleAvatar(savedUser, request.getAvatar());
        return new UserWithAvatar(savedUser, avatarUrl);
    }

    @Transactional
    public UserWithAvatar update(Long id, UserUpdateRequest request) {
        return super.update(id, request);
    }

    @Transactional
    public void delete(Long id) {
        User user = findByIdOrThrow(id);
        user.setIsActive(false);
        userRepository.save(user);
    }
}
package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.request.user.UserUpdateRequest;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.entity.User;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.enums.UploadFolder;
import com.pgh.huutinhdoor.exception.DuplicateResourceException;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.repository.ImageRepository;
import com.pgh.huutinhdoor.repository.UserRepository;
import com.pgh.huutinhdoor.util.EntityUtil;
import com.pgh.huutinhdoor.util.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
public abstract class UserServiceBase {

    protected final UserRepository userRepository;
    protected final ImageRepository imageRepository;
    protected final ImageService imageService;
    protected final PasswordEncoder passwordEncoder;

    public record UserWithAvatar(User user, String avatarUrl) {}


    protected void copyAndEncodePassword(UserUpdateRequest request, User user) {
        EntityUtil.copyNoNullProperties(request, user, "password");
        if(request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }

    protected String handleAvatar(User user, MultipartFile avatar) {
        var avatarUrl = imageRepository
                .findByTargetIdAndTargetTypeAndIsPrimaryTrue(user.getId(), TargetType.USER)
                .map(Image::getUrl)
                .orElse(GlobalConstants.USER_AVATAR);

        if(avatar != null && !avatar.isEmpty()) {
            Image image = imageService.replacePrimaryImage(
                    user.getId(), TargetType.USER, avatar, UploadFolder.USER
            );
            avatarUrl = image.getUrl();
        }
        return avatarUrl;
    }

    public UserWithAvatar getWithAvatar(Long id) {
        User user = findByIdOrThrow(id);
        String avatarUrl = imageRepository
                .findByTargetIdAndTargetTypeAndIsPrimaryTrue(id, TargetType.USER)
                .map(Image::getUrl)
                .orElse(GlobalConstants.USER_AVATAR);
        return new UserWithAvatar(user, avatarUrl);
    }

    public UserWithAvatar update(Long id, UserUpdateRequest request) {
        User user = findByIdOrThrow(id);
        checkDuplicate(request.getEmail(), request.getPhone(), user);
        copyAndEncodePassword(request, user);
        userRepository.save(user);
        String avatarUrl = handleAvatar(user, request.getAvatar());
        return new UserWithAvatar(user, avatarUrl);
    }

    protected User findByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    protected void checkDuplicate(String email, String phone, User existingUser) {
        if (email != null) {
            boolean emailExists = (existingUser == null)
                    ? userRepository.existsByEmail(email)
                    : !email.equals(existingUser.getEmail()) && userRepository.existsByEmail(email);
            if (emailExists) throw new DuplicateResourceException("Email already exists");
        }

        if (phone != null) {
            boolean phoneExists = (existingUser == null)
                    ? userRepository.existsByPhone(phone)
                    : !phone.equals(existingUser.getPhone()) && userRepository.existsByPhone(phone);
            if (phoneExists) throw new DuplicateResourceException("Phone already exists");
        }
    }
}
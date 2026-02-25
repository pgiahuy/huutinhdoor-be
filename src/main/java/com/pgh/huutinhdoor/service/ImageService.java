package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.enums.UploadFolder;
import com.pgh.huutinhdoor.exception.BadRequestException;
import com.pgh.huutinhdoor.exception.ResourceNotFoundException;
import com.pgh.huutinhdoor.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    public Image replacePrimaryImage(Long targetId, TargetType targetType,
                                    MultipartFile file, UploadFolder folder ) {

        imageRepository
                .findByTargetIdAndTargetTypeAndIsPrimaryTrue(targetId, targetType)
                .ifPresent(oldImage -> {
                    cloudinaryService.deleteFile(oldImage.getPublicId());
                    imageRepository.delete(oldImage);
                });

        CloudinaryResponse response = cloudinaryService.uploadFile(file, folder);

        Image image = Image.builder()
                .url(response.getSecure_url())
                .publicId(response.getPublic_id())
                .isPrimary(true)
                .targetId(targetId)
                .targetType(targetType)
                .build();
        return imageRepository.save(image);
    }

    public void deleteAllByTarget(Long targetId, TargetType type) {
        List<Image> images = imageRepository.findAllByTargetIdAndTargetType(targetId, type);

        for (Image image : images) {
            cloudinaryService.deleteFile(image.getPublicId());
            imageRepository.delete(image);
        }
    }

    public void deleteById(Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imageId));

        cloudinaryService.deleteFile(image.getPublicId());
        imageRepository.delete(image);
    }


    public Image uploadAdditionalImage(Long targetId, TargetType targetType, MultipartFile file, UploadFolder folder) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }

        CloudinaryResponse response = cloudinaryService.uploadFile(file, folder);

        Image image = Image.builder()
                .targetId(targetId)
                .targetType(targetType)
                .url(response.getSecure_url())
                .publicId(response.getPublic_id())
                .isPrimary(false)
                .build();

        return imageRepository.save(image);
    }
}

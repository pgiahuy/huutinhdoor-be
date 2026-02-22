package com.pgh.huutinhdoor.service;

import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.entity.Image;
import com.pgh.huutinhdoor.enums.TargetType;
import com.pgh.huutinhdoor.enums.UploadFolder;
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

    public void replacePrimaryImage(Long targetId, TargetType targetType,
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

        imageRepository.save(image);
    }

    public void deleteAllByTarget(Long targetId, TargetType type) {
        List<Image> images = imageRepository.findAllByTargetIdAndTargetType(targetId, type);

        for (Image image : images) {
            cloudinaryService.deleteFile(image.getPublicId());
            imageRepository.delete(image);
        }
    }
}

package com.pgh.huutinhdoor.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pgh.huutinhdoor.config.CloudinaryConfig;
import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.enums.UploadFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;
    private final CloudinaryConfig cloudinaryConfig;

    public List<?> uploadFiles(MultipartFile[] files, UploadFolder folder) {

        if (Arrays.stream(files).allMatch(MultipartFile::isEmpty)) {
            return Collections.singletonList(new CloudinaryResponse("","","Không có file nào được chọn"));
        }
        return Arrays.stream(files)
                .map(file -> {
                    if (file.isEmpty()) {
                        return new CloudinaryResponse("","","File trống: " +
                                (file.getOriginalFilename() != null ? file.getOriginalFilename() : ""));
                    }
                    if (file.getSize() > cloudinaryConfig.getMaxSize()) {
                        return new CloudinaryResponse("","","File quá lớn (max 10MB): " +
                                file.getOriginalFilename());
                    }
                    try {
                        Map<String, Object> data = upload(file, folder);
                        return new CloudinaryResponse(
                                Objects.toString(data.get("secure_url"), ""),
                                Objects.toString(data.get("public_id"), ""),
                                "Upload thành công"
                        );
                    } catch (RuntimeException e) {
                        return new CloudinaryResponse("","","Upload thất bại: " + file.getOriginalFilename());
                    }
                })
                .toList();
    }


    public Map upload(MultipartFile file, UploadFolder folder) {
        try {
            return cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", cloudinaryConfig.getRootPath() + "/" + folder.getPath(),
                            "resource_type", "auto"
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("Upload ảnh thất bại");
        }
    }
}

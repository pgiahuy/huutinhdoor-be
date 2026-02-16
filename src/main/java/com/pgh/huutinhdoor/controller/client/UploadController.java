package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.enums.UploadFolder;
import com.pgh.huutinhdoor.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam String folder) {

        UploadFolder  uploadFolder;
        try {
            uploadFolder = UploadFolder.valueOf(folder.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonList(
                            new CloudinaryResponse("","","Folder không hợp lệ")
                    ));
        }
        List<?> results = cloudinaryService.uploadFiles(files, uploadFolder);
        return ResponseEntity.ok(results);
    }

}

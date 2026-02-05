package com.pgh.huutinhdoor.controller;

import com.pgh.huutinhdoor.dto.response.CloudinaryResponse;
import com.pgh.huutinhdoor.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<CloudinaryResponse> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> data = this.cloudinaryService.upload(file);

        return ResponseEntity.ok(
                new CloudinaryResponse(
                        data.get("secure_url").toString(),
                        data.get("public_id").toString()
                )
        );

    }
}

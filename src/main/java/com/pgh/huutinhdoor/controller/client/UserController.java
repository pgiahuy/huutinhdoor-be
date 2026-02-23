package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.request.user.ChangePasswordRequest;
import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.user.UserUpdateRequest;
import com.pgh.huutinhdoor.service.UserClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserClientService  userClientService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        var userWithAvatar = userClientService.getWithAvatar(id);
        return ResponseEntity.ok(userWithAvatar);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest request) {
        var userWithAvatar = userClientService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/admin/users"+userWithAvatar.user().getId()))
                .body(userWithAvatar);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        var userWithAvatar = userClientService.update(id, request);
        return ResponseEntity.ok(userWithAvatar);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        userClientService.changePassword(id, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

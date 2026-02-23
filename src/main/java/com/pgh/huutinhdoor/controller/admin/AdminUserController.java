package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.user.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.user.UserUpdateRequest;
import com.pgh.huutinhdoor.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private  UserAdminService  userAdminService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        var userWithAvatar = userAdminService.getWithAvatar(id);
        return ResponseEntity.ok(userWithAvatar);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        var users = userAdminService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest request) {
        var userWithAvatar = userAdminService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/admin/users"+userWithAvatar.user().getId()))
                .body(userWithAvatar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        var userWithAvatar = userAdminService.update(id, request);
        return ResponseEntity.ok(userWithAvatar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userAdminService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

package com.pgh.huutinhdoor.controller.admin;

import com.pgh.huutinhdoor.dto.request.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.UserUpdateRequest;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private  final UserAdminService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> result =  userService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserResponse result =  userService.findByIdOrThrow(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request){
        UserResponse result =  userService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/admin/users/"+result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        UserResponse result =  userService.update(request, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

package com.pgh.huutinhdoor.controller.client;

import com.pgh.huutinhdoor.dto.request.UserCreateRequest;
import com.pgh.huutinhdoor.dto.request.UserUpdateRequest;
import com.pgh.huutinhdoor.dto.response.UserResponse;
import com.pgh.huutinhdoor.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserAdminService userService;

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
        return null;
    }
}

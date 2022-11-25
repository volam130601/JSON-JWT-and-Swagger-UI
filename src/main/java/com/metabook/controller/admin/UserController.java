package com.metabook.controller.admin;

import com.metabook.dto.ResponseObject;
import com.metabook.entity.User;
import com.metabook.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {
    private final UserRepository userRepository;

    @Operation(description = "Xem danh sách User", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))), responseCode = "200")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode = "403", description = "Truy cập bị cấm"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy")
    })

    @GetMapping("/users")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return ResponseEntity.ok(new ResponseObject(userRepository.findAll(), "Find All Users", "SUCCESS"));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }
}
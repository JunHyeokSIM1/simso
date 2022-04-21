package com.simso.controller.api;

import com.simso.dto.UserResponseDto;
import com.simso.dto.UserSaveRequestDto;
import com.simso.dto.UserUpdateRequestDto;
import com.simso.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.register(requestDto);
    }

    @PutMapping("/api/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(id,requestDto);
    }

    @GetMapping("/api/user/{id}")
    public UserResponseDto findById (@PathVariable Long id){
        return userService.findById(id);
    }
}

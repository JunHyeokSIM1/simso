package com.simso.Controller.api;

import com.simso.dto.UserResponseDto;
import com.simso.dto.UserUpdateRequestDto;
import com.simso.dto.post.PostsResponseDto;
import com.simso.dto.post.PostsSaveRequestDto;
import com.simso.dto.post.PostsUpdateRequestDto;
import com.simso.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostService postService;

    @PostMapping("api/posts/")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PutMapping("/api/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @GetMapping("/api/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postService.findById(id);
    }
}

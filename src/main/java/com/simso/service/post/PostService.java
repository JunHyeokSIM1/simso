package com.simso.service.post;

import com.simso.domain.Posts;
import com.simso.domain.User;
import com.simso.dto.UserResponseDto;
import com.simso.dto.UserUpdateRequestDto;
import com.simso.dto.post.PostsResponseDto;
import com.simso.dto.post.PostsSaveRequestDto;
import com.simso.dto.post.PostsUpdateRequestDto;
import com.simso.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 게시글 없습니다.id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없습니다.id=" + id));

        return new PostsResponseDto(posts);
    }
}

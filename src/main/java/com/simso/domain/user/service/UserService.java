package com.simso.domain.user.service;

import com.simso.domain.user.entity.User;
import com.simso.domain.user.dto.UserResponseDto;
import com.simso.domain.user.dto.UserSaveRequestDto;
import com.simso.domain.user.dto.UserUpdateRequestDto;
import com.simso.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Long register(UserSaveRequestDto requestDto){

        getValidateDuplicateUser(requestDto);

        return userRepository.save(requestDto.toEntity()).getId();
    }


    private  void getValidateDuplicateUser(UserSaveRequestDto requestDto){
        userRepository.findByUsername(requestDto.getUsername())
                .ifPresent(m ->{
                    throw new IllegalStateException("user 이름이 중복됩니다");
                });

    }

    public List<User> findUser() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    public void DeleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        userRepository.delete(user.get());
    }


    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 id가 없습니다.id=" + id));

        user.update(requestDto.getUsername(), requestDto.getPassword());
        return id;
    }

    public UserResponseDto findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 id가 없습니다.id=" + id));

        return new UserResponseDto(user);
    }
}

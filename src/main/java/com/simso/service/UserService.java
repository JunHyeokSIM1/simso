package com.simso.service;

import com.simso.domain.Item;
import com.simso.domain.User;
import com.simso.dto.UserResponseDto;
import com.simso.dto.UserSaveRequestDto;
import com.simso.dto.UserUpdateRequestDto;
import com.simso.repository.UserRepository;
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
        userRepository.findByname(requestDto.getUsername())
                .ifPresent(m ->{
                    throw new IllegalStateException("user 이름이 중복됩니다");
                });

    }

    public List<User> findUser() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long id) {
        return userRepository.findByid(id);
    }

    public void DeleteUser(Long userId) {
        userRepository.delete(userId);
    }


    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findByid(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 id가 없습니다.id=" + id));

        user.update(requestDto.getUsername(), requestDto.getPassword());
        return id;
    }

    public UserResponseDto findById(Long id){
        User user = userRepository.findByid(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 id가 없습니다.id=" + id));

        return new UserResponseDto(user);
    }
}

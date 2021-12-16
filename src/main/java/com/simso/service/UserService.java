package com.simso.service;

import com.simso.domain.Item;
import com.simso.domain.User;
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
    public Long register(User user){

        getValidateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }


    private  void getValidateDuplicateUser(User user){
        userRepository.findByname(user.getUsername())
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


}

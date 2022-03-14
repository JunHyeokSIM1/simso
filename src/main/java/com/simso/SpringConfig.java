package com.simso;


import com.simso.repository.*;
import com.simso.service.ItemService;
import com.simso.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    private final UserRepository userRepository;


    public SpringConfig(DataSource dataSource, EntityManager em, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.userRepository = userRepository;

    }

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }


    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(em);

    }

//    @Bean
//    public UserRepositoryOld userRepository() {
//        return new JpaUserRepository(em);
//
//    }
}

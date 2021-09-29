package com.simso;


import com.simso.Controller.MemberForm;
import com.simso.repository.ItemRepository;
import com.simso.repository.JpaItemRepository;
import com.simso.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;


    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;

    }
    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository(){
        return  new JpaItemRepository(em);

    }}

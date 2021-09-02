package com.simso.simso.repository;

import com.simso.simso.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void  afterEach(){
        repository.clearStrore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result =  repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("spring-findName");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("spring-findName2");
        repository.save(member1);

        Member result = repository.findByName("spring-findName").get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll(){
        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("spring3");
        repository.save(member4);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        
    }
}
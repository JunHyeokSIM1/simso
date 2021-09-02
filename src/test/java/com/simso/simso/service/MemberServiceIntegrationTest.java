package com.simso.simso.service;

import com.simso.simso.domain.Member;
import com.simso.simso.repository.MemberRepository;
import com.simso.simso.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

   @Autowired MemberService memberService;
   @Autowired
   MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("service");

        //when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("spring");
        Member member1 = new Member();
        member1.setName("spring");
        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->memberService.join(member1));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}
package com.simso.simso.service;

import com.simso.simso.domain.Member;
import com.simso.simso.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void AfterEach(){
        memberRepository.clearStrore();
    }

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
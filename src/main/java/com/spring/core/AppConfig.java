package com.spring.core;

import com.spring.core.discount.DiscountPolicy;
import com.spring.core.discount.FixDiscountPolicy;
import com.spring.core.discount.RateDiscountPolicy;
import com.spring.core.member.MemberService;
import com.spring.core.member.MemberServiceImpl;
import com.spring.core.member.MemoryMemberRepository;
import com.spring.core.order.OrderService;
import com.spring.core.order.OrderServiceImpl;

/**
 * 나의 애플리케이션 전체를 설정하고 구성
 * IoC 를 일으키고 있다. = IoC 컨테이너 or DI 컨테이너
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

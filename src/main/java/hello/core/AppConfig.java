package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 목표  : 중복을 제거하고 역할에 따른 구현이 보이도록 refactoring한다.

public class AppConfig {
    // 실제 구현 객체를 여기서 생성한다.
    // MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPoicy 다 생성
    // 그리고 여기서 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
//        return new MemberServiceImpl();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}

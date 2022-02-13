package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.DirectoryStream;


// 목표  : 중복을 제거하고 역할에 따른 구현이 보이도록 refactoring 한다.
@Configuration
public class AppConfig {
    // 실제 구현 객체를 여기서 생성한다.
    // MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPoicy 다 생성
    // 그리고 여기서 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.

    // 각 메소드에 Bean이라고 등록하면, 스프링 컨테이너에 등록이 된다.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
//        return new MemberServiceImpl();
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}

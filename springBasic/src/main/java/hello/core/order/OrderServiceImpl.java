package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
//// final이 붙은 필드를 받는 생성자를 자동으로 만들어준다.
//// 의존관계 추가할 때 매우 편리하다.
public class OrderServiceImpl implements OrderService{
    // 코드가 간결하지만 외부에서 변경이 불가능해서 테스트가 아주 어렵다.
    // 따라서 안티패턴으로 분리되는 필드 주입.
//     @Autowired private MemberRepository memberRepository;
//     @Autowired private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 할인 정책 변경하려면
    // private final DiscountPolicy discountPolicy = new RateDiscountpolilcy();로 변경해주어야 한다.
    // 즉 우리는 클라이언트인 orderServiceImpl의 코드를 고쳐야한다.
    // 구체(구현) 클래스에도 의존하고 있는 상황!

    // 인터페이스에만 의존하도록 설계와 코드를 변경
    // 이대로만 하면 Null Pointer Exception이 발생하므로 누군가 OrderServiceImpl에
    // DiscountPolicy의 구현 객체를 대신 생성하고 주입해야한다.
    // --> 이에 따른 AppConfig 등장 (구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스)
    // == 롬복이 해당 코드 대체 가능
    @Autowired
    // 같은 qualifier를 찾아서 주입해준다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberid, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberid);
        // 멤버 찾고
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 설계가 잘된 부분.
        // 할인에 대한거는 discountPolicy한테 기냥 바로 넘겨버렸으므로
        // 단일 설계를 잘 지킨 것!! 할인을 고쳐야하면 discount 부분만 고치면 되니까

        // Order만들어서 반환해주면 된다.
        // 그러면 OrderService의 역할은 끝.
        return new Order(memberid, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

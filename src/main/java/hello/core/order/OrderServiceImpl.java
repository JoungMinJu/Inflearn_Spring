package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


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
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
}

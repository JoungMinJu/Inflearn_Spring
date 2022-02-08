package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


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

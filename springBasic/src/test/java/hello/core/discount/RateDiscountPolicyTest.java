package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// static import 해놓으면 assertions 쓸 때마다 import 할 필요가 없음.
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){//vip가 잘 적용이 된다면
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
        }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야한다.")
    // 실패테스트도 필요
    void vip_x(){//vip가 잘 적용이 된다면
        //given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(0);
        // 원랜 0원이 되어야한다.
    }

}
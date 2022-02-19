package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(userA, 10000, "fixDiscountPolicy");

        // discountService가 잘 들어갔니
        assertThat(discountService).isInstanceOf(DiscountService.class);
        // 할인 메소드 확인
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(userA, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

        static class DiscountService{
            private final Map<String, DiscountPolicy> policyMap;
            // 테스트 삼아서 list로 받아보기
            private final List<DiscountPolicy> policies;

            @Autowired
            public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
                this.policyMap = policyMap;
                this.policies = policies;
            }

            public int discount(Member member, int price, String discountCode) {
                // 할인 코드를 빈 이름과 매칭
                // 키 값이 fixDiscountPolicy가 되겠지
                DiscountPolicy discountPolicy = policyMap.get(discountCode);
                return discountPolicy.discount(member, price);
            }
    }
}

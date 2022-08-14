package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A에서 A사용자가 10,000원을 주문함
        statefulService1.order("userA", 10000);
        // Thread B에서 B사용자가 20,000원을 주문함
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        // 의도는 10,000원이 나와야한다.
        System.out.println("price = " + price);
        // 근데 20,000원 나옴.

    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
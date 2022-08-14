package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class singletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    // 문제점을 볼 것
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // JVM 메모리에 조회할 때마다 계속 객체가 생성되어 올라가게 된다.

        // 검증.
        // 테스트는 출력을 눈으로 보는 것이 아니라 자동화 되도록 만들어야한다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    // 싱글톤 만들고 테스트
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService2 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // is equal이랑 is same as
        // same = 객체 인스턴스가 같은지
        // equal = 모양새가 같은지 . .! equals 메소드 쓰는거
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    // 근데 스프링컨테이너를 쓰면 알아서 싱글톤으로 만들어준다.

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        // 참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // JVM 메모리에 조회할 때마다 계속 객체가 생성되어 올라가게 된다.


        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}

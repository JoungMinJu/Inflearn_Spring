package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        // 같은 타입이 여러개면 좀 곤란해짐.
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구현체 타입으로 조회")
    // 상단은 인터페이스로 조회함
    // 근데 역할에 의존하는 것이 아니므로 이론적으로는 이상적이지 않음.
    void findBeanByName2(){
        // 같은 타입이 여러개면 좀 곤란해짐.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByNameX(){
//        MemberService xxx = ac.getBean("xxx", MemberService.class);
        // 이 예외가 터져야 test가 성공하는 것
        // ac. 어쩌구 로직이 실행하면 저 예외가 터져야 성공하는 테스트
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxx", MemberService.class));
        // 해당 NoSuchBeanDefinitionException 에러가 뜬다.
    }
}

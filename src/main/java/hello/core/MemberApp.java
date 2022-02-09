package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 코드로 테스트해보기 위한 클래스
public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
        // 멤버 서비스  인터페이스 받고
        // new memberserviceimpl 객체 생성하면서 memory member repository 받음.
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext == 스프링 컨테이너(bean관리)
        // appconfig의 환경설정 정보를 가지고 스프링이 컨테이너에 객체 생성해서 다 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // (이름, 타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

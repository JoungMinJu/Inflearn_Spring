package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

// 코드로 테스트해보기 위한 클래스
public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        // 멤버 서비스  인터페이스 받고
        // new memberserviceimpl 객체 생성하면서 memory member repository 받음.
        MemberService memberService = appConfig.memberService();

//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

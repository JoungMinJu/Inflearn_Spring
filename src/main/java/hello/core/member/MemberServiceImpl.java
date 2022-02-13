package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 회원을 찾으려면 repository가 필요
    // 인터페이스 = new 객체
    private final MemberRepository memberRepository;


    // memberRepository의 구현체를 생성자를 통해서생성
    // 따라서 추상화에대해서만 의존하게 된다.
    // 생성자 주입
    @Autowired
    // 생성자에 붙여준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

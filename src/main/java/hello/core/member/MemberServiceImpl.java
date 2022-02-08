package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 회원을 찾으려면 repository가 필요
    // 인터페이스 = new 객체
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

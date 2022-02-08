package hello.core.member;

public interface MemberService {
    // 가입, 조회 서비스가 필요
    void join(Member member);

    Member findMember(Long memberId);
}

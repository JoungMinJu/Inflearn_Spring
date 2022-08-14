package hello.core.member;

public interface MemberRepository {
    // 회원 저장소
    // 인터페이스랑 구현체랑 같은 곳에 두기보단 위치를 변경
    // 근데 여기선 같은 패키지에 넣었다

    void save(Member member);

    Member findById(Long memberId);
}

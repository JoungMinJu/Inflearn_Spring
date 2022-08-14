package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 프로젝트가 작아서 Repository 패키지를 따로 만들지 않았음
public class MemberRepository {

    // static으로 선언하기 때문에 new Repository를 많이 해도 Map과 sequence는 하나만 생길 것.

    // 동시성 문제가 고려되어있지 않기 때문에
    // 실무에서는 ConcurrentHashMap이나 AtomicLong 사용 고려한다.
    private static Map<Long, Member> store = new HashMap<>();
    // id가 하나씩 증가하는 시퀀스로 쓰겠다.
    private static long sequence = 0L;

    // 싱글톤
    // 스프링을 안쓰기 때문에 구현해준다.
    // 하나인게 보장이 되므로 위 두개의 인수에 static 안써도 되긴함.
    private static final MemberRepository instance = new MemberRepository();
    // 생성자 막기
    private MemberRepository(){}

    public static MemberRepository getInstance(){
        return instance;
    }


    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        // store 내부의 변수 건들고싶지 않아서 arraylist로 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

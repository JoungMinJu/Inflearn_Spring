package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 한개만 생성해둔다.
    // 자기 자신을 내부에 private static으로 선언해둔다.
    // 이러면 클래스 레벨에 선언되므로 딱 하나만 존재하게 되는 것.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }
    // 자바 뜰 때 바로 내부적으로 객체를 생성하게 된다.

    // new SingletonService를 하지 못하게 private 생성자를 선언한다.
    private SingletonService() {
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    // 꺼낼 수 있는 방법인 getInstance()는 있는데, 생성자가 private이므로 새 객체를 생성해낼 수는 없음.
}

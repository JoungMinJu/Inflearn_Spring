package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// JSON 형식으로 파싱할 수 있게 객체를 하나 생성
@Getter @Setter
public class HelloData {
    private String username;
    private int age;
}

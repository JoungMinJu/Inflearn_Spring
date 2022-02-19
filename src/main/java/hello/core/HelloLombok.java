package hello.core;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


// lombok은 getter setter를 자동으로 만들어준다.
// 생성자, toString 도 다 된다.
@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("AJS");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}

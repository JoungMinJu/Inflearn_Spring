package hello.springmvc.basic;

import lombok.Data;

// 요청 파라미터를 받아서 필요한 객체를만들고 그 객체에 값을 넣어주는!
@Data
// 롬복의 getter setter tostring equalsandhashcode requiredargsconstructor 다 자동으로 적용해준다.
public class HelloData {
    private String username;
    private int age;
}

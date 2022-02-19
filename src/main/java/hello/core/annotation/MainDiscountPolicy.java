package hello.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// Qualifier와 똑같이 붙일 수 있게 하겠다.
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // 이 애노테이션을 쓰면 위의 기능이 다 동작한다.
    // 애노테이션은 상속이라는 기능은 없는데 위에까지 함께 모아서 기능 구현하는 것은 스프링에서 지원하는 기능.
}

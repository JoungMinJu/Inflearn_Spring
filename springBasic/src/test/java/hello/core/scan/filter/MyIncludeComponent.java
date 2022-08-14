package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 임의로 만드는 것
    // 얘가 붙은 것은 component scan에 추가할거야..
}

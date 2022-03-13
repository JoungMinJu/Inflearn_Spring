package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// 이것을 선언하면 문자를 return하면 그냥 스트링이 바로 반환이되게 된다!!!!!!!
// @Controller는 return (뷰 이름) 이된다.
public class LogTestController {
    // org.slf4j
    // 현재 나의 클래스 지정해줌
    // 애노테이션으로 대체 가능!
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        // 얘는 어떤 경우에도 출력이 되므로 운영서버에 이렇게 남기면 절대 안된다.
        System.out.println("name = " + name);

        // 로그 레벨을 찍을 수 있다. 이 로그는 어떤 상태의 레벨이다.
        // 콘솔에서 확인을 할 수 있따.
        // 로그 찎는 양식이 다음과 같음! 불필요한 문자열 더하기 연산이 일어나지 않는다.
        log.trace("info log = {}", name);
        log.debug("info log = {}", name);
        // 위의 두개는 콘솔에 남지 않는다.
        log.info("info log = {}", name);
        log.warn("info log = {}", name);
        log.error("info log = {}", name);

        return "ok";
    }
}

package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// 스프링으로 요청 파라미터를 조회하기
@Slf4j
@Controller
public class RequestParamController {
    // 반환 타입이 없으면서 응답에 값을 직접 집어 넣으면 view 조회 안함
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Servlet Request가 제공하는 방식으로 파라미터를 조회
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }



    // 버전 업
    @RequestMapping("/request-param-v2")
    // 기냥 http 메시지에 return 값 박고싶으면 @RestController 해도되고
    @ResponseBody
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    // 버전 업
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(@RequestParam() String username,
                                 @RequestParam() int age){
        log.info("username={}, age={}", username, age);

        return "ok";
    }


    // 버전 업
    @RequestMapping("/request-param-v4")
    @ResponseBody
    // 요청 파라미터 이름과 맞는다면!
    public String requestParamV4( String username,
                                 int age){
        log.info("username={}, age={}", username, age);

        return "ok";
    }


    @RequestMapping("/request-param-required")
    @ResponseBody
    // 파라미터 필수 여부
    // Integer는 null을 받을 수 있기 때문에 !
    public String requestparamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age){
        log.info("username={}, age={}", username, age);
        return "ok";
        }
    // 기본값 적용
    // 빈문자의 경우에도 설정한 기본 값이 적용된다
    // ?username= 이거
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // 파라미터 값이 한개가 확실하면 Map을 사용
    // 그렇지 않다면 MultiValueMap을 사용
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"),  paramMap.get("age"));
        return "ok";
    }
    // ?라는 쿼리 파라미터 아예 안넣으면 null
    // ?username= 은 빈문자열

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData data = new HelloData();
        data.setUsername(username);
        data.setAge(age);

        return "ok";
    }
    // 스프링은 위의 과정을 완전히 자동화해주는 @ModelAttribute를 제공한다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1-1")
    public String modelAttributeV11(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    // 생락도 가능.
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


}

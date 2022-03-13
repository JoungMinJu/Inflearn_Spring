package hello.servlet.web.springmvc.V1;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller

// 이것도 동일하게 동작
@Component
// 스프링 빈으로 등록
@RequestMapping
// RequestMappingHandlerMapping이 찾아준다.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/neww-form")
    public ModelAndView process(){
        //  뷰 리졸버에서 jsp 처리하기 위한 view가 찾아져서 걔가 render가 된다.
        return new ModelAndView("new-form");
    }
}


// 컴포넌트 스캔 없디 다음과 같이 스프링빈으로 직접 등록해도 된다.

//@RequestMapping
//// RequestMappingHandlerMapping이 찾아준다.
//public class SprintMemberFormControllerV1 {
//
//    @RequestMapping("/springmvc/v1/members/neww-form")
//    public ModelAndView process(){
//        //  뷰 리졸버에서 jsp 처리하기 위한 view가 찾아져서 걔가 render가 된다.
//        return new ModelAndView("new-form");
//    }
//}

// 하고
//ServletApplication에
//@Bean
//SprintMemberFormControllerV1 sprintMemberFormControllerV1(){
//    return new SprintMemberFormControllerV1();
//        }
package hello.servlet.web.frontcontroller.V4;

import java.util.Map;

// Controller는 viewName만 반환한다.
public interface ControllerV4 {
    String process(Map<String, String> paramMap, Map<String, Object> model);
    // model 객체는 파라미터로 전달된다. 직접 ModelView를 생성할 필요가 없음
    // front-controller가 Model을 넘겨주는 것.
    // 결과로 뷰의 이름만 반환해주면 된다.
}

package hello.servlet.web.frontcontroller.V3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // model view를 반환
    ModelView process(Map<String, String> paramMap);
}

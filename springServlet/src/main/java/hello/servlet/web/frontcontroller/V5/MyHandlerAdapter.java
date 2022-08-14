package hello.servlet.web.frontcontroller.V5;


import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    // support 메서드
    // ex) V3 컨트롤러가 넘어오면 v3를 처리할 수 있는 어댑터를 반환해야한다.
    // 처리할 수 있는지 없는지 여부 판단
    boolean supports(Object handler);
    // handle은 handler를 호출해주고 ModelView에 맞춰서 반환해주는 것.
    ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}

package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    // 서블릿이랑 모양이 똑같은 인터페이스 만든다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

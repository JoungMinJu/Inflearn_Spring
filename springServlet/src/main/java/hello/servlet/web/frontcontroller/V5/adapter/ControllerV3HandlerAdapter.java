package hello.servlet.web.frontcontroller.V5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;
import hello.servlet.web.frontcontroller.V5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V3 지원 어뎁터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // 처리할 수 있는지 없는지 여부 넘겨주고
        return(handler instanceof ControllerV3);
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 컨트롤러로 만들고
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        return mv;

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

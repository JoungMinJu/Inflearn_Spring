package hello.servlet.web.frontcontroller.V4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.V4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberSaveControllerV4;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *자리에 어떤게 들어가도 이 서블릿이 무조건 호출된다.
@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    // 매핑 정보를 만든다.
    // 어떤 url을 호출하면 컨트롤러를 호출해라~.. 이런거
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨주어야한다.
        // 다 꺼낸다.
        Map<String, String> paramMap = createParamMap(request);
        // 컨트롤러 안에서 빈 모델을 먼저 넘겨준다.
        Map<String, Object> model = new HashMap<>();
        // controller에서 viewName을 받고
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        // 직접제공하던 모델 넘겨서  render하면
        view.render(model, request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap =new HashMap<>();
        // "username":kim , "age":20 이런거..
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

package hello.servlet.web.frontcontroller.V3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *자리에 어떤게 들어가도 이 서블릿이 무조건 호출된다.
@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    // 매핑 정보를 만든다.
    // 어떤 url을 호출하면 컨트롤러를 호출해라~.. 이런거
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 매핑정보를 이용하여 어떤 컨트롤러 땡길지 찾아야한다.
        // /front-controller/ 머 이런것 받을 수 있다.
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨주어야한다.
        // 다 꺼낸다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        // 논리 이름을 물리 이름으로 바꾸어야한다. 실제 View를 찾아주는 viewResolver가 필요
        String viewName = mv.getViewName();// 논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request,response);
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

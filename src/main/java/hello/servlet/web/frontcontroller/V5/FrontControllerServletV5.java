package hello.servlet.web.frontcontroller.V5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.V3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.V4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.V4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.V5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.V5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // 기존엔
    // private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 생성자
    public FrontControllerServletV5(){
        // 초기화
        initHandlerMappingMap();
        initHanderAdapters();
    }

    private void initHanderAdapters() {
        // 어댑터 초기화해서 넣어준다.
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        // 매핑정보
        // v3 지원하는 것을 먼저  handler매핑맵에 넣는다.
        // v5에서 요청하는 것이기 때문에 경로를 변경해주어야한다.
        // v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    
    // 실행하는것

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 핸들러를 찾아서
        Object handler = getHandler(request);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 처리할 수 잇는지 어댑터 목록에서 뒤져야한다.
        // 핸들러 어댑터 찾아옴
        MyHandlerAdapter adapter =  getHandlerAdapter(handler);

        // 어댑터 호출
        ModelView mv = adapter.handler(request, response, handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                // 지원을 하면 저장해둔다.
                return adapter;
            }
        }
        // 없으면 오류 반환
        // 디버깅 해야하는 핸들러가 무엇인지도 남겨주자.
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다." + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        // 매핑 정보를 가지고 핸들러 맵에서 찾는 과정
        String requestURI = request.getRequestURI();
        // 무엇이 들어올지 모르니가 Object로 받는다.
       return handlerMappingMap.get(requestURI);
    }
}

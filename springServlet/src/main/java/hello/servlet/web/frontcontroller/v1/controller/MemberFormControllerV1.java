package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.naming.ldap.Control;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 컨트롤러들을 모아놓는 패키지
public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controller에 먼저 내용이 들어와야한다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // jsp로 가주게 만들기
        // 컨트롤러에서 뷰로 이동할 때 사용한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 이 코드로 완전하게 서블릿에서 jsp 호출
        dispatcher.forward(request, response);
    }
}

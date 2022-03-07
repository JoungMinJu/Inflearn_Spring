package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 회원 등록 폼 - 컨트롤러
@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Controller에 먼저 내용이 들어와야한다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // jsp로 가주게 만들기
        // 컨트롤러에서 뷰로 이동할 때 사용한다.
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        // 이 코드로 완전하게 서블릿에서 jsp 호출
        dispatcher.forward(req, resp);
    }
}

package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // ctrl+O로 불러온다.
    // 서블릿이 호출되면 service 메소드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        String username = request.getParameter("username"); // 쿼리파라미터 조회 가능.
        System.out.println("username = " + username);
        
        // 헤더에 들어가는 내용
        response.setContentType("text/plain"); // 단순 문자 응답 보낼 것.
        response.setCharacterEncoding("utf-8");
        // 바디에 들어가는 내용
        response.getWriter().write("hello " +username);//http 메세지 바디에 들어가는 내용

    }
}

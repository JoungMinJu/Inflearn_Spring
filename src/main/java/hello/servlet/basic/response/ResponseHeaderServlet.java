package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 코드 생성
        // 200이런거 직접 적는 것보다 저렇게 하는 것이 좋음.
        // [status line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 무효화
        response.setHeader("Pragma", "no-cache"); // + 캐시 무효화
        response.setHeader("my-header", "hello"); // 내가 만든 헤더

        // [Header의 편의 메소드]
        content(response);

        // 쿠키도 넣을 수는 있음 set-cookie로 해서..
        // 근데 그냥 좀 편리하게 하기 위해서
        cookie(response);

        redirect(response);

        // [message Body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    // 조금 더 쉽게 header 생성하는 방법.
    public void content(HttpServletResponse response){
        // Content-Type : text/plain; charset=utf-8
        // Content-Length : 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8")
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); // 생락시 자동 생성! 내가 직접 적어서 내보낼 수도 있음.
        // OK + \n 이어서 byte 길이 3
   }

   private void cookie(HttpServletResponse response){
        // Set-Cookie : myCookie=good; Max-Age=600;
       // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
       Cookie cookie = new Cookie("myCookie", "good");
       cookie.setMaxAge(600); //600초
       response.addCookie(cookie);
   }

   // redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException{
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse..SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

}

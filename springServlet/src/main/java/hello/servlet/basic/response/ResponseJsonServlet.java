package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.beans.factory.ObjectProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("applicatio/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        // 이렇게 바꾸려면 Object Mapper가 필요하다.
        helloData.setUsername("kim");
        helloData.setAge(20);

        // String 데이터로 뽑고
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}

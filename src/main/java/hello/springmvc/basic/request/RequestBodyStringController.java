package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    // 데이터가 넘어오니까 POST를 쓴다.
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        response.getWriter().write("oK");
    }

    @PostMapping("/request-body-string-v2")
    // HttpServletRequest, Response 받지말로 stream 바로 받기
    // 스프링 MVC는 다음 파라미터를 지원한다.
    // InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
    // OutputStream(Writer)  : HTTP 응답 메시지의 바디에 직접 결과 출력
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        responseWriter.write("oK");
    }

    @PostMapping("/request-body-string-v3")
    // 파라미터 보고.. HttpEntity 바디의 내용을 문자로 바꿔서 넣어줄게!
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        // 헤더 정보도 가능
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("messageBody={}",messageBody);

        // 상태코드
        return new ResponseEntity<String>("ok",HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    //그냥 이거 하면 끝! @RequestBody
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}",messageBody);

        // 상태코드
        return "ok";
    }

}

package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 스프링이 자동으로 서블릿을 찾아서 자동으로 서블릿을 등록하고 실행할 수 있게 도와준다.
@ServletComponentScan //서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}

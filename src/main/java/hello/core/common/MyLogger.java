package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request")
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        // 빈이 생성되는 시점에  request.url을 알 수가 없으므로 외부에서 입력 받는다.
        this.requestURL = requestURL;
    }

    public void log(String messgae){
        System.out.println("["+uuid+"] "+"["+requestURL+"] "+messgae);
    }

    @PostConstruct
    public void init(){
        // 글로벌하게 unique한 아이디 생성
        // 다른 http 요청과 구분할 수 있음.
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create:"+ this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close:"+ this);
    }
}

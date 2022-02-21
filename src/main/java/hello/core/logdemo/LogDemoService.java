package hello.core.logdemo;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//이 어노테이션은 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다. 주로 의존성 주입(Dependency Injection) 편의성을 위해서 사용되곤 합니다.
public class LogDemoService {
    private MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}

package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    private String url;

    public NetworkClient() {
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    // 연결한 서버에 메세지 던질 수 있는
    public void call(String message){
        System.out.println("url = " + url + " message= "+message) ;
    }

    // 서비스 종료시
    public void disconnect(){
        System.out.println("close " +url);
    }

    @PostConstruct
    public void init()  {
        // 위에것들 세팅(의존관계주입)이 끝나면 호출해주겠다.
        System.out.println("NetworkClient.init(");
        System.out.println("생성자 호출, url = " + url);
        connect();
    }
    @PreDestroy
    public void close()  {
        System.out.println("NetworkClient.close");
        // 빈이 종료될 때 호출된다.
        disconnect();
    }
}

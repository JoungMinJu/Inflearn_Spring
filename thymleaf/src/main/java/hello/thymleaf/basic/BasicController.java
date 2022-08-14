package hello.thymleaf.basic;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    // 이스케이프 해제
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("userA", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";

    }

    // Session은 로그인할때 사용자가 웹브라우저 끄지 않는 이상 유지되는 것.
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean{
        // HelloBean이라는게 스프링 빈으로 등록된다.
        public String hello(String data){
            return "Hello "+data;
        }
    }
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    // 연산
    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring");
        return "basic/operation";
    }

    // 속성값 설정
    // 타임리프 태ㅡㄱ 속성
    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }

    // 반복
    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }


    private void addUsers(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model){
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model){
        model.addAttribute("user", new User("userA",10));
        addUsers(model);
        return "basic/javascript";
    }


    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

}



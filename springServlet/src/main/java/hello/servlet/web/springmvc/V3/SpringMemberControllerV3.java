package hello.servlet.web.springmvc.V3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(name="/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();


    // GET으로로만 받겠다.
    // 단순 조회
    @RequestMapping(name="/new-form", method = RequestMethod.GET)
    // 더 쉽게 하려면
    // @GetMapping("/new-form")
    public String newForm(){
        // 기냥 String 반환
        return "new-form";
    }

    // 단순 조회
    // @GetMapping
    @RequestMapping(name="", method = RequestMethod.GET)
    public String members(Model model){
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    // 데이터 내용 변경
    @RequestMapping(name="/save", method = RequestMethod.POST)
    // @PostMapping("/save")
    // 애노테이션으로
    // http 기반 request, reseponse 받을 수 있고
    // 파라미터도 직접적으로 받을 수 있다. @RequestParam
    // 타입 캐스팅도 내맴대로 된다... 개이득
    // 모델을 받을 수도 있따.
    public String save(@RequestParam("username") String username,
                       @RequestParam("age") int age,
                             Model model){
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        // 넘어온 모델에 addAttribute로 넘겨준다.
        model.addAttribute("member",member);
        return "save-result";
    }
}

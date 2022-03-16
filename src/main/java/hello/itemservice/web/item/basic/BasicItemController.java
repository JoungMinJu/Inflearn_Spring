package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
// itemRepository에 대해 생성자 주입이 된다.
// lombok의 애노테이션
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    // 모델만 받는다.
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        // "basic/items"에 뷰를 만들어놓겠다
        return "basic/items";
    }

    // 테스트용 데이터 추가
    // PostConstruct는 의존성 주입이 이루어진 후 초기화를 수행하는 메서드
    // 서비스를 수행하기 전에 발생한다.
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000,20));
    }

    // 상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 폼
    // 단순히 뷰 템플릿만 호출한다.
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // 저장
    // 같은 url이지만 POST로 왔을 떄!
    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model
                            ){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // modelAttribute 쓰는 저장 예시!
//    @PostMapping("/add")
    // 애노테이션은 모델 객체를 마들어주고 자동으로 모델에 넣어준다.
    // 이름은 ("item")으로 지정해둔 이름으로 들어간다.
    public String addItemV2(@ModelAttribute("item") Item item
//            , Model model
    ){

        // 모델 attribute 애노테이션이 아래꺼 다 만들어줌.
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);

        // 애노테이션은 모델 객체를 마들어주고 자동으로 모델에 넣어준다.
        // 이름은 ("item")으로 지정해둔 이름으로 들어간다.
        // 즉, 아래코드를 걍 실행해줌
        // 파라미터로 Model model도 선언 안해줘도 된다... 걍 만들어줌.
//        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    // ModelAttribute의 이름을 생략할 수 있다.
    // 그럼 모델에 저장될 때 클래스명을 사용! 클래스의 첛글자만 소문자로 변경해서 등록한다.
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item){
        // @ModelAttribute자체 생략 가능
        itemRepository.save(item);
        return "basic/item";
    }

    // 상품 수정 폼 컨트롤러
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 폼 컨트롤러
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}

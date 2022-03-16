package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 프로젝트가 작으니 걍 같은 패키지
@Repository
// 컴포넌트 스캔의 대상이 된다.
// ctrl+shift+t로 테스트 만들수 있음.
public class ItemRepository {
    // 동시에 여러 쓰레드가 접근하므로 현업에는 해시맵 안됨
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
     public Item findById(Long id){
        return store.get(id);
     }
     public List<Item> findAll(){
        return new ArrayList<>(store.values());
     }
     public void update(Long itemId, Item updateParam){
         Item findItem = findById(itemId);
         findItem.setItemName(updateParam.getItemName());
         findItem.setPrice(updateParam.getPrice());
         findItem.setQuantity(updateParam.getQuantity());
     }
    public void clearStore(){
        store.clear();
    }
}

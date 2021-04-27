package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //multi thread 환경에서 HashMap을 사용하면 안된다. ConcurrentHashMap을 사용해야 한다
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용

    //multi thread 환경에서는 atomic long을 사용해야 한다
    private static long sequence = 0L; //static 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // findAll의 반환 값을 변경하여도 store에 영향이 가지 않도록 array list로 감싼다. Type을 바꿔야 하는 문제도 처리한다
        return new ArrayList<>(store.values());
    }

    //필요한 데이터만 저장하는 Item parameter DTO를 만드는 것이 더 좋다
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void  clearStore() {
        store.clear();
    }
}

package hello.itemservice.domain.item;

import lombok.Data;

@Data // 핵심 도메인 모델에서 사용하지 말자
public class Item {

    private Long id;
    private String itemName;
    private Integer price;

    //Integer -> null될 수 있다고 가정
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

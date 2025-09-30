package jpabookV2.jpashop.domain;

import jakarta.persistence.*;
import jpabookV2.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    private Item item;
    private Order order;
    private int orderPrice;
    private int count;
}

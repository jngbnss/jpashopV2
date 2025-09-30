package jpabookV2.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private Member member;
    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;
    private LocalDateTime orderDate;

    private OrderStatus status;

    //==연관관계 메서드==//
}

package jpabookV2.jpashop.api;

import jpabookV2.jpashop.domain.*;
import jpabookV2.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    //V1. 엔티티 직접 노출 패스할까
    //V2. 엔티티를 DTO로 변환
    @GetMapping("/api/v2/orders")
    public List<OrderDto>ordersV2(){
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderDto> result = orders.stream().map(o -> new OrderDto(o))
                .collect(toList());

        return result;

    }
    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto>orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            order.getOrderItems().stream()
                    .map(orderItem->new OrderItemDto(orderItem))
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto{
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem){
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }

    //V3 엔티티를 DTO로 변환 - 페치 조인 최적화
    @GetMapping("/api/v3/orders")
    public List<OrderDto>ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto>result = orders.stream().map(o->new OrderDto(o)).collect(toList());

        return result;
    }
    // 페이징 불가능
    // 한계 돌파 복잡해보이는데
    //v4 jpa에서 dto 직접 조회
    //V5 JPA에서 DTO 직접 조회 - 컬렉션 조회 최적화
}

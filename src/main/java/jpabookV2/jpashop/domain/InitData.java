package jpabookV2.jpashop.domain;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jpabookV2.jpashop.domain.Address;
import jpabookV2.jpashop.domain.Member;
import jpabookV2.jpashop.domain.item.Book;
import jpabookV2.jpashop.service.MemberService;
import jpabookV2.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final ItemService itemService;

        @Transactional
        public void dbInit() {
            // 멤버 생성
            Member member = new Member();
            member.setName("김철수");
            member.setAddress(new Address("신도시", "사거리", "12345"));
            memberService.join(member);

            Member member1 = new Member();
            member1.setName("김민수");
            member1.setAddress(new Address("신도시", "사거리", "12345"));
            memberService.join(member1);

            // 책 상품 생성
            Book book = new Book();
            book.setName("JPA");
            book.setPrice(1);
            book.setStockQuantity(2);
            book.setAuthor("김영한");
            book.setIsbn("1234");
            itemService.saveItem(book);
        }
    }
}

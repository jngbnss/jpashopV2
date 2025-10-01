package jpabookV2.jpashop.web;

import jpabookV2.jpashop.exception.NotEnoughStockException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotEnoughStockException.class)
    public String handleStockError(NotEnoughStockException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error/stockError";  // stockError.html 보여줌
    }
}

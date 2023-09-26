package ezen.call.web.work.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.order.dto.Cart;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 장바구니 목록 요청 처리 세부 컨트롤러
 * @author 이희영
 * /order/cart
 */
public class CartController implements HttpController {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = "order/cart";

        // 장바구니 담은 상품리스트 출력
        List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cart");
        int totalPrice = 0;
        
        // 장바구니 화면 요청 (GET)
        if (request.getMethod().equalsIgnoreCase("get") && request.getQueryString() == null) {
            if (cartList != null) {
                for (Cart cart : cartList) {
                    int price = cart.getProduct().getPrice();
                    int count = cart.getCount();
                    int total = price * count;
                    totalPrice += total;
                }
            }
            request.getSession().setAttribute("totalPrice", totalPrice);
            model.put("cartList", cartList);
            model.put("totalPrice", totalPrice);
            
        // 장바구니에서 버튼 클릭 요청 (삭제 & 수량변경) (GET)
        } else if (request.getMethod().equalsIgnoreCase("get") && request.getQueryString() != null) {

            //장바구니 삭제하기 버튼
            if (request.getQueryString().contains("delete")) {
                int index = Integer.parseInt(paramMap.get("delete"))-1;
                cartList.remove(index);
                model.put("cartList", cartList);
                viewName = "redirect:/mall/order/cart";
            // 수량 변경 버튼
            } else {
                for (Cart cart : cartList) {
                    if (cart.getProduct().getProductNum() == Integer.parseInt(paramMap.get("productNum"))) {
                        int count = cart.getCount();
                        if (paramMap.get("cartCount").equalsIgnoreCase("plus")) {
                            if (count < 9) {
                                cart.setCount(count + 1);
                            }
                        } else {
                            if (count > 1) {
                                cart.setCount(count - 1);
                            }
                        }
                    }
                }
                model.put("cartList", cartList);
                viewName = "redirect:/mall/order/cart";
            }
        // 주문하기 요청 (POST)
        } else if (request.getMethod().equalsIgnoreCase("post")) {
            List<Cart> orderList = (List<Cart>) request.getSession().getAttribute("cart");
            totalPrice = (int) request.getSession().getAttribute("totalPrice");
            request.getSession().setAttribute("totalPrice", totalPrice);
            request.getSession().setAttribute("orderList", orderList);
            request.getSession().removeAttribute("avgRating");
            request.getSession().removeAttribute("product");
            
            viewName = "redirect:/mall/order/order";
        }
        return viewName;
    }
}
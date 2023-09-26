package ezen.call.web.work.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.order.dto.Cart;
import ezen.call.domain.order.dto.OrderList;
import ezen.call.domain.order.dto.ProductOrder;
import ezen.call.domain.order.service.OrderService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 주문 요청 처리 세부 컨트롤러
 * @author 김종원
 * /order/order
 */
public class OrderController implements HttpController {

    private OrderService orderService  = ServiceFactory.getInstance().getOrderService();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = "order/order";
        
        // 주문 요청 (POST)
        if (request.getMethod().equalsIgnoreCase("post")) {
            
            MemberCombined memberCombined = (MemberCombined) request.getSession().getAttribute("loginMember");
            List<Cart> carts = (List<Cart>) request.getSession().getAttribute("orderList");
            
            String deliName = paramMap.get("deliName");
            String deliPhone = paramMap.get("deliPhone");
            String deliAddress = paramMap.get("deliAddress");
            String deliReq = paramMap.get("deliReq");
            String payment = paramMap.get("payment");

            ProductOrder productOrder = new ProductOrder();
            productOrder.setMemberNum(memberCombined.getMember().getMemberNum());
            productOrder.setPayment(payment);
            productOrder.setReceiverName(deliName);
            productOrder.setDeliveryPhoneNum(deliPhone);
            productOrder.setDeliveryAddress(deliAddress);
            productOrder.setDeliveryRequest(deliReq);
            
            List<OrderList> orderLists = new ArrayList<OrderList>();
            for (Cart cart : carts) {
                OrderList orderList = new OrderList();
                orderList.setProductNum(cart.getProduct().getProductNum());
                orderList.setOrderCount(cart.getCount());
                orderLists.add(orderList);
            }
            
            orderService.createOrder(productOrder, orderLists);
            
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("product");
            viewName = "redirect:/mall/index";
        }
        return viewName;
    }
}
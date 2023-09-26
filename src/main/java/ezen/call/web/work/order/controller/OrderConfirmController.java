package ezen.call.web.work.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.order.dto.OrderConfirm;
import ezen.call.domain.order.service.OrderService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 주문확인 요청 처리 세부 컨트롤러
 * @author 김종원
 * /order/order_confirm
 */
public class OrderConfirmController implements HttpController {

    private OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = "order/order_confirm";

        MemberCombined memberCombined = (MemberCombined) request.getSession().getAttribute("loginMember");
        int memberNumber = memberCombined.getMember().getMemberNum();
        List<OrderConfirm> confirmList = orderService.getOrderConfirm(memberNumber);
        
        model.put("confirmList", confirmList);

        return viewName;
    }
}
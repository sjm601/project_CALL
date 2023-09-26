package ezen.call.domain.order.service;

import java.util.List;

import ezen.call.domain.order.dto.OrderConfirm;
import ezen.call.domain.order.dto.OrderList;
import ezen.call.domain.order.dto.ProductOrder;

/**
 * 주문 관련 비즈니스 로직 처리 및 트랜잭션 관리를 위한 명세
 * @author 이희영
 */
public interface OrderService {
    
	// 주문 생성
    public boolean createOrder(ProductOrder productOrder, List<OrderList> list);
    // 전체 주문 목록 조회
    public List<OrderList> getOrderLists();
    // 회원 번호로 주문 리스트 조회
    public List<ProductOrder> getProductOrders(int memberNumber);
    // 특정회원의 주문확인용 목록
    public List<OrderConfirm> getOrderConfirm(int memberNumber);
}
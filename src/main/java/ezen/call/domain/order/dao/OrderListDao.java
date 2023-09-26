package ezen.call.domain.order.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.order.dto.OrderList;
import ezen.call.domain.order.dto.OrderListAndProduct;

/**
 * 데이터 베이스 주문목록 관리 명세
 *  @author 이희영
 */
public interface OrderListDao {
    
	// 주문목록 생성
    public boolean create(Connection connection, OrderList orderList);
	// 전체 주문목록
    public List<OrderList> findByAll(Connection connection);
	// 특정 주문에 대한 전체 주문 목록
    public List<OrderListAndProduct> findByAllProduct(Connection connection, int orderNumber);
    // 리뷰 작성시 변경
    public boolean updateOrderListByReview(Connection connection, int orderListNum);
}
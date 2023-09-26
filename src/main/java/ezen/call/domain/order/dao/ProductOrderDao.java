package ezen.call.domain.order.dao;

import java.sql.Connection;
import java.util.List;

import ezen.call.domain.order.dto.ProductOrder;

/**
 * 데이터 베이스 주문 관리 명세
 *  @author 이희영
 */
public interface ProductOrderDao {
    
	// 주문생성
    public boolean create(Connection connection, ProductOrder productOrder);
	// 회원 제품주문 전체 리스트
    public List<ProductOrder> findByAll(Connection connection, int memberNum);
}
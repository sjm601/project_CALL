package ezen.call.domain.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.order.dto.ProductOrder;

/**
 * RDB를 통해 주문 저장 및 관리(검색, 수정, 삭제) 구현체
 * @author 이희영
 */
public class JdbcProductOrderDao implements ProductOrderDao {
    
	/**
	 * 주문생성
	 * @param Connection 커넥션
	 * @param ProductOrder 주문
	 * @return Boolean 주문생성 성공여부
	 */
    @Override
    public boolean create(Connection connection, ProductOrder productOrder) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO product_order (")
          .append("    order_number,")
          .append("    member_number,")
          .append("    payment,")
          .append("    receiver_name,")
          .append("    delivery_phone_number,")
          .append("    delivery_address,")
          .append("    delivery_request)")
          .append(" VALUES (")
          .append("   order_number_seq.NEXTVAL,")
          .append("    ?, ?, ?, ?, ?, ?)");

        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, productOrder.getMemberNum());
            pstmt.setString(2, productOrder.getPayment());
            pstmt.setString(3, productOrder.getReceiverName());
            pstmt.setString(4, productOrder.getDeliveryPhoneNum());
            pstmt.setString(5, productOrder.getDeliveryAddress());
            pstmt.setString(6, productOrder.getDeliveryRequest());
            pstmt.executeUpdate();
            success = true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return success;
    }
    
    /**
	 * 회원 제품주문 전체 리스트
	 * @param Connection 커넥션
	 * @param Int 회원번호
	 * @return List<ProductOrder> 주문리스트
	 */
    @Override
    public List<ProductOrder> findByAll(Connection connection, int memberNumber) {
        List<ProductOrder> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT")
          .append("   order_number,")
          .append("   member_number,")
          .append("   TO_CHAR(order_date, 'YYYY-MM-DD') order_date,")
          .append("   payment,")
          .append("   receiver_name,")
          .append("   delivery_phone_number,")
          .append("   delivery_address,")
          .append("   delivery_request")
          .append(" FROM product_order")
          .append(" WHERE member_number = ?")
          .append(" ORDER BY order_number DESC");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, memberNumber);
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int orderNum = rs.getInt("order_number");
                int memberNum = rs.getInt("member_number");
                String orderDate = rs.getString("order_date");
                String payment = rs.getString("payment");
                String receiverName = rs.getString("receiver_name");
                String deliveryPhoneNum = rs.getString("delivery_phone_number");
                String deliveryAddress = rs.getString("delivery_address");
                String deliveryRequest = rs.getString("delivery_request");
                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrderNum(orderNum);
                productOrder.setMemberNum(memberNum);
                productOrder.setOrderDate(orderDate);
                productOrder.setPayment(payment);
                productOrder.setReceiverName(receiverName);
                productOrder.setDeliveryPhoneNum(deliveryPhoneNum);
                productOrder.setDeliveryAddress(deliveryAddress);
                productOrder.setDeliveryRequest(deliveryRequest);
                list.add(productOrder);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (Exception e) {}
        }
        return list;
    }
}
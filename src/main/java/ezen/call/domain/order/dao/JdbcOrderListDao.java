package ezen.call.domain.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.order.dto.OrderList;
import ezen.call.domain.order.dto.OrderListAndProduct;
import ezen.call.domain.order.dto.OrderProduct;

/**
 * RDB를 통해 주문목록 저장 및 관리(검색, 수정, 삭제) 구현체
 * @author 이희영
 */
public class JdbcOrderListDao  implements OrderListDao {
    
	/**
	 * 주문목록 생성
	 * @param Connection 커넥션
	 * @param OrderList 주문목록
	 * @return Boolean 주문목록 생성 성공여부
	 */
    @Override
    public boolean create(Connection connection, OrderList orderList) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO order_list (")
          .append("    order_list_number,")
          .append("    order_number,")
          .append("    product_number,")
          .append("    order_count)")
          .append(" VALUES (")
          .append("    order_list_number_seq.NEXTVAL,")
          .append("    order_number_seq.CURRVAL,")
          .append("    ?, ?)");
        
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, orderList.getProductNum());
            pstmt.setInt(2, orderList.getOrderCount());
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
	 * 전체 주문목록
	 * @param Connecion 커넥션
	 * @return List<OrderList> 주문목록 리스트
	 */
    @Override
    public List<OrderList> findByAll(Connection connection) {
        List<OrderList> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT order_list_number, product_number, order_number, order_count")
          .append("    FROM order_list");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            list = new ArrayList<OrderList>();
            while (rs.next()) {
                int orderListNum = rs.getInt("order_list_number");
                int productNum = rs.getInt("product_number");
                int orderNum = rs.getInt("order_number");
                int orderCount = rs.getInt("order_count");
                OrderList orderList = new OrderList();
                orderList.setOrderListNum(orderListNum);
                orderList.setProductNum(productNum);
                orderList.setOrderNum(orderNum);
                orderList.setOrderCount(orderCount);
                list.add(orderList);
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
    
    /**
     * 특정 주문에 대한 전체 주문 목록
     * @param Connecion 커넥션
     * @return List<OrderList> 주문목록 리스트
     */
    @Override
    public List<OrderListAndProduct> findByAllProduct(Connection connection, int orderNumber) {
        List<OrderListAndProduct> list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT")
          .append("   ol.order_list_number,")
          .append("   ol.product_number,")
          .append("   ol.order_number,")
          .append("   ol.order_count,")
          .append("   ol.review_comp,")
          .append("   p.product_name,")
          .append("   p.category,")
          .append("   p.price,")
          .append("   p.thumbnail_path")
          .append(" FROM order_list ol")
          .append("   JOIN product p ON p.product_number = ol.product_number")
          .append(" WHERE ol.order_number = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, orderNumber);
            rs = pstmt.executeQuery();
            list = new ArrayList<OrderListAndProduct>();
            while (rs.next()) {
                int orderListNum = rs.getInt("order_list_number");
                int productNum = rs.getInt("product_number");
                int orderNum = rs.getInt("order_number");
                int orderCount = rs.getInt("order_count");
                String reviewComp = rs.getString("review_comp");
                
                String productName = rs.getString("product_name");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                String thumbnailPath = rs.getString("thumbnail_path");
                
                OrderListAndProduct orderListAndProduct = new OrderListAndProduct();
                OrderList orderList = new OrderList();
                orderList.setOrderListNum(orderListNum);
                orderList.setProductNum(productNum);
                orderList.setOrderNum(orderNum);
                orderList.setOrderCount(orderCount);
                orderList.setReviewComp(reviewComp);
                
                OrderProduct product = new OrderProduct();
                product.setProductNum(productNum);
                product.setProductName(productName);
                product.setCategory(category);
                product.setPrice(price);
                product.setThumbnailPath(thumbnailPath);
                
                orderListAndProduct.setOrderList(orderList);
                orderListAndProduct.setProduct(product);
                
                list.add(orderListAndProduct);
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
    
    /**
     * 리뷰 작성시 상태 변경
     */
    @Override
    public boolean updateOrderListByReview(Connection connection, int orderListNum) {
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE order_list")
          .append(" SET review_comp = 'T'")
          .append(" WHERE order_list_number = ?");
        
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, orderListNum);
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
}
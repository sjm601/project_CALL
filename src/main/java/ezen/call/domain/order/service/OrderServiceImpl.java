package ezen.call.domain.order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ezen.call.domain.order.dao.OrderListDao;
import ezen.call.domain.order.dao.ProductOrderDao;
import ezen.call.domain.order.dto.OrderConfirm;
import ezen.call.domain.order.dto.OrderList;
import ezen.call.domain.order.dto.OrderListAndProduct;
import ezen.call.domain.order.dto.ProductOrder;

/**
 * 주문목록 관련 비즈니스 로직 처리 및 트랜잭션 관리 구현체
 * @author 이희영
 */
public class OrderServiceImpl implements OrderService{
    
    private DataSource dataSource;
    private OrderListDao orderListDao;
    private ProductOrderDao productOrderDao;
    
    public OrderServiceImpl(DataSource dataSource, OrderListDao orderListDao, ProductOrderDao productOrderDao) {
        this.dataSource = dataSource;
        this.orderListDao = orderListDao;
        this.productOrderDao = productOrderDao;
    }

    /**
	 * 주문 생성
	 * @param ProductOrder 주문
	 * @param List<OrderList> 주문목록
	 * @return Boolean 주문 생성 성공여부
	 */
    @Override
    public boolean createOrder(ProductOrder productOrder, List<OrderList> list) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            productOrderDao.create(connection, productOrder);
            for (OrderList orderList : list) {
                orderListDao.create(connection, orderList);
            }
            connection.commit();
            success = true;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e.getMessage());
            } finally {
                try {
                    if (connection != null) connection.close();
                } catch (SQLException e1) {}
            }
        }
        return success;
    }

    /**
	 * 전체 주문 목록 조회
	 * @return List<OrderList> 주문목록 리스트
	 */
    @Override
    public List<OrderList> getOrderLists() {
        List<OrderList> orderLists = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            orderLists = orderListDao.findByAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return orderLists;
    }
    
    /**
	 * 회원 번호로 주문 리스트 조회
	 * @param Int 회원번호
	 * @return List<ProductOrder> 주문 리스트
	 */
    @Override
    public List<ProductOrder> getProductOrders(int memberNumber) {
        List<ProductOrder> productOrders = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            productOrders = productOrderDao.findByAll(connection, memberNumber);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return productOrders;
    }
   
    /**
     * 회원 번호로 주문확인 리스트 조회
     * @param Int 회원 번호
     * @return List<OrderConfirm> 주문확인 리스트
     */
    @Override
    public List<OrderConfirm> getOrderConfirm(int memberNumber) {
        List<OrderConfirm> list = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            list = new ArrayList<OrderConfirm>();
            List<ProductOrder> orders = productOrderDao.findByAll(connection, memberNumber);
            for (ProductOrder productOrder : orders) {
                OrderConfirm orderConfirm = new OrderConfirm();
                int orderNumber = productOrder.getOrderNum();
                List<OrderListAndProduct> orderList = orderListDao.findByAllProduct(connection, orderNumber);
                orderConfirm.setProductOrder(productOrder);
                orderConfirm.setOrderListAndProducts(orderList);
                list.add(orderConfirm);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return list;
    }

    // 테스트 메인
    public static void main(String[] args) {
//        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        OrderService orderService = serviceFactory.getOrderService();
        
//        ProductOrder order = new ProductOrder(3, "kakao", "홍길동",
//                "01011112222", "서울시", "빠른배송");
//        List<OrderList> list = new ArrayList<OrderList>();
//        OrderList orderList1 = new OrderList(1,2);
//        OrderList orderList2 = new OrderList(2,3);
//        OrderList orderList3 = new OrderList(3,1);
//        list.add(orderList1);
//        list.add(orderList2);
//        list.add(orderList3);
//        boolean su = orderService.createOrder(order, list);
//        System.out.println(su);
        
//        List<OrderList> orderLists = orderListService.getOrderLists();
//        System.out.println(orderLists);
    }
}
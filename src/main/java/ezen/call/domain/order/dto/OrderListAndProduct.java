package ezen.call.domain.order.dto;

/**
 * OrderListAndProduct 클래스
 * @author 김종원
 */
public class OrderListAndProduct {

    OrderList orderList;
    OrderProduct product;
    
    public OrderListAndProduct() {}
    
    public OrderListAndProduct(OrderList orderList, OrderProduct product) {
        this.orderList = orderList;
        this.product = product;
    }
    
    public OrderList getOrderList() {
        return orderList;
    }
    
    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }
    
    public OrderProduct getProduct() {
        return product;
    }
    
    public void setProduct(OrderProduct product) {
        this.product = product;
    }
    
    @Override
    public String toString() {
        return "orderListAndProduct [" + orderList + ", " + product + "]";
    }
}
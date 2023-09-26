package ezen.call.domain.order.dto;

import java.util.List;

/**
 * OrderConfirm 주문확인 클래스
 * @author 김종원
 */
public class OrderConfirm {
    
    ProductOrder productOrder;
    List<OrderListAndProduct> orderListAndProducts;
    
    public OrderConfirm() {}

    public OrderConfirm(ProductOrder productOrder, List<OrderListAndProduct> orderListAndProducts) {
        this.productOrder = productOrder;
        this.orderListAndProducts = orderListAndProducts;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public List<OrderListAndProduct> getOrderListAndProducts() {
        return orderListAndProducts;
    }

    public void setOrderListAndProducts(List<OrderListAndProduct> orderListAndProducts) {
        this.orderListAndProducts = orderListAndProducts;
    }

    @Override
    public String toString() {
        return "OrderConfirm [" + productOrder + ", " + orderListAndProducts + "]";
    }
}
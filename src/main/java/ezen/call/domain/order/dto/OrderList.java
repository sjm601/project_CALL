package ezen.call.domain.order.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 이희영
 */
public class OrderList {
    
    private int orderListNum;
    private int productNum;
    private int orderNum;
    private int orderCount;
    private String reviewComp;
    
    public OrderList() {}

    public OrderList(int orderListNum, int productNum, int orderNum, int orderCount, String reviewComp) {
        this.orderListNum = orderListNum;
        this.productNum = productNum;
        this.orderNum = orderNum;
        this.orderCount = orderCount;
        this.reviewComp = reviewComp;
    }

    public OrderList(int productNum, int orderCount) {
        this.productNum = productNum;
        this.orderCount = orderCount;
    }

    public int getOrderListNum() {
        return orderListNum;
    }

    public void setOrderListNum(int orderListNum) {
        this.orderListNum = orderListNum;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getReviewComp() {
        return reviewComp;
    }

    public void setReviewComp(String reviewComp) {
        this.reviewComp = reviewComp;
    }

    @Override
    public String toString() {
        return "OrderList [orderListNum=" + orderListNum + ", productNum=" + productNum + ", orderNum=" + orderNum
                + ", orderCount=" + orderCount + ", reviewComp=" + reviewComp + "]";
    }
    
}
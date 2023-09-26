package ezen.call.domain.order.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 이희영
 */
public class ProductOrder {

    private int orderNum;
    private int memberNum;
    private String orderDate;
    private String payment;
    private String receiverName;
    private String deliveryPhoneNum;
    private String deliveryAddress;
    private String deliveryRequest;

    public ProductOrder() {}

    public ProductOrder(int orderNum, int memberNum, String orderDate, String payment, String receiverName,
            String deliveryPhoneNum, String deliveryAddress, String deliveryRequest) {
        this.orderNum = orderNum;
        this.memberNum = memberNum;
        this.orderDate = orderDate;
        this.payment = payment;
        this.receiverName = receiverName;
        this.deliveryPhoneNum = deliveryPhoneNum;
        this.deliveryAddress = deliveryAddress;
        this.deliveryRequest = deliveryRequest;
    }
    
    public ProductOrder(int memberNum, String payment, String receiverName,
            String deliveryPhoneNum, String deliveryAddress, String deliveryRequest) {
        this.memberNum = memberNum;
        this.payment = payment;
        this.receiverName = receiverName;
        this.deliveryPhoneNum = deliveryPhoneNum;
        this.deliveryAddress = deliveryAddress;
        this.deliveryRequest = deliveryRequest;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDeliveryPhoneNum() {
        return deliveryPhoneNum;
    }

    public void setDeliveryPhoneNum(String deliveryPhoneNum) {
        this.deliveryPhoneNum = deliveryPhoneNum;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryRequest() {
        return deliveryRequest;
    }

    public void setDeliveryRequest(String deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    @Override
    public String toString() {
        return "ProductOrder [orderNum=" + orderNum + ", memberNum=" + memberNum + ", orderDate=" + orderDate
                + ", payment=" + payment + ", receiverName=" + receiverName + ", deliveryPhoneNum=" + deliveryPhoneNum
                + ", deliveryAddress=" + deliveryAddress + ", deliveryRequest=" + deliveryRequest + "]";
    }
}
package ezen.call.domain.order.dto;

import ezen.call.domain.product.dto.Product;

/**
 * 카트 클래스
 * 제품, 수량
 * @author 이희영
 */
public class Cart {
    
    Product product;
    int count;
    
    public Cart() {}
    
    public Cart(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart [product=" + product + ", count=" + count + "]";
    }    
}
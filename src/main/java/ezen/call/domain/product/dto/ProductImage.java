package ezen.call.domain.product.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 김종원
 */
public class ProductImage {

    int productImageNum;
    String productImagePath;
    int productNum;
    
    public ProductImage() {}
    
    public ProductImage(int productImageNum, String productImagePath, int productNum) {
        this.productImageNum = productImageNum;
        this.productImagePath = productImagePath;
        this.productNum = productNum;
    }
    
    public int getProductImageNum() {
        return productImageNum;
    }
    
    public void setProductImageNum(int productImageNum) {
        this.productImageNum = productImageNum;
    }
    
    public String getProductImagePath() {
        return productImagePath;
    }
    
    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }
    
    public int getProductNum() {
        return productNum;
    }
    
    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
    
    @Override
    public String toString() {
        return "ProductImage [productImageNum=" + productImageNum + ", productImagePath=" + productImagePath
                + ", productNum=" + productNum + "]";
    }
}
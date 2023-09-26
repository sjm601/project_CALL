package ezen.call.domain.order.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 김종원
 */
public class OrderProduct {
    
    int productNum;
    String productName;
    String category;
    int price;
    String thumbnailPath;
    String description;
    String saleWhether;
    
    public OrderProduct() {}

    public OrderProduct(int productNum, String productName, String category, int price, String thumbnailPath,
            String description, String saleWhether) {
        this.productNum = productNum;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.thumbnailPath = thumbnailPath;
        this.description = description;
        this.saleWhether = saleWhether;
    }
    
    public OrderProduct(String productName, String category, int price, String thumbnailPath,
            String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.thumbnailPath = thumbnailPath;
        this.description = description;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSaleWhether() {
        return description;
    }

    public void setSaleWhether(String saleWhether) {
        this.saleWhether = saleWhether;
    }

    @Override
    public String toString() {
        return "Product [productNum=" + productNum + ", productName=" + productName + ", category=" + category
                + ", price=" + price + ", thumbnailPath=" + thumbnailPath + ", description=" + description
                + ", saleWhether=" + saleWhether + "]";
    }
}
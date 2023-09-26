package ezen.call.domain.review.dto;

/**
 * JavaBean 규약에 따라 만든 재사용 가능한 컴포넌트
 * @author 박상훈
 */
public class Review {
    
    private int productReviewNum;
    private int productNum;
    private int reviewRaiting;
    private String reviewContent;
    private String reviewDate;
    private String writerName;
    
    public Review() {}

    public Review(int productReviewNum, int productNum, int reviewRaiting, String reviewContent, String reviewDate,
            String writerName) {
        this.productReviewNum = productReviewNum;
        this.productNum = productNum;
        this.reviewRaiting = reviewRaiting;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.writerName = writerName;
    }

    public int getProductReviewNum() {
        return productReviewNum;
    }

    public void setProductReviewNum(int productReviewNum) {
        this.productReviewNum = productReviewNum;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getReviewRaiting() {
        return reviewRaiting;
    }

    public void setReviewRaiting(int reviewRaiting) {
        this.reviewRaiting = reviewRaiting;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    @Override
    public String toString() {
        return "Reivew [productReviewNum=" + productReviewNum + ", productNum=" + productNum + ", reviewRaiting="
                + reviewRaiting + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate + ", writerName="
                + writerName + "]";
    }
}
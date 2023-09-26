package ezen.call.domain.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ezen.call.domain.review.dto.Review;

/**
 * RDB를 통해 리뷰 저장 및 목록 구현체
 * @author 박상훈
 */
public class JdbcReviewDao  implements ReviewDao {
    
    /**
     * 신규리뷰 등록
     * @param Connection 커넥션
     * @param Review 리뷰
     * @return Boolean 성공 여부
     */
    public boolean create(Connection connection, Review review){
        boolean success = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO product_review (")
          .append("    product_review_number,")
          .append("    product_number,")
          .append("    review_rating,")
          .append("    review_content,")
          .append("    writer_name")
          .append(" ) VALUES (")
          .append("   product_review_number_seq.NEXTVAL,")
          .append("   ?,")
          .append("   ?,")
          .append("   ?,")
          .append("   ?)");
        
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, review.getProductNum());
            pstmt.setInt(2, review.getReviewRaiting());
            pstmt.setString(3, review.getReviewContent());
            pstmt.setString(4, review.getWriterName());
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
     * 총 리뷰 수 조회
     * @param Connection 커넥션
     * @return Int 리뷰 수
     */
    @Override
    public int getCountAll(Connection connection) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT COUNT(*) cnt")
          .append(" FROM product_review");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return count;
    }
    
    /**
     * 해당 제품 리뷰 총 수
     * @param Connection 커넥션
     * @param Int 제품 번호
     * @return Int 리뷰 수
     */
    @Override
    public int getCountByProduct(Connection connection, int ProductNumber) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT COUNT(*) cnt")
          .append(" FROM product_review")
          .append(" WHERE product_number = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, ProductNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return count;
    }
    
    /**
     * 리뷰 전체 리스트 조회 (페이징처리)
     * @param Connection 커넥션
     * @param Int 요청 페이지
     * @param Int 항목 수
     * @return List<Review> 리뷰 리스트
     */
    @Override
    public List<Review> findByAll(Connection connection, int requestPage, int elementSize) {
        List<Review>  list = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  page, num, product_number, review_rating, review_content, review_date, writer_name")
          .append(" FROM (  SELECT  ceil(ROWNUM / ?) page, num, product_number,  review_rating,   review_content,   review_date, writer_name")
          .append("         FROM (  SELECT m1.product_review_number num, m2.product_number product_number, m1.review_rating review_rating, m1.review_content review_content,  to_char(m1.review_date, 'yyyy-MM-DD') review_date,  m1.writer_name writer_name")
          .append("                 FROM product_review m1")
          .append("                     JOIN product m2 ON m1.product_number = m2.product_number")
          .append("                 ORDER BY product_review_number DESC) )")
          .append(" WHERE  page = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, elementSize);
            pstmt.setInt(2, requestPage);
            rs = pstmt.executeQuery();
            list = new ArrayList<Review>();
            while (rs.next()) {            
                int num = rs.getInt("num");
                int productNum = rs.getInt("product_number");
                int reviewRating = rs.getInt("review_rating");
                String reviewContent = rs.getString("review_content");
                reviewContent = reviewContent.replaceAll("\r", "<br>");
                String reviewDate = rs.getString("review_date");
                String writerName = rs.getString("writer_name");
                Review review = new Review();
                review.setProductReviewNum(num);
                review.setProductNum(productNum);
                review.setReviewRaiting(reviewRating);
                review.setReviewContent(reviewContent);
                review.setReviewDate(reviewDate);
                review.setWriterName(writerName);
                list.add(review);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }
    
    /**
     * 해당 제품 리뷰 리스트 조회 (페이징 처리)
     * @param Connection 커넥션
     * @param Int 요청 페이지
     * @param Int 항목 수
     * @param Int 제품 번호
     * @return List<Review> 리뷰 리스트
     */
    @Override
    public List<Review> findByProduct(Connection connection, int requestPage, int elementSize, int productNumber) {
        List<Review>  list = null;
        StringBuilder sb = new StringBuilder();
        sb.append("     SELECT")
            .append("     page,")
            .append("     num,")
            .append("     product_number,")
            .append("     review_rating,")
            .append("     review_content,")
            .append("     review_date,")
            .append("     writer_name")
            .append(" FROM (")
            .append("         SELECT")
            .append("             ceil(ROWNUM / ?) page,")
            .append("             num,")
            .append("             product_number,")
            .append("             review_rating,")
            .append("             review_content,")
            .append("             review_date,")
            .append("             writer_name")
            .append("         FROM (")
            .append("                 SELECT")
            .append("                     m1.product_review_number              num,")
            .append("                     m2.product_number                     product_number,")
            .append("                     m1.review_rating                      review_rating,")
            .append("                     m1.review_content                     review_content,")
            .append("                     to_char(m1.review_date, 'yyyy-MM-DD') review_date,")
            .append("                     m1.writer_name                        writer_name")
            .append("                 FROM")
            .append("                          product_review m1")
            .append("                     JOIN product m2 ON m1.product_number = m2.product_number")
            .append("                 WHERE m1.product_number = ?")
            .append("                 ORDER BY")
            .append("                     product_review_number DESC)")
            .append("     )")
            .append(" WHERE page = ?");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, elementSize);
            pstmt.setInt(2, productNumber);
            pstmt.setInt(3, requestPage);
            rs = pstmt.executeQuery();
            list = new ArrayList<Review>();
            while (rs.next()) {         
                int num = rs.getInt("num");
                int productNum = rs.getInt("product_number");
                int reviewRating = rs.getInt("review_rating");
                String reviewContent = rs.getString("review_content");
                reviewContent = reviewContent.replaceAll("\r", "<br>");
                String reviewDate = rs.getString("review_date");
                String writerName = rs.getString("writer_name");
                Review review = new Review();
                review.setProductReviewNum(num);
                review.setProductNum(productNum);
                review.setReviewRaiting(reviewRating);
                review.setReviewContent(reviewContent);
                review.setReviewDate(reviewDate);
                review.setWriterName(writerName);
                list.add(review);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {}
        }
        return list;
    }
    
   /**
     * 해당 제품 상품 평점 조회
     * @param Connection 커넥션
     * @param Int 제품 번호
     * @return Float 평점
     */
   @Override
   public float findRatingByProduct(Connection connection, int ProductNum) {
       float rating = 0;
       StringBuilder sb = new StringBuilder();
       sb.append(" SELECT avg(review_rating) rating")
         .append(" FROM product_review")
         .append(" WHERE product_number = ?");
       
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       try {
           pstmt = connection.prepareStatement(sb.toString());
           pstmt.setInt(1, ProductNum);
           rs = pstmt.executeQuery();
           if (rs.next()) {
               rating = rs.getFloat("rating");
           }
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       } finally {
           try {
               if (rs != null) rs.close();
               if (pstmt != null) pstmt.close();
           } catch (Exception e) {}
       }
       return rating;
   }
}
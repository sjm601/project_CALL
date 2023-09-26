package ezen.call.domain.review.dao;
import java.sql.Connection;
import java.util.List;

import ezen.call.domain.review.dto.Review;

/**
 * 데이터 베이스 게시글 관리 명세
 *  @author 박상훈
 */
public interface ReviewDao {
    
	// 신규리뷰 등록
    public boolean create(Connection connection, Review review);
    // 총 리뷰 수 조회
    public int getCountAll(Connection connection);
    // 해당 제품 리뷰 총 수
    public int getCountByProduct(Connection connection, int productNumber);
    // 리뷰 전체 리스트 조회 (페이징처리)
    public List<Review> findByAll(Connection connection, int requestPage, int elementSize);
    // 해당 제품 리뷰 리스트 조회 (페이징 처리)
    public List<Review> findByProduct(Connection connection, int requestPage, int elementSize, int productNumber);
    // 해당 제품 상품 평점 조회
    public float findRatingByProduct(Connection connection, int ProductNum);
}

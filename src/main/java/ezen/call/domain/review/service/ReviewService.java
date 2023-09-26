package ezen.call.domain.review.service;

import java.util.List;

import ezen.call.domain.review.dto.Review;
import ezen.call.web.common.page.PageParams;

/**
 * 방명록 관련 비즈니스 로직 처리 및 트랜잭션 관리를 위한 명세
 * @author 박상훈
 */
public interface ReviewService {

	// 리뷰 등록
    public void writeReview(Review review, int orderListNum);
    // 총 리뷰 수 조회
    public int getReviewCount();
    // 해당 제품 총 리뷰 수 조회
    public int getReviewCountByProduct(int productNumber);
    // 리뷰 리스트 조회 (페이징 처리)
    public List<Review> getReviews(PageParams params);
    //해당 제품 리뷰 리스트 조회 (페이징 처리)
    public List<Review> getReviewsByProduct(PageParams params, int productNumber);
    //해당 제품 평점 조회
    public float getRatingByProduct(int productNumber);
}
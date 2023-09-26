package ezen.call.domain.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ezen.call.domain.order.dao.OrderListDao;
import ezen.call.domain.order.service.OrderService;
import ezen.call.domain.review.dao.ReviewDao;
import ezen.call.domain.review.dto.Review;
import ezen.call.web.common.page.PageParams;

/**
 * 방명록 관련 비즈니스 로직 처리 및 트랜잭션 처리 구현체
 */
public class ReviewServiceImpl implements ReviewService {

    private DataSource dataSource;
    private ReviewDao reviewDao;
    private OrderListDao orderListDao;

    public ReviewServiceImpl(DataSource dataSource, ReviewDao reviewDao, OrderListDao orderListDao) {
        this.dataSource = dataSource;
        this.reviewDao = reviewDao;
        this.orderListDao = orderListDao;
    }

    /**
     * 리뷰 등록
     * @param Review 리뷰
     */
    @Override
    public void writeReview(Review review, int orderListNum) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            reviewDao.create(connection, review);
            orderListDao.updateOrderListByReview(connection, orderListNum);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
    }
    
    /**
     * 총 리뷰 수 조회
     * @return Int 리뷰 수
     */
    @Override
    public int getReviewCount() {
        int count = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            count = reviewDao.getCountAll(connection);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return count;
    }
    
    /**
     * 해당 제품 총 리뷰 수 조회
     * @param Int 제품 번호
     * @return Int 리뷰 수
     */
    @Override
    public int getReviewCountByProduct(int productNumber) {
        int count = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            count = reviewDao.getCountByProduct(connection, productNumber);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return count;
    }

    /**
     * 리뷰 리스트 조회 (페이징 처리)
     * @param PageParams 페이징
     * @return List<Review> 리뷰 리스트
     */
    @Override
    public List<Review> getReviews(PageParams params) {
        List<Review> list = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            list = reviewDao.findByAll(connection, params.getRequestPage(), params.getElementSize());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return list;
    }
    
    /**
     * 해당 제품 리뷰 리스트 조회 (페이징 처리)
     * @param PageParams 페이징
     * @param Int 제품 번호
     * @return List<Review> 해당 제품 리뷰 리스트
     */
    @Override
    public List<Review> getReviewsByProduct(PageParams params, int productNumber) {
        List<Review> list = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            list = reviewDao.findByProduct(connection, params.getRequestPage(), params.getElementSize(), productNumber);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return list;
    }

    /**
     * 해당 제품 평점 조회
     * @param Int 제품 번호
     * @return Float 평점
     */
    @Override
    public float getRatingByProduct(int productNumber) {
        float rating = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            rating = reviewDao.findRatingByProduct(connection, productNumber);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e1) {}
        }
        return rating;
    }

    // 테스트 메인
    public static void main(String[] args) {
//        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        ReviewService reviewService = serviceFactory.getReviewService();
//        PageParams params = new PageParams(4,5,3,0);
//        Review review = new Review(90, 5, 5, "이쁘게 잘쓰겠습니다", null, "박상훈");
//        // 리뷰 추가
//        reviewService.writeReview(review);
//        
//        // 리뷰 전체 개수 
//        int all = reviewService.getReviewCount();
//        System.out.println(all);
//
//        // 요청 페이지
//        List<Review> reviews = reviewService.getReviews(params);
//        System.out.println(reviews);
//        
          // 특정 제품 리뷰
//        List<Review> reviews = reviewService.getReviewsByProduct(params, 1);
//        for (Review review : reviews) {
//         System.out.println(review);
//        }
    }
}
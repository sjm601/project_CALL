package ezen.call.domain.common.factory;

import ezen.call.domain.member.dao.JdbcMemberDao;
import ezen.call.domain.member.dao.JdbcMemberHistoryDao;
import ezen.call.domain.member.dao.MemberDao;
import ezen.call.domain.member.dao.MemberHistoryDao;
import ezen.call.domain.order.dao.JdbcOrderListDao;
import ezen.call.domain.order.dao.JdbcProductOrderDao;
import ezen.call.domain.order.dao.OrderListDao;
import ezen.call.domain.order.dao.ProductOrderDao;
import ezen.call.domain.product.dao.JdbcProductDao;
import ezen.call.domain.product.dao.ProductDao;
import ezen.call.domain.review.dao.JdbcReviewDao;
import ezen.call.domain.review.dao.ReviewDao;

/**
 * Simple Factory 적용
 * @author 이희영
 */
public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    // MemberDao 구현체 반환
    public MemberDao getMemberDao() {
        return new JdbcMemberDao();
    }
    
    // MemberHistoryDao 구현체 반환
    public MemberHistoryDao getMemberHistoryDao() {
        return new JdbcMemberHistoryDao();
    }
    
    // ProductDao 구현체 반환
    public ProductDao getProductDao() {
        return new JdbcProductDao();
    }
    
    // OrderListDao 구현체 반환
    public OrderListDao getOrderListDao() {
        return new JdbcOrderListDao();
    }
    
    // ProductOrderDao 구현체 반환
    public ProductOrderDao getProductOrderDao() {
        return new JdbcProductOrderDao();
    }
    
    // ReviewDao 구현체 반환
    public ReviewDao getReviewDao() {
        return new JdbcReviewDao();
    }
}
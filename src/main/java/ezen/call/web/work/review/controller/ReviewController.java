package ezen.call.web.work.review.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.member.dto.MemberCombined;
import ezen.call.domain.review.dto.Review;
import ezen.call.domain.review.service.ReviewService;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 리뷰 요청 처리 세부 컨트롤러
 * @author 박상훈
 * /order/order_confirm
 */
public class ReviewController implements HttpController {

    private ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        String viewName = "/order/order_confirm"; 
        
        // 리뷰 작성 요청 (POST)
        if (request.getMethod().equalsIgnoreCase("post")) {
            String content = paramMap.get("content");
            int rating = Integer.parseInt(paramMap.get("rating"));
            int productNum = Integer.parseInt(request.getParameter("productNum"));
            int orderListNum = Integer.parseInt(request.getParameter("orderListNum"));

            MemberCombined memberCombined = (MemberCombined) request.getSession().getAttribute("loginMember");
            String memberName = memberCombined.getMember().getMemberName();

            Review review = new Review();
            review.setWriterName(memberName);
            review.setReviewRaiting(rating);
            review.setProductNum(productNum);
            review.setReviewContent(content);
            
            reviewService.writeReview(review, orderListNum);
            viewName = "redirect:/mall/order/orderconfirm";
        }
        return viewName;
    }
}
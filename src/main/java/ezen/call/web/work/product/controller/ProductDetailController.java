package ezen.call.web.work.product.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ezen.call.domain.common.factory.ServiceFactory;
import ezen.call.domain.order.dto.Cart;
import ezen.call.domain.product.dto.Product;
import ezen.call.domain.product.service.ProductService;
import ezen.call.domain.review.dto.Review;
import ezen.call.domain.review.service.ReviewService;
import ezen.call.web.common.page.PageParams;
import ezen.call.web.common.page.Pagination;
import ezen.call.web.mvc.controller.HttpController;

/**
 * 제품 상세 페이지 처리 세부 컨트롤러
 * @author 이희영 /product/detail
 */
public class ProductDetailController implements HttpController {

	private ProductService productService = ServiceFactory.getInstance().getProductService();
	private ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
	int productNum = 0;

	// 한 페이지에 보여지는 목록 갯수 설정
	private static final int ELEMENT_SIZE = 3;
	// 한페이지에 보여지는 페이지 갯수 설정
	private static final int PAGE_SIZE = 5;

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		String viewName = "product/detail";

		List<Cart> carts = null;

		// 상품 상세 페이지 요청 (GET)
		if (request.getQueryString() != null) {
			// 상품번호 파라메터 가져오기
			String productParam = request.getParameter("productNum");
			productNum = Integer.parseInt(productParam);

			Product product = productService.readProductByNum(productNum);
			request.getSession().setAttribute("product", product);

			// 평점 출력
			double avgRating = Math.round(reviewService.getRatingByProduct(productNum) * 10) / 10.0;
			request.getSession().setAttribute("avgRating", avgRating);

			// 사용자 선택페이지
			String requestPage = paramMap.get("page");
			if (requestPage == null || requestPage.equals("")) {
				requestPage = "1";
			}
			int selectPage = Integer.parseInt(requestPage);

			// 페이징 계산을 위한 게시글 전체 갯수 조회
			int rowCount = reviewService.getReviewCountByProduct(productNum);

			// 전체 페이지수 계산
			PageParams params = new PageParams(ELEMENT_SIZE, PAGE_SIZE, selectPage, rowCount);
			Pagination pagination = new Pagination(params);
			List<Review> list = reviewService.getReviewsByProduct(params, productNum);

			// 모델에 상품 정보 넣기
			model.put("product", product);
			// 모델에 게시글 목록 설정
			model.put("list", list);
			// 모델에 페이징 정보 설정
			model.put("pagination", pagination);
			// 모델에 카운트 정보 설정
			model.put("rowCount", rowCount);

			// 추천상품 조회
			String category = product.getCategory();
			List<Product> productList = productService.detailProductList(category);
			// 모델에 추천상품 목록 설정
			model.put("productList", productList);

			viewName = "product/detail";

		// 상세 페이지 제품 수량 변경 요청 (POST)
		} else if (request.getMethod().equalsIgnoreCase("post")) {
			int count = Integer.parseInt(paramMap.get("count"));
			Product product = (Product) request.getSession().getAttribute("product");

			if (request.getSession().getAttribute("cart") == null) {
				Cart cart = new Cart();
				carts = new LinkedList<Cart>();
				cart.setProduct(product);
				cart.setCount(count);
				carts.add(cart);
			} else {
				carts = (List<Cart>) request.getSession().getAttribute("cart");
				Cart cart = new Cart();
				boolean exist = false;
				for (Cart cartContent : carts) {
					if (cartContent.getProduct().getProductNum() == product.getProductNum()) {
						int productCount = cartContent.getCount();
						cartContent.setCount(count + productCount);
						if (cartContent.getCount() > 9) {
							cartContent.setCount(9);
						}
						exist = true;
					}
				}
				cart.setProduct(product);
				cart.setCount(count);
				if (!exist) {
					carts.add(cart);
				}
			}
			request.getSession().setAttribute("cart", carts);
			
			if(paramMap.get("purchase").equalsIgnoreCase("cart")) {
				if (paramMap.get("move").equalsIgnoreCase("no")) {
					viewName = "redirect:/mall/product/category?productNum=" + productNum;
				} else if (paramMap.get("move").equalsIgnoreCase("move")) {
					viewName = "redirect:/mall/order/cart";
				}
			} else if (paramMap.get("purchase").equalsIgnoreCase("purchase")) {
				int price = product.getPrice();
				 List<Cart> orderList = (List<Cart>)request.getSession().getAttribute("cart");
	            request.getSession().setAttribute("totalPrice", price);
	            request.getSession().setAttribute("orderList", orderList);
	            request.getSession().removeAttribute("avgRating");
	            viewName = "redirect:/mall/order/order";
			}
		}
		return viewName;
	}
}
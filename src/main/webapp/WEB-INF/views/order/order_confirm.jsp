<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- CSS CDN -->
<jsp:include page="/WEB-INF/views/modules/css_cdn.jsp" />
<!-- JavaScript CDN -->
<jsp:include page="/WEB-INF/views/modules/js_cdn.jsp" />
<!-- CSS & JavaScript -->
<link rel="stylesheet" href="/css/global.css" />
<link rel="stylesheet" href="/css/order_confirm.css" />
<script defer src="/js/order_confirm.js"></script>
<title>Order_confirm</title>
</head>

<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/modules/header.jsp" />
	<!-- Order Confirm 시작 -->
	<div class="container mb-5 order-confirms">
		<section class="order-confirm text-center my-5">
			<input type="hidden" id="productNum" name="productNum" value="" />
			<h2>구매내역</h2>
			<c:forEach items="${confirmList}" var="orderConfirm">
				<div class="mb-5">
					<div
						class="fs-4 d-flex justify-content-start align-self-center px-3 py-2">
						${orderConfirm.productOrder.orderDate}</div>
					<div class="m_hr border-bottom"></div>
					<c:forEach items="${orderConfirm.orderListAndProducts}"
						var="orderListAndProd" varStatus="loop">
						<div class="py-2">
							<div class="d-flex">
								<div class="px-5 align-self-center">${loop.count}</div>
								<div>
									<a href="${path}/product/category?productNum=${orderListAndProd.product.productNum}">
										<img src="${orderListAndProd.product.thumbnailPath}">
									</a>
								</div>
								<div class="px-5 flex-fill text-start align-self-center">
									<div>
										<div class="pkind pb-2">${orderListAndProd.product.category}</div>
										<div class="pname">${orderListAndProd.product.productName}</div>
									</div>
								</div>
								<div class="px-5 mx-2 align-self-center">수량 :
									${orderListAndProd.orderList.orderCount}개</div>
								<div class="px-5 mx-2 align-self-center confirmPrice"><fmt:formatNumber value="${orderListAndProd.product.price * orderListAndProd.orderList.orderCount}" pattern="#,###" />원</div>
								<div class="px-5 mx-2 align-self-center">
									<button class="btn btn-outline-secondary open-modal" type="button"
										data-bs-toggle="modal"
										data-bs-target="#exampleModal_${orderListAndProd.orderList.orderListNum}"
										data-bs-whatever="@mdo"
										data-productnum="${orderListAndProd.product.productNum}"
										onclick="setProductNum(this)" <c:if test="${orderListAndProd.orderList.reviewComp == 'T'}">disabled</c:if>>리뷰작성</button>
								</div>
							</div>
						</div>
						<div class="c_hr border-bottom"></div>
					</c:forEach>
				</div>
			</c:forEach>
		</section>
	</div>
	<!-- Order Confirm 끝-->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/modules/footer.jsp" />
	<!-- Review Modal 시작 -->
	<c:forEach items="${confirmList}" var="orderConfirm" varStatus="loop">
		<c:forEach items="${orderConfirm.orderListAndProducts}"
			var="orderListAndProd" varStatus="loop1">
			<form action="${path}/order/review" id="review-form-${orderListAndProd.orderList.orderListNum}"
				method="post">
				<div class="modal fade" id="exampleModal_${orderListAndProd.orderList.orderListNum}"
					tabindex="-1" aria-labelledby="exampleModalLabel_${loop.index}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">리뷰 작성</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<input type="hidden" name="productNum"
								value="${orderListAndProd.product.productNum}" />
							<input type="hidden" name="orderListNum"
                                value="${orderListAndProd.orderList.orderListNum}" />
							<div class="modal-body">
								<div class="star-rating space-x-4 mx-auto">
									<input type="radio" id="5-stars-${orderListAndProd.orderList.orderListNum}" name="rating" value="5" />
									<label for="5-stars-${orderListAndProd.orderList.orderListNum}" class="star pr-4">★</label> 
									<input type="radio" id="4-stars-${orderListAndProd.orderList.orderListNum}" name="rating" value="4" /> 
									<label for="4-stars-${orderListAndProd.orderList.orderListNum}" class="star">★</label>
									<input type="radio" id="3-stars-${orderListAndProd.orderList.orderListNum}" name="rating" value="3" />
									<label for="3-stars-${orderListAndProd.orderList.orderListNum}" class="star">★</label>
									<input type="radio" id="2-stars-${orderListAndProd.orderList.orderListNum}" name="rating" value="2" />
									<label for="2-stars-${orderListAndProd.orderList.orderListNum}" class="star">★</label>
									<input type="radio" id="1-star-${orderListAndProd.orderList.orderListNum}" name="rating" value="1" checked />
									<label for="1-star-${orderListAndProd.orderList.orderListNum}" class="star">★</label>
								</div>
								<div class="mb-3">
									<textarea class="form-control" id="message-text-${orderListAndProd.orderList.orderListNum}"
										name="content" placeholder="리뷰를 작성해 주세요..." required></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-primary"
									id="send-message-btn-${orderListAndProd.orderList.orderListNum}">작성하기</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
	</c:forEach>
	<!-- Review Modal 끝 -->
</body>

</html>
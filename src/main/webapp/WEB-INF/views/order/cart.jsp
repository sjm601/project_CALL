<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />

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
<link rel="stylesheet" href="/css/cart.css" />
<script defer src="/js/cart.js"></script>
<title>Cart</title>
</head>

<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/modules/header.jsp" />

	<!-- cart 시작-->
	<div class="container carts">
		<section class="cart text-center my-5">
			<form action="/mall/order/cart" method="post">
				<h2>장바구니</h2>
				<div class="d-flex justify-content-end align-self-center px-3 py-2">
					<c:out value="${today}" />
				</div>
				<div class="m_hr border-bottom"></div>

				<c:forEach items="${cartList}" var="cartList" varStatus="status">
					<div class="py-2">
						<div class="d-flex">
							<div class="px-5 align-self-center cartListNumber">${status.count}</div>
							<div>
								<img src="${cartList.product.thumbnailPath}">
							</div>
							<div class="px-5 flex-fill text-start align-self-center">
								<div>
									<div class="pkind pb-2">${cartList.product.category}</div>
									<div class="pname">${cartList.product.productName}</div>
									<div class="pPrice">
										<fmt:formatNumber value="${cartList.product.price}"
											pattern="#,###" />
										원
									</div>
								</div>
							</div>
							<div class="d-flex align-self-center px-3">
								<div>
									<div class="align-self-center py-1 px-2">수량</div>
								</div>
								<div>
									<div class="input-group input-group-sm px-2">
										<a
											href="${path}/order/cart?cartCount=minus&productNum=${cartList.product.productNum}"
											class="cnt cnt-minus btn btn-outline-secondary" type="button">-</a>
										<input type="text" readonly
											class="cnt-count form-control text-center"
											value="${cartList.count}"> <a
											href="${path}/order/cart?cartCount=plus&productNum=${cartList.product.productNum}"
											class="cnt cnt-plus btn btn-outline-secondary" type="button">+</a>
									</div>
								</div>
							</div>
							<div class="px-5 align-self-center priceDiv">
								<span class="countPrice"><fmt:formatNumber
										value="${cartList.product.price * cartList.count}"
										pattern="#,###" /></span>원
							</div>
							<div class="px-5 align-self-center">
								<button class="btn btn-outline-secondary deleteBtn"
									type="button">삭제</button>
							</div>
						</div>
					</div>
					<div class="c_hr border-bottom mt-1"></div>
				</c:forEach>
				<div class="d-flex justify-content-end py-2 mt-3">
					<div class="price align-self-center px-3">
						총 금액 : <span id="totalPrice"><fmt:formatNumber
								value="${totalPrice}" pattern="#,###" /></span>원
					</div>
					<div class="align-self-center px-3">
						<c:choose>
							<c:when test="${not empty loginMember}">
								<button type="submit" class="btn btn-outline-dark orderBtn" <c:if test="${empty cartList}">disabled</c:if>>주문하기</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-outline-dark orderBtn">주문하기</button>
								<div class="sub_text">주문하기 위해선 로그인을 해주세요..</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form>
		</section>
	</div>
	<!-- cart 끝-->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/modules/footer.jsp" />
</body>

</html>
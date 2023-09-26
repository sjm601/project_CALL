<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Font Awesome CSS CDN -->
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous" />
<!-- Google Fonts CSS CDN -->
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- CSS & JavaScript -->
<link rel="stylesheet" href="/css/global.css" />
<link rel="stylesheet" href="/css/detail.css" />
<script defer src="/js/detail.js"></script>
<!-- Sweetalert JavaScript CDN -->
<script defer src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- Bootstrap JavaScript CDN -->
<script defer
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
<title>Detail</title>
</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/header.jsp" />

    <!-- Detail 메인 페이지-->
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${path}/index">홈</a></li>
                <li class="breadcrumb-item"><a
                    href="${path}/product/${product.category}?<c:choose><c:when test="${product.category eq 'glasses'}">ng</c:when><c:otherwise>sg</c:otherwise></c:choose>">${product.category}</a></li>
                <li class="breadcrumb-item active" aria-current="page">${product.productName}</li>
            </ol>
        </nav>
        <!-- 선택 아이템 세부정보 -->
        <!--이미지-->
        <div class="pickitem">
            <div class="left-column">
                <img class="ng-image1 shadow" src="${product.thumbnailPath}"
                    alt="Product Image">
            </div>
            <!--설명-->
            <form class="right-column" action="${path}/product/category"
                method="post" id="detial-form">
                <p class="category">${product.category}</p>
                <p class="i-name">${product.productName}</p>
                <p class="price">
                    <fmt:formatNumber value="${product.price}" pattern="#,###" />
                    원
                </p>
                <div class="describe">
                    <p class="des-p">${product.description}</p>
                </div>
                <div class="down-des">
                    <hr class="dropdown-divider">
                </div>
                <div class="d-flex align-self-center px-3">
                    <div>
                        <div class="align-self-center py-1 px-2">수량</div>
                    </div>
                    <div class="mb-3">
                        <div class="input-group input-group-sm px-2">
                            <button class="cnt cnt-minus btn btn-outline-secondary"
                                type="button">-</button>
                            <input type="text" readonly
                                class="cnt cnt-count form-control text-center" name="count"
                                value="1">
                            <button class="cnt cnt-plus btn btn-outline-secondary"
                                type="button">+</button>
                            <input type="hidden" id="purchase" name="purchase" value="cart" />
                        </div>
                    </div>
                </div>
                <div class="button-list">
                    <button type="button" id="cartBtn" class="btn btn-outline-secondary me-4">장바구니</button>
                    <button type="button" id="purchaseBtn" class="btn btn-outline-secondary me-4 <c:if test="${empty loginMember}">disabled</c:if>">구매하기</button>
                </div>
            </form>
        </div>
    </div>
    <!--리뷰 페이지-->
    <section class="container">
        <h3>리뷰 (${rowCount})</h3>
        <div class="divide-con">
            <hr class="divider">
        </div>
        <div class="review-box">
            <p class="star-score my-0 py-2">
                ☆
                <%=request.getSession().getAttribute("avgRating")%>
                <span class="all-review">총 ${rowCount}건의 리뷰</span>
            </p>
        </div>
        <div class="c_hr border-bottom"></div>
        <div class="review-con">
            <%-- 리뷰 목록 반복 --%>
            <c:forEach items="${list}" var="review">
                <div class="comment-item">
                    <div class="review-info">
                        <div class="d-flex bd-highlight mb-3">
                            <span class="material-symbols-outlined p-2 bd-highlight">
                                account_circle </span>
                            <div class="name p-2 bd-highlight">
                                <c:out
                                    value="${fn:substring(review.writerName, 0, fn:length(review.writerName) - 2) }" />
                                **
                            </div>
                            <div class="star p-2 bd-highlight">★${review.reviewRaiting}</div>
                            <div class="ms-auto p-2 bd-highlight">${review.reviewDate }</div>
                        </div>
                    </div>
                    <p class="review-commnet">${review.reviewContent}</p>
                    <div class="c_hr border-bottom"></div>
                </div>
            </c:forEach>
            <!--페이지네이션-->

            <nav aria-label="page navigation">
                <ul class="pagination d-flex justify-content-center">

                    <c:if test="${pagination.showPrevious}">
                        <li class="page-item"><a class="page-link"
                            href="${path}/product/category?productNum=${product.productNum}&page=${pagination.previousStartPage}"
                            aria-label="Previous"> <span aria-hidden="true"
                                class="page-icon material-symbols-outlined">
                                    arrow_back_ios </span>
                        </a></li>
                    </c:if>
                    <!-- 페이지 -->
                    <c:forEach var="i" begin="${pagination.startPage}"
                        end="${pagination.endPage}">
                        <c:choose>
                            <c:when test="${i == pagination.params.requestPage}">
                                <li class="page-item"><a class="page-link"
                                    href="${path}/product/category?productNum=${product.productNum}&page=${i}">
                                        ${i} </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                    href="${path}/product/category?productNum=${product.productNum}&page=${i}">
                                        ${i} </a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <!-- 다음 페이지 -->
                    <c:if test="${pagination.showNext}">
                        <li class="page-item"><a class="page-link"
                            href="${path}/product/category?productNum=${product.productNum}&page=${pagination.nextStartPage}"
                            aria-label="Next"> <span aria-hidden="true"
                                class="page-icon material-symbols-outlined">
                                    arrow_forward_ios </span>
                        </a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <!-- 추천 상품-->
        <div class="recommend">
            <div class="banner text-center mb-5">
                <h1 class="py-5">추천상품</h1>
            </div>
            <div class="product">
                <ul class="row d-flex justify-content-between">
                    <c:forEach items="${productList}" var="productList">
                        <li class="col-sm-5 col-md-3">
                            <div class="product-imgbox mb-3">
                                <a
                                    href="${path}/product/category?productNum=${productList.productNum}"><img
                                    class="product-image shadow" src="${productList.thumbnailPath}"></a>
                            </div>
                            <div class="d-flex justify-content-between px-3">
                                <a
                                    href="${path}/product/category?productNum=${productList.productNum}"
                                    class="product-info">${productList.productName}</a> <a
                                    href="${path}/product/category?productNum=${productList.productNum}"
                                    class="product-price"><fmt:formatNumber value="${productList.price}" pattern="#,###" />원</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </section>
    <!-- Detail 메인 페이지 끝-->
    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/modules/footer.jsp" />
</body>

</html>
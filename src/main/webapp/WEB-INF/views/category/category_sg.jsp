<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<link rel="stylesheet" href="/css/category.css" />
<title>Category-sunglasses</title>
</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/header.jsp" />

    <!-- Section 시작 -->
    <section>
        <!-- Main 이미지 -->
        <img class="main-image img-fluid"
            src="/images/main/main_sunglasses.jpg">
        <div class="container">
            <div class="banner text-center">
                <h2 class="mb-4">선글라스</h2>
                <p class="mb-5 fs-5">Lorem ipsum dolor sit, amet consectetur</p>
            </div>
            <!-- Product 시작 -->
            <div class="product-container">
                <!-- 정렬 기능 -->
                <div class="d-flex justify-content-end">
                    <ul class="d-flex">
                        <li class="d-flex"><a class="p-2" href="${path}/product/sunglasses?sortName">이름순</a></li>
                        <li class="d-flex"><a class="p-2" href="${path}/product/sunglasses?sortHighprice">높은 가격순</a></li>
                        <li class="d-flex"><a class="p-2" href="${path}/product/sunglasses?sortLowprice">낮은 가격순</a></li>
                    </ul>
                </div>
                <!-- 제품 -->
                <div class="product row d-flex justify-content-between">
                    <c:forEach items="${productList}" var="product">
                        <div class="product col-sm-10 col-md-5 mb-5">
                            <div class="product-imgbox">
                                <a href="${path}/product/category?productNum=${product.productNum}"><img class="product-image shadow" src="${product.thumbnailPath}"></a>
                            </div>
                            <div class="d-flex justify-content-between p-3">
                                <a href="${path}/product/category?productNum=${product.productNum}">${product.productName}</a>
                                <a href="${path}/product/category?productNum=${product.productNum}"><fmt:formatNumber
                                value="${product.price}" pattern="#,###" />원</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- Product 끝 -->
        </div>
    </section>
    <!-- Section 끝 -->
    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/modules/footer.jsp" />
</body>

</html>
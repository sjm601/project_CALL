<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <!-- CSS CDN -->
  <jsp:include page="/WEB-INF/views/modules/css_cdn.jsp"/>
  <!-- JavaScript CDN -->
  <jsp:include page="/WEB-INF/views/modules/js_cdn.jsp"/>
  <!-- CSS & JavaScript -->
  <link rel="stylesheet" href="/css/global.css" />
  <link rel="stylesheet" href="/css/index.css" />
  <title>C-all Glasses</title>
</head>

<body>
  <!-- Header -->
  <jsp:include page="/WEB-INF/views/modules/header.jsp"/>
  
  <!-- Section 시작 -->
  <section>
    <!-- Main 이미지 -->
    <img class="main-image img-fluid" src="/images/main/main_index.jpg">
    <div class="container">
      <div class="pre-collection">
        <div class="middle-menu text-center">
          <h2 class="mb-4">2024 PRE Collection</h2>
          <p class="mb-5 fs-5">Lorem ipsum dolor sit, amet consectetur</p>
        </div>
        <div class="text-center m-auto">
          <a href="${path}/product/category?productNum=1" class="btn btn-outline-secondary m-5">더 알아보기</a>
          <a href="${path}/product/category?productNum=1" class="btn btn-outline-secondary m-5">구매하기</a>
        </div>
      </div>
      <!-- 신제품 -->
      <div class="new-product">
        <!-- 선글라스 신상 -->
        <div class="mb-5">
          <h3 class="mb-4">New 선글라스</h3>
          <div class="sg-glasses m-auto">
            <ul class="row d-flex justify-content-between ps-0">
                    <c:forEach items="${sunglassesList}" var="sunglasses">
               <li class="col-sm-12 col-md-4 sg-product">
                 <div class="sg-product-imgbox">
                   <a href="${path}/product/category?productNum=${sunglasses.productNum}"><img class="sg-image shadow" src="${sunglasses.thumbnailPath}"></a>
                 </div>
                 <div class="d-flex justify-content-between p-3">
                    <a href="${path}/product/category?productNum=${sunglasses.productNum}">${sunglasses.productName}</a>
                   <a href="${path}/product/category?productNum=${sunglasses.productNum}"><fmt:formatNumber
                                value="${sunglasses.price}" pattern="#,###" />원</a>
                 </div>
               </li>
            </c:forEach>
            </ul>
          </div>
        </div>
        <!-- 안경 신상 -->
        <div class="mb-5">
          <h3 class="mb-4">New 안경</h3>
          <div class="ng-glasses m-auto">
            <ul class="row d-flex justify-content-between ps-0">
               <c:forEach items="${glassesList}" var="glasses">
               <li class="col-sm-12 col-md-4 sg-product">
                 <div class="sg-product-imgbox">
                   <a href="${path}/product/category?productNum=${glasses.productNum}"><img class="sg-image shadow" src="${glasses.thumbnailPath}"></a>
                 </div>
                 <div class="d-flex justify-content-between p-3">
                   <a href="${path}/product/category?productNum=${glasses.productNum}">${glasses.productName}</a>
                   <a href="${path}/product/category?productNum=${glasses.productNum}"><fmt:formatNumber
                                value="${glasses.price}" pattern="#,###" />원</a>
                 </div>
               </li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
      <!-- YouTube 영상 -->
      <div class="youtube">
        <iframe src="https://www.youtube.com/embed/cAWAnKRM1t8?autoplay=1&mute=1%controls=0&loop=1&playlist=cAWAnKRM1t8" title="YouTube video player" frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen></iframe>
        <br>
      </div>
      <!--하단 메뉴 -->
      <div class="bottom-menu text-center">
        <ul class="row d-flex justify-content-between p-0">
          <li class="col-sm-12 col-md-6">
            <div class="position-relative">
              <a href="${path}/product/sunglasses?sg">
                <img class="bottom_item_img sg shadow" src="/images/sg/sgC1.jpg">
                <p class="mb-5 me-5 fs-2 position-absolute">Sunglasses</p>
              </a>
            </div>
            <a href="${path}/product/sunglasses?sg" class="btn btn-outline-secondary m-5">자세히보기</a>
          </li>
          <li class="col-sm-12 col-md-6">
            <div class="position-relative">
              <a href="${path}/product/glasses?ng">
                <img class="bottom_item_img ng shadow" src="/images/ng/ngC1.jpg">
                <p class="mb-5 me-5 fs-2 position-absolute">Glasses</p>
              </a>
            </div>
            <a href="${path}/product/glasses?ng" class="btn btn-outline-secondary m-5">자세히보기</a>
          </li>
        </ul>
      </div>
    </div>
  </section>
  <!-- Section 끝 -->
  <!-- Footer -->
  <jsp:include page="/WEB-INF/views/modules/footer.jsp"/>
</body>

</html>
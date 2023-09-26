<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />

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
  <link rel="stylesheet" href="/css/order.css" />
  <script defer src="/js/order.js"></script>
  <title>Order</title>
</head>

<body>
  <!-- Header -->
  <jsp:include page="/WEB-INF/views/modules/header.jsp"/>
  
  <!-- Section 시작 -->
  <section class="container mb-5">
    <!-- 주문목록 시작 -->
    <div class="order text-center my-5">
      <h2>주문하기</h2>
      <div class="d-flex justify-content-end align-self-center px-3 py-2">
        <c:out value="${today}"/>
      </div>
      <div class="m_hr border-bottom"></div>
      
      <c:forEach items="${orderList}" var="prod" varStatus="loop">
      <div class="py-2">
        <div class="d-flex">
          <div class="px-5 align-self-center">${loop.count}</div>
          <div><img src="${prod.product.thumbnailPath}" width="300px"></div>
          <div class="px-5 flex-fill text-start align-self-center">
            <div>
              <div class="pkind pb-2">${prod.product.category}</div>
              <div class="pname">${prod.product.productName}</div>
            </div>
          </div>
          <div class="px-5 mx-5 align-self-center">수량 : ${prod.count}개</div>
          <div class="px-5 mx-5 align-self-center orderListprice"><fmt:formatNumber value="${prod.product.price*prod.count}" pattern="#,###" />원</div>

        </div>
      </div>
      <div class="c_hr border-bottom"></div>
      </c:forEach>
      
      <div class="d-flex justify-content-end py-2">
        <div class="align-self-center px-1">총 금액 : </div>
        <div class="price align-self-center px-1"><fmt:formatNumber value="${totalPrice}" pattern="#,###" />원</div>
      </div>
    </div>
    <!-- 주문목록 시작 -->
    <form action="${path}/order/order" method="post" class="needs-validation" novalidate>
      <!-- 배송지 정보 시작 -->
      <div class="address-info mb-5">
        <div class="address-info-header">
          <h2 class="pb-1">배송지 정보</h2>
        </div>
        <div class="address-input-group p-3">
          <div class="row mb-2">
            <p class="p_box col-1">받는 분</p>
            <input type="text" class="form-control col-3 rounded-1 border-1 ps-2 short" name="deliName" value="${loginMember.member.memberName}" placeholder="이름을 입력하세요" required>
            <div class="invalid-feedback col"> 받는 분의 성함은 반드시 입력하여야합니다...</div>
          </div>
          <div class="row mb-2">
            <p class="p_box col-1">받는 분 연락처</p>
            <input type="number" class="form-control col-3 rounded-1 border-1 ps-2 short" name="deliPhone" value="${loginMember.member.phoneNum}" placeholder="연락처 (예: 01012341234)" required>
            <div class="invalid-feedback col"> 연락처는 반드시 입력하여야합니다...</div>
          </div>
          <div class="row mb-2">
            <p class="p_box col-1">배송지 입력</p>
            <input type="text" class="form-control col-4 rounded-1 border-1 ps-2 long" name="deliAddress" placeholder="주소를 입력하세요" required>
            <div class="invalid-feedback col"> 주소는 반드시 입력하여야합니다...</div>
          </div>
          <div class="row mb-2">
            <p class="p_box col-1">배송 요청사항</p>
            <input type="text" class="form-control col-4 rounded-1 border-1 ps-2 long" name="deliReq" value="요청 사항 없음" placeholder="배송 요청사항을 입력하세요">
          </div>
        </div>
      </div>
      <!-- 배송지 정보 끝 -->
      <!-- 결제수단 시작 -->
      <div class="payment-info mb-5">
        <div class="payment-info-header">
          <h2 class="pb-1">결제수단</h2>
        </div>
        <div class="row p-2">
          <p class="form-check-label col-md-1 d-inline">결제방법</p>
          <div class="form-check mx-3 col-sm-1 col-md-2 my-auto">
            <input class="form-check-input" type="radio" name="payment" id="flexRadioDefault1" value="credit" required>
            <label class="form-check-label" for="flexRadioDefault1">
              카드
            </label>
          </div>
          <div class="form-check mx-3 col-sm-1 col-md-2 my-auto">
            <input class="form-check-input" type="radio" name="payment" id="flexRadioDefault2" value="without_passbook" required>
            <label class="form-check-label" for="flexRadioDefault2">
              무통장입금
            </label>
          </div>
          <div class="form-check mx-3 col-sm-1 col-md-2 my-auto">
            <input class="form-check-input" type="radio" name="payment" id="flexRadioDefault3" value="apple" required>
            <label class="form-check-label" for="flexRadioDefault3">
              애플페이
            </label>
          </div>
          <div class="form-check mx-3 col-sm-1 col-md-2 my-auto">
            <input class="form-check-input" type="radio" name="payment" id="flexRadioDefault4" value="kakao" required>
            <label class="form-check-label" for="flexRadioDefault4">
              카카오페이
            </label>
          </div>
          <div class="invalid-feedback col">결제방법을 선택해주세요...</div>
        </div>
        <div class="d-flex p-2">
          <p class="total">최종 결제 금액</p>
          <p class="fs-4 text-danger"><fmt:formatNumber value="${totalPrice}" pattern="#,###" />원</p>
        </div>
      </div>
      <!-- 결제수단 끝 -->
      <div class="d-flex justify-content-center">
        <button id="order" type="submit" class="btn btn-light border">결제하기</button>
      </div>
    </form>
  </section>
  <!-- Section 끝 -->
  <!-- Footer -->
  <jsp:include page="/WEB-INF/views/modules/footer.jsp"/>
</body>

</html>
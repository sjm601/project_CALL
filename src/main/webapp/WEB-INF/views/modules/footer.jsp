<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Footer 시작 -->
  <footer class="p-5">
    <div class="row mb-5">
      <div class='col-6'>
        <div class='row'>
          <ul class="fs-6 me-5 col-md-2">
            <h5 class="fs-5 mb-4">Product</h5>
            <li><a href="${path}/product/sunglasses?sg">SUNGLASSES</a></li>
            <li><a href="${path}/product/glasses?ng">GLASSES</a></li>
          </ul>
          <c:if test="${not empty loginMember}">
          <ul class="fs-6 me-5 col-md-2">
            <h5 class="fs-5 mb-4">Member</h5>
            <li><a href="${path}/order/orderconfirm">ORDER LIST</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
          </ul>
          </c:if>
          <ul class="fs-6 me-5 col-md-2">
            <h5 class="fs-5 mb-4">Service</h5>
            <li><a href="${path}/order/cart">CART</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
            <li><a href="#">Lorem</a></li>
          </ul>
        </div>
      </div>
      <div class="col-sm-2 col-md-6">
        <div class="d-flex justify-content-end">
          <ul class="d-flex">
            <li class="me-2"><a href="https://www.facebook.com/" target="_blank"><i
                  class="fa-brands fa-facebook fa-2xl"></i></a></li>
            <li class="mx-2"><a href="https://www.instagram.com/" target="_blank"><i
                  class="fa-brands fa-instagram fa-2xl"></i></a></li>
            <li class="mx-2"><a href="https://www.youtube.com/" target="_blank"><i
                  class="fa-brands fa-youtube fa-2xl"></i></a></li>
            <li class="ms-2"><a href="https://twitter.com/" target="_blank"><i
                  class="fa-brands fa-twitter fa-2xl"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
    <div>
      <p class="fs-6">2023. C-all Glasses</p>
      <p class="address fs-6">(주) 주식회사 / 대표자명 : 홍길동 / 사업자 번호 : 111-22-33333 / 통신판매신고번호 : 제 2023-서울노원-1111호 / 이메일 문의 :
        <span><a href="#">c-all@call.com</a></span> / 주소 : 서울시 노원구 홍길동길 1
      </p>
    </div>
  </footer>
  <!-- Footer 끝 -->
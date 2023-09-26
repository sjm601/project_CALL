<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="module" defer src="/js/index.js"></script>
<!-- Header 시작 -->
<header class="m-auto pt-4 pb-1 d-flex top-0 border-bottom border-black">
	<div class="container">
		<div class="mb-4">
			<a href="/mall/index">
				<h1 class="text-center">C-all Glasses</h1>
			</a>
		</div>
		<div class="d-flex justify-content-center position-relative mb-3">
			<div class="align-self-center">
				<ul class="d-flex ps-0 mb-0">
					<li class="mx-4"><a class="p-2"
						href="${path}/product/sunglasses?sg">선글라스</a></li>
					<li class="mx-4"><a class="p-2"
						href="${path}/product/glasses?ng">안경</a></li>
					<li class="mx-4"><a class="p-2" href="${path}/store/store">매장</a></li>
					<li class="mx-4"><a class="p-2" href="#">수리서비스</a></li>
				</ul>
			</div>
			<div class="align-self-center position-absolute end-0">
				<ul class="d-flex mb-0 ps-0">
					<li class="mx-3">
						<button class="btn p-0" type="button" id="dropdownMenuButton1"
							data-bs-toggle="dropdown" aria-expanded="false">
							<c:choose>
								<c:when test="${empty loginMember}">
									<span class="material-symbols-outlined">person</span>
								</c:when>
								<c:otherwise>
									<span class="material-symbols-outlined"> account_circle
									</span>
								</c:otherwise>
							</c:choose>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							<c:choose>
								<c:when test="${empty loginMember}">
									<li><a class="dropdown-item"
										href="${path}/member/signin?login">로그인</a></li>
									<li><a class="dropdown-item" href="${path}/member/signup">회원가입</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="dropdown-item"
										href="${path}/member/signin?logout">로그아웃</a></li>
									<li><a class="dropdown-item"
										href="${path}/order/orderconfirm">주문내역</a></li>
									<li><button class="dropdown-item quitMember">탈퇴하기</button></li>
									<c:if test="${loginMember.memberHistory.administrator == 'T'}">
										<li><a class="dropdown-item"
											href="${path}/member/memberpage">회원관리</a></li>
									</c:if>
								</c:otherwise>
							</c:choose>
						</ul>
					</li>
					<li class="mx-3"><a href="${path}/order/cart"><span
							class="material-symbols-outlined">shopping_cart</span></a></li>
				</ul>
			</div>
		</div>
	</div>
</header>
<!-- Header 끝 -->
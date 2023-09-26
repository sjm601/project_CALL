<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="/css/login.css" />
<script defer src="/js/signin.js"></script>
<title>Login</title>
</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/header.jsp" />

    <!-- Section 시작 -->
    <section class="container m-auto mt-3">
        <div class="text-center mb-3 py-3">
            <h2>로그인</h2>
        </div>
        <form action="${path}/member/signin" method="post"
            class="d-flex flex-column">
            <div class="justify-content-center m-auto">
                <div class="position-relative">
                    <span class="material-symbols-outlined position-absolute">person</span>
                    <input type="text" id="userEmail" class="mb-3 rounded-2 border-1"
                        name="email" placeholder="아이디" />
                </div>
                <div class="position-relative">
                    <span class="material-symbols-outlined position-absolute">lock</span>
                    <input type="password" id="userPassword"
                        class="mb-3 rounded-2 border-1" name="password" placeholder="비밀번호" />
                </div>
                <c:if test="${not empty loginError}">
                    <div class="alert alert-secondary">${loginError}</div>
                </c:if>
                <div class="form-check m-auto d-flex mb-4">
                    <input class="form-check-input align-self-center mt-0 me-2"
                        type="checkbox" name="saveEmail" id="saveEmail"> <label
                        class="form-check-label" for="saveEmail"> 아이디 저장 </label>
                </div>
                <div>
                    <button class="btn btn-light w-100 border mb-4">로그인</button>
                </div>
                <!-- <div>
                    <button class="btn btn-warning w-100 mb-2" disabled>카카오 로그인</button>
                </div> -->
                <div>
                    <div class="line d-flex mb-4">
                        <span>회원이 아니시면</span>
                    </div>
                </div>
                <div>
                    <a href="${path}/member/signup" class="btn btn-light w-100 border mb-4">회원가입</a>
                </div>
            </div>
        </form>
    </section>
    <!-- Section 끝 -->
</body>

</html>
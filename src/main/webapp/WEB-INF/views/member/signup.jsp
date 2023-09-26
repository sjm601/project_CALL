<%@ page contentType="text/html; charset=utf-8"%>

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
<link rel="stylesheet" href="/css/signup.css" />
<script defer src="/js/signup.js"></script>
<title>Signup</title>

</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/header.jsp" />

    <!-- Section 시작 -->
    <section class="mt-3 d-flex justify-content-center align-items-center">
        <div class="container py-3">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center" href="index.do">
                    <h3 class="text-dark mb-3">회원가입</h3>
                </a>
            </div>
            <form class="p-2 m-auto needs-validation" novalidate action ="${path}/member/signup" method="post">
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">person</span>
                    <input type="text" class="form-control border border-secondary"
                        id="name" name="member_name"  placeholder="이름" required >
                    <div class="invalid-feedback">이름을 입력하여주세요...</div>
                </div>
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">mail</span>
                    <input type="text" class="form-control border border-secondary"
                        id="email" name="email" placeholder="이메일" required>
                    <div class="invalid-feedback">이메일을 입력하여주세요...</div>
                </div>
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">Lock</span>
                    <input type="password" class="form-control border border-secondary"
                        id="passwd" name="password" placeholder="비밀번호" required>
                    <div class="invalid-feedback">비밀번호를 입력하여주세요...</div>
                </div>
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">Lock</span>
                    <input type="password" class="form-control border border-secondary"
                        id="repasswd" placeholder="비밀번호확인" required>
                    <div class="invalid-feedback">비밀번호가 맞지 않습니다...</div>
                </div>
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">smartphone</span>
                    <input type="number" class="form-control border border-secondary"
                        id="phoneNum" name="phone_number" placeholder="전화번호" required>
                    <div class="invalid-feedback">전화번호를 입력하여주세요...</div>
                </div>
                <div class="form-group position-relative">
                    <span class="material-symbols-outlined position-absolute">cake</span>
                    <input type="number" class="form-control border border-secondary"
                        id="birthday" name="birthday" placeholder="생년월일 8자리" required>
                    <div class="invalid-feedback">이름을 입력하여주세요...</div>
                </div>
                <div class="input-group position-relative mb-4">
                    <input type="radio" class="btn-check" name="gender" value="M" id="option1" autocomplete="off" required>
                    <label class="btn btn-outline-primary" for="option1">남자</label>
                    <input type="radio" class="btn-check" name="gender" value="F" id="option2" autocomplete="off" required>
                    <label class="btn btn-outline-primary" for="option2">여자</label>
                    <div class="invalid-feedback">성별을 선택하여 주세요...</div>
                </div>
                <div class="form-check">
                    <input class="form-check-input all" type="checkbox" name="agree"
                        id="exampleRadios1" value="option1" required> <label
                        class="form-check-label" for="exampleRadios1"> 약관 전체 동의하기
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input check-item" type="checkbox"
                        name="agree" id="exampleRadios2" value="option2" required>
                    <label class="form-check-label" for="exampleRadios2"> [필수]
                        만 14세 이상입니다. </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input check-item" type="checkbox"
                        name="agree" id="exampleRadios3" value="option3" required>
                    <label class="form-check-label" for="exampleRadios3"> [필수]
                        이젠몰 이용약관 </label>
                </div>
                <div class="form-check mb-4">
                    <input class="form-check-input check-item" type="checkbox"
                        name="agree" id="exampleRadios4" value="option4" required>
                    <label class="form-check-label" for="exampleRadios4"> [필수]
                        개인정보 수집 및 이용 동의 </label>
                </div>
                <div>
                    <button id="signupBtn" type="submit"
                        class="btn btn-light btn-outline-secondary">회원가입</button>
                </div>
            </form>
        </div>
    </section>
    <!-- Section 끝 -->
</body>

</html>
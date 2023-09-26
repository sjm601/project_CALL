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
<link rel="stylesheet" href="/css/index.css" />
<link rel="stylesheet" href="/css/memberpage.css" />
<title>Memberpage</title>
</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/modules/header.jsp" />

    <!-- Main 시작 -->
    <section class="container">
        <!--section 제목-->
        <div class="text-center mb-3 py-3">
            <h2>회원관리</h2>
        </div>
        <!--table 시작-->
        <table class="table table-hover text-center w-75 m-auto mb-5">
            <thead class="table-dark">
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">이름</th>
                    <th scope="col">이메일</th>
                    <th scope="col">가입날짜</th>
                    <th scope="col">상태</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${memberList}" var="memberList" varStatus="status">
                    <tr>
                        <th scope="row">${memberList.member.memberNum}</th>
                        <td><a href="#">${memberList.member.memberName}</a></td>
                        <td>${memberList.member.email}</td>
                        <!--추후에 회원 구매내역페이지로 이동작업-->

                        <td>${memberList.memberHistory.memberRegisterDate}</td>
                        <c:choose>
                        <c:when test="${memberList.memberHistory.administrator == 'T'}">
                        <td>관리자</td>
                        </c:when>
                        <c:when test="${memberList.memberHistory.status == 'T'}">
                        <td>회원</td>
                        </c:when>
                        <c:when test="${memberList.memberHistory.status == 'F'}">
                        <td>탈퇴회원</td>
                        </c:when>
                        </c:choose>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--table 끝-->
        <!-- pagination 페이징 처리 시작-->
        <!-- 페이징 처리 -->
        <nav class="mt-3">
            <ul class="pagination justify-content-center">
                <%-- 처음으로 보여주기 여부 --%>
                <c:if test="${pagination.showFirst}">
                    <li class="page-item"><a class="page-link"
                        href="?page=1" aria-label="First"> <span
                            aria-hidden="true">처음으로</span>
                    </a></li>
                </c:if>

                <%-- 이전 목록 보여주기 여부 --%>
                <c:if test="${pagination.showPrevious}">
                    <li class="page-item"><a class="page-link"
                        href="?page=${pagination.previousStartPage}"
                        aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                    </a></li>
                </c:if>

                <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
                    <c:choose>
                        <c:when test="${i == pagination.params.requestPage}">
                            <li class="page-item active"><a class="page-link">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                href="?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <%-- 다음 목록 보여주기 여부 --%>
                <c:if test="${pagination.showNext}">
                    <li class="page-item"><a class="page-link"
                        href="?page=${pagination.nextStartPage}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                    </a></li>
                </c:if>

                <%-- 마지막으로 보여주기 여부 --%>
                <c:if test="${pagination.showLast}">
                    <li class="page-item"><a class="page-link"
                        href="?page=${pagination.totalPages}" aria-label="First">
                            <span aria-hidden="true">마지막으로</span>
                    </a></li>
                </c:if>
            </ul>
        </nav>
        <!-- pagination 페이징 처리 끝->
      <!--시간이 난다면, 회원 검색 창 추가-->
    </section>
    <!-- Main 끝 -->
    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/modules/footer.jsp" />
</body>

</html>
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
<link rel="stylesheet" href="/css/cart.css" />
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a144765d3b5f4c381b5a956bd2a4365e"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a144765d3b5f4c381b5a956bd2a4365e&libraries=clusterer"></script>
<title>Store</title>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/modules/header.jsp" />

	<!-- map 시작-->
	<div class="container">
		<section class="map">
			<h1 class="m-auto mb-5 mt-5 text-center">매장 위치</h1>
			<div class="m-auto mb-5 mt-5" id="map" style="width: 700px; height: 700px;"></div>
			<script>
      			const container = document.getElementById('map');
        		const options = { center: new kakao.maps.LatLng(37.6557431, 127.0623944), level: 3 };
				<!--맵 생성-->
        		const map = new kakao.maps.Map(container, options);
				<!--마커 위치-->
        		const markerPosition  = new kakao.maps.LatLng(37.6557431, 127.0623944);
				<!--마커 생성-->
        		const marker = new kakao.maps.Marker({ position: markerPosition });
				<!--마커 표시-->
        		marker.setMap(map);
        
        		<!--마커 클릭이벤트-->
        		const iwContent = '<div style="padding:5px;">mall</div>',
        		// 인포윈도우에 표출될 내용
        		iwRemoveable = true; 
        		// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
        
 				// 인포윈도우를 생성
        		const infowindow = new kakao.maps.InfoWindow({ content : iwContent, removable : iwRemoveable });

				// 마커 클릭이벤트 등록
        		kakao.maps.event.addListener(marker, 'click', function() {
				// 마커 위에 인포윈도우를 표시
        		infowindow.open(map, marker);  
				});
    		</script>
		</section>
	</div>
	<!-- map 끝 -->
	<jsp:include page="/WEB-INF/views/modules/footer.jsp" />
</body>

</html>
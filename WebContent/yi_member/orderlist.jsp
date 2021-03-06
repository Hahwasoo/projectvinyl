<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> 
<!-- jQuery -->
<!-- viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, shrink-to-fit=no">
<!-- viewport / shrink-to-fit=no 사파리 브라우저에 영향을 미치는 속성 -->
<!-- Bootstrap -->
<link rel="stylesheet" href="css/yi_adminboard.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<!-- Bootstrap -->
</head>
<body>
<div class="container">
<header>
         <jsp:include page="/hs_communityheader.jsp"></jsp:include>
     </header>

<h4 class="yi_mypagelogo">마이페이지</h4>
   
<c:set var="list" value="${requestScope.list}"></c:set>
<c:set var="mlist" value="${requestScope.mlist }"></c:set>


<div class="accordion" id="accordion1">
  <div class="card">
    <div class="card-header" id="orderlist">
      <h2 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          주문내역
        </button>
      </h2>
    </div>
    
    <div id="collapseOne" class="collapse" aria-labelledby="orderlist" data-parent="#accordion1">
      <div class="card-body">
        
    <h5 class="yi_accordion-inside-title">주문 내역</h5>
<div class="yi_orderguide">
<ul>
<br>
<li>상세조회 버튼을 클릭하시면 주문/입금/배송정보 등 세부내역을 확인하실 수 있습니다.</li>
<li>포인트를 사용하여 주문하셨다가 재주문을 원하신다면 해당 주문을 취소하시면 포인트는 다시 반환됩니다.</li>
<li>주문취소 방법: 리스트의 상세정보를 클릭하시고 들어가신후 하단의 주문취소 버튼을 클릭하시면 됩니다.</li>
<li>결제,배송 이후 취소/반품 요청은 관리자에게 연락주세요.</li>
<br>
</ul>
  </div>
<table class="table table-hover">
<thead class="thead-dark">
<tr><th>주문일</th><th>주문번호</th><th>주문상세</th><th>배송현황</th>
</tr>
</thead>
<tbody>

<c:forEach var="item" items="${list }" varStatus="stat">
<tr>

<c:choose> 
<c:when test="${list[stat.index].status == 0 }">
<c:set var="orderst" value="주문취소"></c:set>
</c:when>
<c:when test="${list[stat.index].status== 1 }">
<c:set var="orderst" value="결제확인"></c:set>
</c:when>
<c:when test="${list[stat.index].status == 2 }">
<c:set var="orderst" value="배송준비중"></c:set>
</c:when>
<c:when test="${list[stat.index].status == 3 }">
<c:set var="orderst" value="배송중"></c:set>
</c:when>
<c:when test="${list[stat.index].status == 4 }">
<c:set var="orderst" value="배송완료"></c:set>
</c:when>
</c:choose>


<td><c:out value="${item.orderdate }"></c:out></td>
<td><c:out value="${item.order_no }"></c:out></td>
<td><a href="yiorderdetail.do?num=${item.order_no }" class="btn btn-outline-primary">상세보기</a></td>

<td><button type="button" class="btn btn-secondary btn-lg" disabled><c:out value="${orderst }"></c:out></button></td>
</tr>
</c:forEach>

</tbody>
</table>    
        
        
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="memberInfo">
      <h2 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
         회원정보
        </button>
      </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="memberInfo" data-parent="#accordion1">
      <div class="card-body">
       
       <h5 class="yi_accordion-inside-title">회원 정보</h5>
<table class="table table-hover">
<tr><th>아이디</th>
<td><c:out value="${mlist[0].id }"></c:out></td></tr>
<tr><th>이름</th>
<td><c:out value="${mlist[0].name }"></c:out></td></tr>
<tr><th>생년월일</th>
<td><c:out value="${mlist[0].birth }"></c:out></td></tr>
<tr><th>전화번호</th>
<td><c:out value="${mlist[0].phone }"></c:out></td></tr>
<tr><th>주소</th>
<td><c:out value="${mlist[0].addr }"></c:out></td></tr>
</table>
<div class="yi_modifybtn">
<a href="yimodify.do" class="btn btn-outline-primary">회원정보 수정</a><br>
    </div>   
       
      </div>
    </div>
  </div>
  
</div>









<footer>
         <jsp:include page="/hs_user_footer.jsp"></jsp:include>
     </footer>
</div>
</body>
</html>
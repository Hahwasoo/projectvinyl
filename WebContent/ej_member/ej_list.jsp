<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
#ej_searchbox{border:1px solid silver;
width: 60%;
padding: 20px;}
#ej_line{border:0.5px solid silver;}
</style>



<script>
function delconfirm(){

	var jbResult = confirm( '해당 회원을 삭제 하시겠습니까?' );
	//document.write( jbResult );	
}
</script>


<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원조회</h3>
<!--setAttribute로 지정한것 변수로 받아오기  -->
<c:set var="list" value="${requestScope.list }"/>
<c:set var="currpage" value="${requestScope.currpage }"/>
<c:set var="startblock" value="${requestScope.startblock }"/>
<c:set var="endblock" value="${requestScope.endblock }"/>
<c:set var="totalpage" value="${requestScope.totalpage }"/>
<c:set var="search" value="${requestScope.search }"></c:set>
<c:set var="txtsearch" value="${requestScope.txtsearch }"></c:set> 

<form method="post" action="ej_list.do"><!--처음에 list.do햇음  -->
<div id="ej_searchbox">
<select name="search"><!--search txtsearch받아야댐 ListAction에서  -->
	<option value="id">아이디</option>
	<option value="name">이름</option>
</select>
<input type="text" name="txtsearch">
<!-- <input type="submit" value="검색"> -->

<div id="ej_line"></div>
<label for="point">구매액</label>
<input type="text" name="stxtsearch1">원~<input type="text" name="stxtsearch2">원<br>
<input type="submit" value="상세검색"> 
</div>



<table>
<thead><tr><th>선택</th><th>번호</th><th>아이디</th><th>비밀번호</th><th>이름</th><th>구매액</th><th>적립금</th>
<th>                  <th><th>삭제</th></tr>
</thead>
<tbody>
	<!--arraylist니까 foreach  -->
	<c:forEach var="item" items="${list}">
	<tr>
 	<td><input type="checkbox" name="select" id="select" value="${item.memNo }" ></td>
	<td><c:out value="${item.memNo }"></c:out></td> 
	<td>${item.id }</td>
	<td>${item.pwd }</td>
	<td>${item.name }</td>
	<td>${item.total }원</td>
	<td><a href="ej_insert.do?memNo=${item.memNo}" target="_blank"  >${item.point }포인트</a><td> 
<!--target="_blank"  -->
	<td><a href="ej_delete.do?memNo=${item.memNo}" onclick="delconfirm();">삭제</a><td>


	</tr>
	 </c:forEach>
</tbody>
</table><br>
<c:forEach var="i" begin="${starblock }+1" end="${endblock }" step="1">
	<c:if test="${currpage==i }">
	<c:out value="${i }"/>
	
	</c:if>

	<c:if test="${currpage!=i }">
	<a href="ej_list.do?curr=${i }&search=${search}&txtsearch=${txtsearch }">${i }</a>
	</c:if>
</c:forEach><br>

<%-- <input type="hidden" name="memno" value="${item.memNo }"> --%>





</form>

</body>
</html>
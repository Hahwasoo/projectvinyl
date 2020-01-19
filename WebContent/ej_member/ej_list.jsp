<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
#searchbox{border:1px solid silver;
width: 60%;
padding: 20px;}
#line{border:0.5px solid silver;}
</style>

<script>

/*  $(document).ready(function () {
	//let values=[];//체크된 번호를 받는 배열
	var h=new Array();
$("input[name='select']").click(function(){
	$("input[name='select']:checked").each(function(i)
			{
			
			let h=($("input[name='select']:checked").val());
			console.log(h);

				});
	
});
	 $('#delete').on('click',function(){
	
	$.ajax({
		url:"delete.do",
		type:"get",
		data: h,
		success:function(data){
			alert("삭제완료!");
		},
		error:function(data){
			alert("에러 발생 ");
			
		}
	});
	
	
});

}); */

function send(){/* document.form이름.submit() */
	if(document.frm.title.value!="")
		document.frm.submit();
}//send()

function del(mem_no){
	console.log(sno);
	console.log(bno);
	location.href="subdelete.do?subno="+sno+"&boardno="+bno;
}
$(document).ready(function(){
	let no=${dto.boardno};
	console.log("no:",no);
	$.ajax({
		url:'subdetail.do'
		,data:{'num':no}
		,dataType: 'json'//json으로 하면 ~.do로 자료 사용하는건맞음 SubDetailAction implements 하면안됨
		//class로 하면 자료 받을 수 없다. class가 아닌 servlet만들면됨 객체만들필요없다
		//ublic class SubDetailAction /*implements Action */{//파라미터 받아줘야함

	//@Override
	//public ForwardAction execute(HttpServletRequest request, HttpServletResponse response)
			//throws ServletException, IOException {
		//json타입으로 받아옴 요청받고 응답에 대한 자료 처리;
		//out객체 만들어서 자료들을 처리 굳이 controll탈필요 없 
		//return 어쩌고.jsp할필요없
		//implements Action 할 필요 없음
		//properties에 안쓴다
		
	

		,method:'post'//post방식으로 하면 숨겨서 넘김
		,success:function(data)
		{
			$.each(data,function(index,item){
				console.log(index);
				console.log(item);
				console.log(item.subno);
				//let result="<tr><td>"+item.subno+"</td>";//따로 넣어야됨 . 이건 시퀀스인듯
				let result="<tr>";
				result+="<td>"+item.subtitle+"</td>";
				result+="<td>"+item.writer;
				result+="<input type='button' value='삭제' onclick=del("+item.subno+","+item.boardno+")>";
				//result+="<td>"+item.boardno+"</td>";//1번글에 대한 댓글임을 보여줌. 따라서 출력할 필요없음
				result+="</td></tr>";
				$('#result').append(result);
				});
			
		}
		,error:function(data)
		{
			console.log('error',data);
		}
	});
	
});
</script>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<% %>
<!--setAttribute로 지정한것 변수로 받아오기  -->
<c:set var="list" value="${requestScope.list }"/>
<c:set var="currpage" value="${requestScope.currpage }"/>
<c:set var="startblock" value="${requestScope.startblock }"/>
<c:set var="endblock" value="${requestScope.endblock }"/>
<c:set var="totalpage" value="${requestScope.totalpage }"/>
<c:set var="search" value="${requestScope.search }"></c:set>
<c:set var="txtsearch" value="${requestScope.txtsearch }"></c:set> 

<form method="post" action="list.do"><!--처음에 list.do햇음  -->
<div id="searchbox">
<select name="search"><!--search txtsearch받아야댐 ListAction에서  -->
	<option value="id">아이디</option>
	<option value="name">이름</option>
</select>
<input type="text" name="txtsearch">
<input type="submit" value="검색">

<div id="line"></div>
<label for="point">구매액</label>
<input type="text" name="stxtsearch1">원~<input type="text" name="stxtsearch2">원<br>
<input type="submit" value="상세검색"> 
</div>



<table>
<thead><tr><th>선택</th><th>번호</th><th>아이디</th><th>비밀번호</th><th>이름</th><th>구매액</th><th>적립금</th><th>삭제</th></tr>
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

	<td>${item.total }</td>
		<td>${item.point }<input type="text" name="addpoint" id="addpoint">
		<input type="button" id="point" value="적립금" >
		</td>
	<td><input type="button" id="delete" value="삭제" ><td>


	</tr>
	 </c:forEach>
</tbody>
</table><br>
<c:forEach var="i" begin="${starblock }+1" end="${endblock }" step="1">
	<c:if test="${currpage==i }">
	<c:out value="${i }"/>
	
	</c:if>

	<c:if test="${currpage!=i }">
	<a href="list.do?curr=${i }&search=${search}&txtsearch=${txtsearch }">${i }</a>
	</c:if>
</c:forEach><br>

<%-- <input type="hidden" name="memno" value="${item.memNo }"> --%>





</form>

</body>
</html>
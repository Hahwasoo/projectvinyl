<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>



<form method = "post" action="hm_uploadresult.do" enctype="multipart/form-data"><!-- ���ε��Ҷ� enctype �� �������  -->
	<ul>
	<li>
	<label for ="name">��ȣ��</label>
	<input type ="text" name = "name" id ="name">
	</li>
	<li>
	<label for ="content">����</label>
	<input type ="text" name = "content" id ="content">
	</li>
	<li>
	<label for ="place">��ġ</label>
	<input type ="text" name = "place" id ="place">
	</li>
	<li>
	<label for ="lat">����</label>
	<input type =text name = "lat" id ="lat">
	</li>
	<li>
	<label for ="lon">�浵</label>
	<input type ="text" name = "lon" id ="lon">
	</li>
	<li>
	<label for ="file_name">�����̸�</label>
	<input type ="text" name = "file_name" id ="file_name">
	</li> 
	<li>
	<label for ="uploadfile">���ϰ��</label>
	<input type ="file" name = "uploadfile" id ="uploadfile">
	</li>
	<li>
	<input type ="submit" value ="����">
	<input type ="reset" value ="���">
	</li>
	</ul>
	</form>
</body>
</html>
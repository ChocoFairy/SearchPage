<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>普通用户登陆</title>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<a href="index.html">返回</a>
	<form action="${pageContext.request.contextPath }/testServlet">
		<input type="file" id="test" accept="image/png, image/jpeg" name="filePath"/>
		<button type="submit" onclick="upload()">上传照片</button>
	</form>
	<script>	
		function upload(){
			alert("上传成功！");
		}
    </script>
	<div style="width: 80%; text-align: center;margin-left: 8%;">
		<h3>个人信息显示</h3>
		<table id="info_table" class="table table-bordered">
            <thead>
            	<tr>
	                <th >学号</th>
	                <th >姓名</th>
	                <th >电话</th>
	                <th >qq</th>
	                <th >邮箱</th>
	            </tr> 
            </thead>
            <tbody>
            	<tr>
            		<td>${currentUser.id }</td>
            		<td>${currentUser.name }</td>
            		<td>${currentUser.phone }</td>
            		<td>${currentUser.qq }</td>
            		<td>${currentUser.email }</td>
            	</tr>
            	
            	
            </tbody>
        </table>
	</div>
</body>
</html>
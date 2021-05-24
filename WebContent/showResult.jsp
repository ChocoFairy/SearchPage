<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="pageBean" class="javaBean.PageBean" scope="session"/>
<jsp:setProperty name="pageBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>显示结果</title>
<link rel="stylesheet" href="css/page.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="http://img.htmlsucai.com/cdn/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/page.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<%! private static final int MAX_COUNT = 5; %>
	<% 
		int pageNum = 0;
		String parameter = request.getParameter("currentPageNum");
		if(parameter==null){
			pageNum = 0;
		}else{
			pageNum = Integer.parseInt(parameter);
		}
		request.getSession().setAttribute("page_num", pageNum);
		pageBean.setNumInPage(5);
	%>
	<!-- 不分页显示 -->
	<c:if test="<%=pageBean.getTotalNum()<=pageBean.getNumInPage() %>" var="flag" scope="session">
		<% pageBean.showInfo(); %>
		<p style="margin: 10px; font-size: 18px"><b>不分页显示，当前页面显示记录数：<%=pageBean.getTotalNum() %></b></p>
		<div style="margin: 20px; padding: 10px; width: 80%;border: solid darkgray;">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>姓名</th>
						<th>联系电话</th>
						<th>QQ</th>
						<th>电子邮箱</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach begin="0" end="<%=pageBean.getTotalNum()-1 %>" var="i">
					<tr>
						<td>${Persons[i].id }</td>
						<td>${Persons[i].name }</td>
						<td>${Persons[i].phone }</td>
						<td>${Persons[i].qq }</td>
						<td>${Persons[i].email }</td>
					</tr>
				</c:forEach>
				</tbody> 
			</table>
		</div>
	</c:if>
	<!-- 分页显示 -->
	<c:if test="${not flag }">
		<% 
			pageBean.setCurrentPageNum((int)request.getSession().getAttribute("page_num"));
			pageBean.showInfo();
			pageBean.pageStrategy();
		%>
		<p style="margin: 10px; font-size: 18px"><b>分页显示，当前页面显示记录数：<%=pageBean.getCurrentNum() %></b></p>
		<div style="margin: 20px; padding: 10px; height:550px; width: 80%;border: solid darkgray;">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>姓名</th>
						<th>联系电话</th>
						<th>QQ</th>
						<th>电子邮箱</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach begin="<%=pageBean.getStart() %>" end="<%=pageBean.getEnd() %>" var="i">
					<tr>
						<td>${Persons[i].id }</td>
						<td>${Persons[i].name }</td>
						<td>${Persons[i].phone }</td>
						<td>${Persons[i].qq }</td>
						<td>${Persons[i].email }</td>
					</tr>
				</c:forEach>
				</tbody> 
			</table>
			<div style="position: fixed; bottom: 320px; right: 400px;">
				<ul class="page" maxshowpageitem="5" pagelistcount="<%=pageBean.getNumInPage() %>"  id="page"></ul>
				<form style="float:right" action="${pageContext.request.contextPath }/showResult.jsp?data=${data}&currentPageNum=0" >
				  	<table  cellspacing="1" style="margin=10px">
				  		<tr>
				  			<td><p style="font-size: 18px; margin-top: 5px">每页显示的记录数：</p></td>
				  			<td><input type="number" class="form-control" name="numInPage" value="<%=pageBean.getNumInPage() %>" min="1" max="7" /></td>
				  			<td><button type="submit" class="btn btn-default" >修改</button></td>
				  		</tr>
				  	</table>
	  			</form>
			</div>
		</div>
	</c:if>
</body>
</html>
<script type="text/javascript">
    function forward(page){
    	// page是下一次要跳转的页（从1开始）
        alert(page);
        window.location.href="${pageContext.request.contextPath }/showResult.jsp?data=${Data}&currentPageNum="+(page-1);
    }

    $("#page").initPage(${Num},${page_num+1},forward);
</script>
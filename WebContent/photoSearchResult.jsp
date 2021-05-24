<%@page import="javaBean.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>照片搜索结果</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="http://img.htmlsucai.com/cdn/jquery/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<%
		Person p = (Person)request.getSession().getAttribute("photoSearchPerson");
		String imgUrl = "http://localhost:8080/SearchPage/img/"+p.getId()+".jpg";
		request.getSession().setAttribute("imgUrl", imgUrl);
	%>
	<span>
		<a href="infoManager.jsp">返回</a>
		<b>人脸搜索结果</b>
	</span>
	
	<div style="margin: auto; width: 90%; padding-top: 20px;">
        <table id="info_table" class="table table-hover table-bordered">
            <thead>
            	<tr>
	                <th>学号</th>
	                <th>姓名</th>
	                <th>电话</th>
	                <th>qq</th>
	                <th>邮箱</th>
	                <th>照片</th>
	                <th>操作</th>
	            </tr> 
            </thead>
            <tbody>
           		<tr>
            		<td>${photoSearchPerson.id }</td>
            		<td>${photoSearchPerson.name }</td>
            		<td>${photoSearchPerson.phone }</td>
            		<td>${photoSearchPerson.qq }</td>
            		<td>${photoSearchPerson.email }</td>
            		<td><img style="height: 100px;width: 80px;" alt="图片显示异常" src="${imgUrl}"></td>
            		<td>
            			<button id="modifyBtn" class='btn btn-success' onclick="modify(1)">修改</button>
            			<button id="delBtn" style='margin-left: 10px;' type='button' class='btn btn-danger' onclick="del(${i+1})">删除</button>
            		</td>
            	</tr>
            </tbody>
        </table>
    </div>
    
    <!-- 修改个人信息 -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyMatModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">修改个人信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 提交表单到后台 -->
                    <form role="form" action="${pageContext.request.contextPath }/modifyInfoServlet" method="post">
                        <div class="form-group">
                            <label class="control-label">学号(无法修改)</label>
                            <input id="id" name="id" class="form-control" type="text" readonly="readonly" value="${photoSearchPerson.id }">
                        </div>
                        <div class="form-group">
                            <label class="control-label">姓名</label>
                            <input id="name" name="name" class="form-control" type="text" placeholder="修改后的姓名" value="${photoSearchPerson.name }">
                        </div>
                        <div class="form-group">
                            <label class="control-label">电话</label>
                            <input id="phone" name="phone" class="form-control" type="text" placeholder="修改后的电话" value="${photoSearchPerson.phone }">
                        </div>
                        <div class="form-group">
                            <label class="control-label">qq</label>
                            <input id="qq" name="qq" class="form-control" type="text" placeholder="修改后的qq" value="${photoSearchPerson.qq }">
                        </div>
                        <div class="form-group">
                            <label class="control-label">邮箱</label>
                            <input id="email" name="email" class="form-control" type="text" placeholder="修改后的邮箱" value="${photoSearchPerson.email }">
                        </div>
                        <div class=" modal-footer">
		                    <button type="submit" class="btn btn-success">保存</button>
		                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
		                </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 删除的信息提示 -->
    <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">确认删除</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label class="control-label" id="del_label">确认删除学号为${ photoSearchPerson.id}的信息吗？</label>
                    <label id="dia_mat_del_id" class="control-label" data-visible="false" hidden='true'></label>
                </div>
                <div class="modal-footer">
                   	<input id="del_id" name="del_id" value="" type="hidden">
                   	<button type="submit" class="btn btn-success" data-dismiss="modal" onclick="del(-1)">删除</button>
                   	<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	function modify(){
    		$("#modifyModal").modal("show");
    	}
    	function del(num){
    		if(num==-1){
    			var id = $("#del_id").val();
    			alert(id+",删除成功！");
    			// 跳转到处理的Servlet界面进行删除
    			window.location.href = "${pageContext.request.contextPath }/deleteInfoServlet?del_id="+id;
    		}
    		$("#delModal").modal("show");
    	}
    </script>
</body>
</html>
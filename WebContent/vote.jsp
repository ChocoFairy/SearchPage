<%@page import="dao.GetUserDao"%>
<%@page import="javaBean.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票系统</title>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.12.4.min.js"></script>
<script src="js/echarts.min.js"></script>

<style>
	ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	    border: 1px solid #e7e7e7;
	    background-color: #f3f3f3;
	}
	
	li {
	    float: left;
	}
	
	li a {
	    display: block;
	    color: #666;
	    text-align: center;
	    padding: 10px 12px;
	    text-decoration: none;
	}
	
	li a:hover:not(.active) {
	    background-color: #ddd;
	}
	
	li a.active {
	    color: white;
	    background-color: #be2ac0;
	}
</style>
<script type="text/javascript">
	//定时刷新
	function init(){
		setTimeout("fresh()",5000);
	}
	function fresh(){
		//alert("刷新");
		window.location.href = "${pageContext.request.contextPath}/vote.jsp";
	}
</script>

</head>
<body onload="init()">
	<%
		// 每次刷新时重新拿到数据库中的Teacher数据
		GetUserDao teacherDao = new GetUserDao();
		try{
			List<Teacher> teachers = teacherDao.getTeachers();
			request.getSession().setAttribute("teacherNum", teachers.size());
			request.getSession().setAttribute("teachers", teachers);
		}catch(Exception e){
			e.printStackTrace();
		}
	%>
	<ul style="position:fixed; top:0; width:100%;">
	  <li><a class="active" href="index.html">主页</a></li>
	  <li><a href="#">投票系统</a></li>
	  <li style="float:right; margin: 3px;"><button class="btn btn-default" onclick="signin()">登陆Login</button></li>
	</ul>
	<div style="text-align: center;padding-top: 100px;">
		<div id="echartsPie" style="width: 600px;height:400px;margin: auto;"></div>
		<p style="margin-top: 20px;"><b style="font-size: 16px;">选择喜欢的老师</b>
			<select id="teachers">
				<option value="0">-请选择-</option>
				<c:forEach begin="0" end="${teacherNum-1 }" var="i">
					<option value=${i }>${teachers[i].name }</option>
				</c:forEach>
		    </select>
			<button onclick="submit()">投票</button>
		</p>
	</div>
	
	<script type="text/javascript">
		var echartsPie;
		var json = [
		            {value:${teachers[0].votes},name:'${teachers[0].name}'},
		            {value:${teachers[1].votes},name:'${teachers[1].name}'},
		            {value:${teachers[2].votes},name:'${teachers[2].name}'},
		            {value:${teachers[3].votes},name:'${teachers[3].name}'},
		            {value:${teachers[4].votes},name:'${teachers[4].name}'},
		            {value:${teachers[5].votes},name:'${teachers[5].name}'},
		            {value:${teachers[6].votes},name:'${teachers[6].name}'},
		            {value:${teachers[7].votes},name:'${teachers[7].name}'}
		            ];
		var option = {
			    title : {
			        text: '软院教师喜爱度投票结果',
			        subtext: '独家报道',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} 票"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:['${teachers[0].name}','${teachers[1].name}',
			        	'${teachers[2].name}','${teachers[3].name}',
			        	'${teachers[4].name}','${teachers[5].name}',
			        	'${teachers[6].name}','${teachers[7].name}']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:"教师",
			            type:'pie',
			            radius : '55%',//饼图的半径大小
			            center: ['50%', '60%'],//饼图的位置
			            data:json
			        }
			    ]
			}; 
		
		echartsPie = echarts.init(document.getElementById('echartsPie'));
		$(function(){
			echartsPie.setOption(option);
			
		});	 
		
		function submit(){
			// 获取单选框的值，传递到下一个页面
			var select = document.getElementById("teachers");
			var value = select.selectedIndex;
			window.location.href = "${pageContext.request.contextPath}/voteServlet?select="+value;
		}
	</script>
</body>
</html>
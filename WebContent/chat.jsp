<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>大连理工大学聊天</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" href="assets/i/favicon.ico">
<link rel="stylesheet" href="assets/css/amazeui.min.css">
<link rel="stylesheet" href="assets/css/app.css">
<link href="umeditor/themes/default/css/umeditor.css" rel="stylesheet">
<style>
.title {
	text-align: center;
}
.chat-content-container {
	height: 29rem;
	overflow-y: scroll;
	border: 1px solid silver;
}
</style>
</head>
<body>
	<% 
		String goalId = request.getParameter("goalID");
		String myId = request.getParameter("currID");
		System.out.println("my: "+myId+" goal: "+goalId);
	%>
	<!-- 标题 -->
	<div class="title">
		<div class="am-g am-g-fixed">
			<div class="am-u-sm-12">
				<h1 class="am-text-primary">大工校友聊天</h1>
			</div>
		</div>
	</div>
	<!-- 聊天内容框开始 -->
	<div class="chat-content">
		<div class="am-g am-g-fixed chat-content-container">
			<div class="am-u-sm-12">
				<ul id="message-list" class="am-comments-list am-comments-list-flip">
				</ul>
			</div>
		</div>
	</div>
	<!-- 聊天内容框结束 -->
	<div class="message-input am-margin-top">
		<!-- 输入内容框开始 -->
		<div class="am-g am-g-fixed">
			<div class="am-u-sm-12">
				<form class="am-form">
					<div class="am-form-group">
						<script type="text/plain" id="myEditor" style="width: 100%;height: 8rem;"></script>
					</div>
				</form>
			</div>
		</div>
		<!-- 输入昵称框开始 -->
		<div class="am-g am-g-fixed am-margin-top">
			<div class="am-u-sm-6">
				<div id="message-input-nickname" class="am-input-group am-input-group-primary">
					<span class="am-input-group-label"><i class="am-icon-user"></i></span>
					<input id="nickname" type="text" class="am-form-field" placeholder="Please enter nickname"/>
				</div>
			</div>
			<div class="am-u-sm-6">
				<button id="send" type="button" class="am-btn am-btn-primary">
					<i class="am-icon-send"></i>发送
				</button>
			</div>
		</div>
	</div>

	<script src="assets/js/jquery.min.js"></script>
	<script charset="utf-8" src="umeditor/umeditor.config.js"></script>
	<script charset="utf-8" src="umeditor/umeditor.min.js"></script>
	<script src="umeditor/lang/zh-cn/zh-cn.js"></script>
	
	<script>
	$(function() {
		    //随机方法   生成id模拟用户
		    function rand(num){
		    	return parseInt(Math.random()*1000000+1);
		    }
			// 初始化消息输入框
			var um = UM.getEditor('myEditor');
			// 使昵称框获取焦点
			$('#nickname')[0].focus();
			// 新建WebSocket对象，最后的/WebSocket跟服务器端的@ServerEndpoint("/websocket")对应
			//var socket = new WebSocket('ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket');
			//var socket = new WebSocket("ws://localhost:8080/ChatTest/websocket");
			var target = "ws://"+window.location.host+"/SearchPage/websocket"+"/<%=myId%>/<%=goalId%>";
			//alert(target);
			var socket = new WebSocket(target);
			
			// 处理服务器端发送的数据
			socket.onmessage = function(event) {
				addMessage(event.data);
			};
			
			// 点击Send按钮时的操作
			$('#send').on('click', function() {
				var nickname = $('#nickname').val();
				//alert(um.getContent()); //内容
				//alert(nickname);	//昵称
				if (!um.hasContents()) {	// 判断消息输入框是否为空
					// 消息输入框获取焦点
					um.focus();
					// 添加抖动效果
					$('.edui-container').addClass('am-animation-shake');
					setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
				} else if (nickname == '') {	// 判断昵称框是否为空
					//昵称框获取焦点
					$('#nickname')[0].focus();
					// 添加抖动效果
					$('#message-input-nickname').addClass('am-animation-shake');
					setTimeout("$('#message-input-nickname').removeClass('am-animation-shake')", 1000);
				} else {
					// 发送消息
					socket.send(JSON.stringify({
						content : um.getContent(),
						nickname : nickname
					}));
					// 清空消息输入框
					um.setContent('');
					// 消息输入框获取焦点
					um.focus();
				}
			});

			// 把消息添加到聊天内容中
			function addMessage(message) {
				//alert("添加内容"+message+"_end");  
				message = JSON.parse(message);
				var messageItem = '<li class="am-comment '
						+ (message.isSelf ? 'am-comment-flip' : 'am-comment')
						+ '">'
						+ '<a href="javascript:void(0)" ><img src="assets/images/'
						+ (message.isSelf ? 'self.jpg' : 'others.jpg')
						+ '" alt="" class="am-comment-avatar" width="48" height="48"/></a>'
						+ '<div class="am-comment-main"><header class="am-comment-hd"><div class="am-comment-meta">'
						+ '<a href="javascript:void(0)" class="am-comment-author">'
						+ message.nickname + '</a> <time>' + message.date
						+ '</time></div></header>'
						+ '<div class="am-comment-bd">' + message.content
						+ '</div></div></li>';
				$(messageItem).appendTo('#message-list');
				// 把滚动条滚动到底部
				$(".chat-content-container").scrollTop($(".chat-content-container")[0].scrollHeight);
			}
		});
	</script>
	
</body>
</html>
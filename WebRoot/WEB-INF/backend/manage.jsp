<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>后台管理页面</title>
		<meta charset="utf-8"/>
		<style type="text/css">
		</style>
	</head>
	<!-- frameset组织页面结构 -->
	<frameset rows="13%, 87%" frameborder="0" framespacing="0">
		<frame src="${app}/toTop.action" noresize="noresize"/>
		<frameset cols="14%, 86%" frameborder="0" framespacing="0">
			<frame src="${app}/toLeft.action" noresize="noresize"/>
			<frame src="${app}/toRight.action" name="rightFrame"  noresize="noresize"/>
		</frameset>
	</frameset>
	<body>
	</body>
</html>

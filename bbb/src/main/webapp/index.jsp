<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
</head>
<body>
<jsp:forward page="/emps.action"></jsp:forward>
<%-- <%=request.getContextPath() %> --%>
1.添加spring-webmvc正常<br>
2.spring-jdbc<br>
3.spring-test<br>
4.spring-aspects<br>
5.mybatis<br>
6.mybatis-sprin<br>
7.c3p0<br>
<p>
原因：mysql-connector-java5.1.41jar包有问题，现改5.1.30<br>
其他jar包暂时没问题
</p>
<button >一键变帅</button>
</body>
</html>
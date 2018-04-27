<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能操作</title>

<style>
.table4_1 table {
	width: 100%;
	margin: 25px 0;
	border: 0;
}

.table4_1 th {
	background-color: #93DAFF;
	color: #000000
}

.table4_1, .table4_1 th, .table4_1 td {
	font-size: 1.2em;
	text-align: center;
	padding: 8px;
	border-collapse: collapse;
}

.table4_1 th, .table4_1 td {
	border: 1px solid #c1e9fe;
	border-width: 1px 0 1px 0
}

.table4_1 tr {
	border: 1px solid #c1e9fe;
}

.table4_1 tr:nth-child(odd) {
	background-color: #dbf2fe;
}

.table4_1 tr:nth-child(even) {
	background-color: #fdfdfd;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<center>
		<h2>功能操作</h2>

		<table class=table4_1>
			<tr>
				<td width="20%"><a href="${ctx }/category?operate=show">类别管理</a></td>
			</tr>
			<tr>
				<td><a href="${ctx }/source?operate=show">资源管理</a></td>
			</tr>
			<c:if test="${user.userId  == 0}">
				<tr>
					<td><a href="${ctx }/user">用户管理</a></td>
				</tr>
			</c:if>
		</table>

	</center>
</body>
</html>
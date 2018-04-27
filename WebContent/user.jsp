<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>

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
	padding: 4px;
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
		<h2>
			用户管理 &nbsp;&nbsp;&nbsp;
			<button onclick="back()">返回</button>
		</h2>

		<table class=table4_1>
			<tr>
				<th width="20%">序号</th>
				<th width="20%">用户名称</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach var="userDO" items="${userDOs }" varStatus="status">
				<tr>
					<td width="20%">${status.index + 1}</td>
					<td width="20%">${userDO.userName}</td>
					<td width="20%"><c:if test="${userDO.status == 1 }">
							<a onclick="update(${userDO.userId}, 0)">停用</a>
						</c:if> <c:if test="${userDO.status == 0 }">
							<a onclick="update(${userDO.userId}, 1)"><font color="green">恢复</font></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>

	</center>

	<script type="text/javascript">
		function back() {
			window.location.href = "${ctx}/show.jsp";
		}

		function update(userId, status) {
			if(confirm("确定停用该用户吗？")){
				$.ajax({
					url : "${ctx}/user",
					type : "post",
					data : {
						"operate" : "del",
						"userId" : userId,
						"status" : status
					},
					success : function(data) {
						if (data != null) {
							alert("停用成功")
							window.location.href = "${ctx}/user";
						} else {
							alert("停用失败")
						}
					},
					error : function() {
						alert("服务器当当了...")
					}
				});
			}

		}
		
	</script>
</body>
</html>
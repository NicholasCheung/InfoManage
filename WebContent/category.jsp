<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>类别管理</title>

<style>
.table4_1 table {
	width: 100%;
	margin: 15px 0;
	border: 0;
}

.table4_1 th {
	background-color: #93DAFF;
	color: #000000
}

.table4_1, .table4_1 th, .table4_1 td {
	font-size: 0.95em;
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
			类别管理 &nbsp;&nbsp;&nbsp;
			<button onclick="toAdd()">新增</button>
			<button onclick="back()">返回</button>
		</h2>

		<table class=table4_1>
			<tr>
				<th width="20%">序号</th>
				<th width="20%">类别名称</th>
				<th width="20%">类别描述</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach var="categoryDO" items="${categoryDOs }"
				varStatus="status">
				<tr>
					<td width="20%">${status.index + 1}</td>
					<td width="20%">${categoryDO.categoryName}</td>
					<td width="20%">${categoryDO.categoryDesc}</td>
					<td width="20%"><a onclick="edit(${categoryDO.categoryId})">编辑</a>
						| <a onclick="del(${categoryDO.categoryId})">删除</a></td>
				</tr>
			</c:forEach>
		</table>

	</center>

	<script type="text/javascript">
		function edit(categoryId) {
			window.location.href="category?operate=toEdit&categoryId="+categoryId
		}
		
		function back() {
			window.location.href = "${ctx}/show.jsp";
		}

		function del(categoryId) {
			if(confirm("确定删除该类别吗？")){
				$.ajax({
					url : "${ctx}/category",
					type : "post",
					data : {
						"operate" : "del",
						"categoryId" : categoryId
					},
					success : function(data) {
						if (data != null) {
							alert("删除成功")
							window.location.href = "${ctx}/category?operate=show";
						} else {
							alert("删除失败")
						}
					},
					error : function() {
						alert("服务器当当了...")
					}
				});
			}

		}
		
		function toAdd(){
			window.location.href = "${ctx}/category?operate=toAdd";
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源搜搜</title>

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
		<h2>资源搜搜</h2>
		<h3></h3>
		<form action="${ctx }/source" method="post">
			<table class=table4_1 style="margin-bottom: 20px;">
				<tr>
					<td style="padding-left: 20px;">资源名称：<input name="sourceName"
						value="${sourceName }" id="sourceName" /></td>
					<td style="padding-left: 20px;">资源类别：<input
						value="${categoryName }" name="categoryName" id="categoryName" /></td>
					<td style="padding-left: 20px;">上传者名称：<input name="userName"
						value="${userName }" id="userName" /></td>
					<td style="padding-left: 20px;">资源类型：<select name="sourceType"
						id="sourceType" style="width: 170px"><option value="">请选择</option>
							<option value="1">图片</option>
							<option value="2">音频</option>
							<option value="3">视频</option>
							<option value="4">文件</option>
							<option value="5">其他</option></select></td>
					<td style="padding-left: 20px;"><input type="submit"
						value="搜搜" /></td>
				</tr>
			</table>
		</form>
		<table class=table4_1>
			<tr>
				<th width="10%">序号</th>
				<th width="20%">资源名称</th>
				<th width="10%">资源描述</th>
				<th width="10%">资源类别</th>
				<th width="10%">资源类别描述</th>
				<th width="10%">资源类型</th>
				<th width="10%">上传者</th>
				<th width="10%">上传时间</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach var="sourceDTO" items="${sourceDTOs }" varStatus="status">
				<tr>
					<td width="10%">${status.index + 1}</td>
					<td width="20%">${sourceDTO.sourceDO.sourceName}</td>
					<td width="10%">${sourceDTO.sourceDO.sourceDesc}</td>
					<td width="10%">${sourceDTO.categoryDO.categoryName}</td>
					<td width="10%">${sourceDTO.categoryDO.categoryDesc}</td>
					<td width="10%"><c:if
							test="${sourceDTO.sourceDO.sourceType == 1}">图片</c:if> <c:if
							test="${sourceDTO.sourceDO.sourceType == 2}">音频</c:if> <c:if
							test="${sourceDTO.sourceDO.sourceType == 3}">视频</c:if> <c:if
							test="${sourceDTO.sourceDO.sourceType == 4}">文件</c:if> <c:if
							test="${sourceDTO.sourceDO.sourceType == 5}">其他</c:if></td>
					<td width="10%">${sourceDTO.userDO.userName}</td>
					<td width="10%"><fmt:formatDate
							value="${sourceDTO.sourceDO.gmtCreate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td width="10%"><a
						href="${ctx }/download?filename=${sourceDTO.sourceDO.sourceUrl}">下载</a>
						| <a
						href="${ctx }/comment?sourceId=${sourceDTO.sourceDO.sourceId}">评论</a></td>
				</tr>
			</c:forEach>
		</table>
	</center>

	<script type="text/javascript">
		$("#sourceType").val('${sourceType}')
	</script>
</body>
</html>
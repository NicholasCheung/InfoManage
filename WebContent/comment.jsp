<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源评论</title>

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
		<h2>资源评论</h2>
		<input type="button" style="margin-bottom: 10px; height: 30px;"
			value="返回" onclick="history.back(-1)" />

		<table class=table4_1>
			<tr>
				<th width="20%">资源名称</th>
				<th width="10%">资源描述</th>
				<th width="10%">资源类别</th>
				<th width="10%">资源类别描述</th>
				<th width="10%">资源类型</th>
				<th width="10%">上传者</th>
				<th width="10%">上传时间</th>
			</tr>
			<tr>
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
			</tr>
		</table>
		<c:if test="${user.userId != 0}">
			<table class='table4_1' style="width: 65%; margin-top: 60px;">
				<tr>
					<td width="20%">您的评论：</td>
					<td><textarea id=content style="width: 100%;" rows="5"></textarea></td>
					<td><button onclick="comment(${sourceDTO.sourceDO.sourceId})">提交评论</button></td>
				</tr>
			</table>
		</c:if>
		<table class='table4_1' style="width: 65%; margin-top: 60px;">
			<c:forEach var="commentDTO" items="${commentDTOs }"
				varStatus="status">
				<tr>
					<td width="10%">序号：${status.index + 1}</td>
					<td width="20%">评论者：${commentDTO.userDO.userName }</td>
					<td width="15%">评论时间：<fmt:formatDate
							value="${commentDTO.commentDO.gmtCreate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td width="10%">内容：</td>
					<td width="35%">${commentDTO.commentDO.content }</td>
					<td width="10%"><c:if
							test="${commentDTO.commentDO.status == 0 && user.userId == 0}">
							<font color="green">已删除</font>
						</c:if> <c:if test="${commentDTO.commentDO.status == 1}">
							<c:if
								test="${commentDTO.commentDO.userId == user.userId || user.userId == 0 }">
								<a onclick="delcomment(${commentDTO.commentDO.commentId })">删除</a>
							</c:if>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</center>

	<script type="text/javascript">
	

		function comment(sourceId){
			if("${user.userId}" == ""){
				alert("请先登录")
				return;
			}
			
			var content =  $("#content").val();
			if(content==null || content == ""){
				alert("请输入评论内容");
				return;
			}
			
			$.ajax({
				url : "${ctx}/comment",
				type : "post",
				data : {
					"operate" : "add",
					"content" :content,
					"sourceId" : sourceId
				},
				success : function(data) {
					if (data == "success") {
						alert("评论成功");
						window.location.reload()
					} else{
						alert("评论失败");
					}
				},
				error : function() {
					alert("服务器当当了...")
				}
			});
		}
		
		function delcomment(commentId){
			if(confirm("确定删除该评论内容吗？")){
			$.ajax({
				url : "${ctx}/comment",
				type : "post",
				data : {
					"operate" : "del",
					"commentId" : commentId
				},
				success : function(data) {
					if (data == "success") {
						alert("删除成功");
						window.location.reload()
					} else if (data != "") {
						alert(data);
					}else{
						alert("删除失败");
					}
				},
				error : function() {
					alert("服务器当当了...");
				}
			});
		}}
	</script>
</body>
</html>
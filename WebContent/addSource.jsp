<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源添加</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	<fieldset>

		<form id="addSourceForm">
			<table height="412" width="100%">
				<tr height="170">
					<td width="41%"></td>
					<td><h2>资源添加</h2></td>
				</tr>

				<tr>
					<td></td>
					<td><table>

							<tr>
								<td>资源名称：</td>
								<td><input type="text" name="sourceName" id="sourceName"
									size="20" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>资源描述：</td>
								<td><input type="text" name="sourceDesc" id="sourceDesc"
									size="20" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>资源类别：</td>
								<td><select name="categoryId" id="categoryId"
									style="width: 170px"><option value="">请选择</option>
										<c:forEach items="${categoryDOs }" var="categoryDO">
											<option value="${categoryDO.categoryId }">${categoryDO.categoryName }</option>
										</c:forEach>
								</select></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>资源类型：</td>
								<td><select name="sourceType" id="sourceType"
									style="width: 170px"><option value="">请选择</option>
										<option value="1">图片</option>
										<option value="2">音频</option>
										<option value="3">视频</option>
										<option value="4">文件</option>
										<option value="5">其他</option></select></td>
								<td></td>
								<td></td>
							</tr>


						</table></td>
				<tr>
					<td></td>
					<td><table>
							<tr>
								<td><input type="button" value="提交" onClick="uploadPic()" /></td>
								<td><input type="button" value="返回" onClick="back()" /></td>
							</tr>
						</table></td>
			</table>
			<input type="hidden" name="operate" value="add" /> <input
				type="hidden" name="sourceUrl" id="sourceUrl" />
		</form>
		<form id="fileForm" enctype="multipart/form-data">
			<table style="margin-left: 41%">
				<tr>
					<td>选择资源：</td>
					<td><input type="file" name="sourceFile" id="sourceFile"></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</form>



	</fieldset>
	<script language="javascript">
		function back() {
			window.location.href = "${ctx}/source?operate=show";
		}

		function uploadPic() {
			var sourceName = $("#sourceName").val();
			var sourceFile = $("#sourceFile").val();
			var categoryId = $("#categoryId").val();
			var sourceType = $("#sourceType").val();
			if ((sourceName == "") || (sourceName == null)) {
				alert("请输入资源名称!");
				return false;
			}

			if ((sourceFile == "") || (sourceFile == null)) {
				alert("请选择资源!");
				return false;
			}

			if ((categoryId == "") || (categoryId == null)) {
				alert("请选择资源类别!");
				return false;
			}

			if ((sourceType == "") || (sourceType == null)) {
				alert("请选择资源类型!");
				return false;
			}
			var options = {
				url : "${ctx}/upload",
				// 请求方式  
				type : "post",
				beforeSend : function(data) {
				//	alert("上传中请等待...")
				},
				success : function(data) {
					$("#sourceUrl").val(data)
					add()
				},
				error : function() {
					alert("图片上传失败")
				}
			};

			$("#fileForm").ajaxSubmit(options);
		}

		function add() {

			$.ajax({
				url : "${ctx}/source",
				type : "post",
				data : $("#addSourceForm").serialize(),
				success : function(data) {
					if (data == "success") {
						alert("添加成功")
						window.location.href = "${ctx}/source?operate=show";
					} else {
						alert("添加失败")
					}
				},
				error : function() {
					alert("服务器当当了...")
				}
			});
		}
	</script>
</body>
</html>


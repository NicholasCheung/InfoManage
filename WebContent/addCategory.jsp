<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>类别添加</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	<fieldset>
		<form method="POST" action="login" id="addCategoryForm">
			<table height="412" width="100%">
				<tr height="170">
					<td width="41%"></td>
					<td><h2>类别添加</h2></td>
				</tr>
				<tr>
					<td></td>
					<td><table>

							<tr>
								<td>类别名称：</td>
								<td><input type="text" name="categoryName"
									id="categoryName" size="20" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>类别描述：</td>
								<td><input type="text" name="categoryDesc"
									id="categoryDesc" size="20" /></td>
								<td></td>
								<td></td>
							</tr>
						</table></td>
				<tr>
					<td></td>
					<td><table>
							<tr>
								<td><input type="button" value="提交" onClick="add()" /></td>
								<td><input type="button" value="返回" onClick="back()" /></td>
							</tr>
						</table></td>
			</table>
		</form>
	</fieldset>
	<script language="javascript">
		function back() {
			window.location.href = "${ctx}/category?operate=show";
		}

		function add() {
			var categoryName = $("#categoryName").val();
			var categoryDesc = $("#categoryDesc").val();
			if ((categoryName == "") || (categoryName == null)) {
				alert("请输入类别名称!");
				return false;
			}

			$.ajax({
				url : "${ctx}/category",
				type : "post",
				data : {
					"operate" : "add",
					"categoryName" : categoryName,
					"categoryDesc" : categoryDesc
				},
				success : function(data) {
					if (data == "success") {
						alert("添加成功")
						window.location.href = "${ctx}/category?operate=show";
					} else if (data == null) {
						alert("添加失败")
					} else {
						alert(data)
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


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>

</head>
<body>
	<fieldset>
		<form id="registerForm">
			<table background="images\bg_img1.jpg" height="412" width="100%"
				style="background-repeat: no-repeat; background-size: 100%;">
				<tr height="170">
					<td width="41%"></td>
					<td><h2>用户注册</h2></td>
				</tr>
				<tr>
					<td></td>
					<td><table>

							<tr>
								<td>用户名：</td>
								<td><input type="text" name="username" id="username"
									placeholder="请输入用户名" size="20" maxlength="20" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>密 码：</td>
								<td><input type="password" name="password" id="password"
									placeholder="请输入密码" size="20" maxlength="20" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>确 认 密 码：</td>
								<td><input type="password" name="rePassword"
									id="rePassword" placeholder="请输入确认密码" size="20" maxlength="20" /></td>
								<td></td>
								<td></td>
							</tr>
						</table></td>
				<tr>
					<td></td>
					<td><table>
							<tr>
								<td><input type="button" value="注册"
									onClick="registerUser()" /></td>
								<td><input type="button" value="登录" onClick="toLogin()" /></td>
							</tr>
						</table></td>
			</table>
			<input type="hidden" value="add" name="operate" />
		</form>
	</fieldset>
	<script language="javascript">
		function toLogin() {
			window.location.href = "${ctx}"
		}

		function registerUser() {
			var sUserName = $("#username").val();
			var sPassword = $("#password").val();
			var rePassword = $("#rePassword").val();
			if ((sUserName == "") || (sUserName == null)) {
				alert("请输入用户名!");
				return false;
			}

			if ((sPassword == "") || (sPassword == null)) {
				alert("请输入密码!");
				return false;
			}

			if ((rePassword == "") || (rePassword == null)) {
				alert("请输入确认密码!");
				return false;
			}

			if (rePassword != sPassword) {
				alert("两次密码输入不一致");
				return false;
			}

			$.ajax({
				url : "${ctx}/register",
				type : "post",
				data : $("#registerForm").serialize(),
				success : function(data) {
					if (data == "success") {
						alert("注册成功")
						window.location.href = "${ctx}";
					} else if (data == null || data == "fail") {
						alert("注册失败")
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


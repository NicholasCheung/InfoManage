<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="head_top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>

</head>
<body>
	<fieldset>
		<form method="POST" action="login" id="frmLogin">
			<table background="images\bg_img1.jpg" height="412" width="100%">
				<tr height="170">
					<td width="41%"></td>
					<td><h2>管理员登录</h2></td>
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
						</table></td>
				<tr>
					<td></td>
					<td><table>
							<tr>
								<td><input type="button" name="login" value="登录"
									onClick="validateLogin()" /></td>
								<td><input type="reset" name="rs" value="重置"></td>
							</tr>
							</tr>
						</table></td>
			</table>
		</form>
	</fieldset>
	</form>
	<script language="javascript">
		function validateLogin() {
			var sUserName = $("#username").val();
			var sPassword = $("#password").val();
			if ((sUserName == "") || (sUserName == null)) {
				alert("请输入用户名!");
				return false;
			}

			if ((sPassword == "") || (sPassword == null)) {
				alert("请输入密码!");
				return false;
			}
			
			$("#frmLogin").submit()
		}
	</script>
</body>
</html>


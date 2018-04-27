<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:if test="${user != null }">
	<table style="margin-left: 80%">
		<tr>
			<td><h1>当前登录用户：${user.userName }</h1></td>
			<td style="padding-left: 30px"><h1>
					<a href="${ctx }/login_out">退出</a>
				</h1></td>
		</tr>
	</table>
</c:if>
<c:if test="${user == null }">
	<table style="margin-left: 80%; margin-top: 40px">
		<tr>
			<td><a href="${ctx}" target="_blank"><input type="button"
					value="登录" /></a></td>
			<td style="padding-left: 30px"><a href="${ctx}/register"
				target="_blank"><input type="button" value="注册" /></a></td>
		</tr>
	</table>
</c:if>

<script type="text/javascript">
	$("#sourceType").val('${sourceType}')
</script>
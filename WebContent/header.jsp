<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<table>
	<tr>
		<td>当前登录用户：${user.userName }</td>
		<td><a href="${ctx }/login_out">退出</a></td>
	</tr>
</table>
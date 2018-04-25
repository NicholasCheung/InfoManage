<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="<%=request.getContextPath()%>" var="ctx"></c:set>
<c:set value="<%=request.getServerName()%>" var="server"></c:set>
<c:set value="<%=request.getServerPort()%>" var="port"></c:set>
<c:set value="<%=request.getScheme()%>" var="schneme"></c:set>
<c:set value="${schneme}://${server}:${port}/SourceManage" var="style"></c:set>
<script type="text/javascript" src="${style }/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${style }/js/jquery-form.js"></script>
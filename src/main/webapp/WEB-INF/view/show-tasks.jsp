<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<html lang="${param.lang}">
<head>
    <%@ include file="jspf/common/head.jspf" %>
    <%@ include file="jspf/head-and-foot-deps.jspf" %>
    <title><fmt:message key="page.title.site.name"/></title>
    <link rel="stylesheet" href="${contextPath}/static/css/manage-page.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
   <%@include file="jspf/common/task-table.jspf"%>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="/static/js/common.js"></script>
</body>
</html>
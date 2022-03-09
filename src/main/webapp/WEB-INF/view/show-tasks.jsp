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
    <div class="table_wrapper">
        <table class="fl-table">
            <thead>
            <tr>
                <th><fmt:message key="label.couch.tasks.username.student"/></th>
                <th><fmt:message key="label.couch.tasks.title"/></th>
                <th><fmt:message key="label.couch.tasks.date.submitted"/></th>
                <th><fmt:message key="label.couch.tasks.mark"/></th>
                <th><fmt:message key="label.management.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.studentUsername}</td>
                <td>${task.title}</td>
                <td>${task.submittedDate}</td>
                <td>${task.mark}</td>
                <td width="200px">
                    <div class="button_actions">
                        <form action="${contextPath}/controller?command=checkTask" method="post">
                            <input type="hidden" name="taskId" value="${task.id}">
                            <button><fmt:message key="label.couch.courses.task.check"/></button>
                        </form>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="/static/js/common.js"></script>
</body>
</html>
<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<%@ include file="jspf/common/lang-header-footer.jspf" %>

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
                <th><fmt:message key="label.couch.tasks.title"/></th>
                <th><fmt:message key="label.couch.tasks.deadline"/></th>
                <th><fmt:message key="label.management.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.title}</td>
                <c:set var="datePattern"><fmt:message key='label.pattern.date'/></c:set>
                <td><fmt:formatDate value="${task.deadline}" pattern="${datePattern}"/></td>
                <td width="300px">
                    <div class="button_actions">
                        <form action="${contextPath}/controller?command=doTask" method="post">
                            <button><fmt:message key="label.student.task.start"/></button>
                            <input type="hidden" name="courseId" value="${param.number}">
                            <input type="hidden" name="taskId" value="${task.id}">
                        </form>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
    <div class="text">
    <fmt:message key="label.student.task.submitted"/>
    </div>
    <%@include file="jspf/common/task-table.jspf"%>
</div>


<%@ include file="jspf/common/footer.jspf" %>
<script src="/static/js/common.js"></script>
</body>
</html>
<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<html lang="${param.lang}">
<head>
    <%@ include file="jspf/common/head.jspf" %>
    <%@ include file="jspf/head-and-foot-deps.jspf" %>
    <title><fmt:message key="page.title.site.name"/></title>
    <link rel="stylesheet" href="${contextPath}/static/css/course-page.css">
    <link rel="stylesheet" href="${contextPath}/static/css/label.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
    <div class="body_elements">
        <div id="form_task_creator">
            <form action="${contextPath}/controller?command=submitCheck" method="POST">
                <input type="hidden" name="userTaskId" value="${task.id}">
                <p>
                <fmt:message key="label.couch.tasks.title"/>: ${task.title}
                </p>
                <p>
                <fmt:message key="label.couch.tasks.description"/>: ${task.description}
                </p>
                <fmt:message key="label.couch.tasks.solution"/>:
                <c:if test="${userRole eq 'COUCH'}">
                    <p>${task.solution}</p>
                </c:if>
                <c:if test="${userRole eq 'USER'}">
                <textarea name="solution" maxlength="255"></textarea>
                </c:if>
                <fmt:message key="label.couch.tasks.mark"/>:
                <input type="number" min="0" max="10" name="mark">
                <fmt:message key="label.couch.tasks.feedback"/>:
                <textarea name="feedback" maxlength="255"></textarea>
                <div class="button_wrapper">
                    <input type="submit" value="Submit">
                </div>
            </form>
            <c:if test="${not empty invalidData}">
                <div class="warning">
                    <fmt:message key="label.couch.courses.edit.unsuccessfully"/>
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="/static/js/common.js"></script>
</body>
</html>
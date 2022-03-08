<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<html lang="${param.lang}">
<head>
    <%@ include file="jspf/common/head.jspf" %>
    <%@ include file="jspf/head-and-foot-deps.jspf" %>
    <title><fmt:message key="page.title.tasks.check"/></title>
    <link rel="stylesheet" href="${contextPath}/static/css/course.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
    <div class="course block">
        <fmt:message key="label.management.course.title"/>: ${course.title}
        <fmt:message key="label.management.course.description"/>: ${course.description}
        <fmt:message key="label.management.course.duration"/>: ${course.duration}
        <fmt:message key="label.management.course.technology"/>: ${course.technology.getDecodingValue()}
        <fmt:message key="label.management.course.format"/>: ${course.courseFormat}
        <fmt:message key="label.management.course.current-pupils-quantity"/>: ${course.currentPupilsQuantity}
        <fmt:message key="label.management.course.max-pupils-quantity"/>: ${course.maxPupilsQuantity}
        <fmt:message key="label.management.course.active"/>: ${course.active}
    </div>
    <div class="course editor">
        <form action="${contextPath}/controller?command=editCourse" method="POST">
            SMTH
        </form>
    </div>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="${contextPath}/static/js/common.js"/>
</body>
</html>
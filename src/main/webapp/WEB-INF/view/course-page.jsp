<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<html lang="${param.lang}">
<head>
    <%@ include file="jspf/common/head.jspf" %>
    <%@ include file="jspf/head-and-foot-deps.jspf" %>
    <link rel="stylesheet" href="/static/css/courses-page.css">
    <title>${course.title}</title>
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
    <div class="item info">
        <h2 class="item title">${course.title}</h2>
        <div class="item description">${course.duration}</div>
        <div class="item label">
            ${course.technology} ${course.courseFormat}
        </div>

        <p>
            <fmt:message key="label.item.current-pupils-quantity"/> ${course.currentPupilsQuantity}
            <fmt:message key="label.item.from"/> ${course.maxPupilsQuantity}
            <fmt:message key="label.item.availability"/>${course.active}
        </p>
    </div>
    <c:if test="${userRole eq 'USER'}">
    <c:if test="${not empty subscribe}">
        <form action="${pageContext.request.contextPath}/controller?command=subscribe" method="post">
            <input type="hidden" name="courseId" value="${course.id}">
            <button><fmt:message key="label.item.subscribe"/></button>
        </form>
    </c:if>
    <c:if test="${not empty unsubscribe}">
        <form action="${pageContext.request.contextPath}/controller?command=unsubscribe" method="post">
            <input type="hidden" name="courseId" value="${course.id}">
            <button><fmt:message key="label.item.unsubscribe"/></button>
        </form>
    </c:if>

    <div class="message fail">
        <c:if test="${fail}">
            <fmt:message key="label.item.subscription"/>
        </c:if>
    </div>
    </c:if>
</div>
<%@ include file="jspf/common/footer.jspf" %>
<script src="/static/js/common.js"></script>
</body>
</html>
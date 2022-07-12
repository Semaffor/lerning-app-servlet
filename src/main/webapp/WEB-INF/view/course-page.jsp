<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<%@ include file="jspf/common/lang-header-footer.jspf" %>
<link rel="stylesheet" href="/static/css/courses-page.css">
<title>${course.title}</title>
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>
<div class="body_wrapper">
    <div class="course_block">

        <h2 class="item title">${course.title}</h2>
        <div class="course_item description">
            <fmt:message key="label.management.course.duration"/>: ${course.duration}
            <fmt:message key="label.management.course.duration.days"/>
        </div>
        <div class="course_item label"><fmt:message key="label.management.course.technology"/>:${course.technology.getDecodingValue()}</div>
        <div class="course_item"><fmt:message key="label.management.course.format"/>: ${course.courseFormat}</div>
        <div class="course_item">
            <fmt:message key="label.item.current-pupils-quantity"/> ${course.currentPupilsQuantity}
            <fmt:message key="label.item.from"/> ${course.maxPupilsQuantity}
        </div>
        <div class="course_item">
            <c:if test="${course.active eq 'true'}">
                <fmt:message key="label.item.availability"/> <fmt:message key="label.management.course.active"/>
            </c:if>
            <c:if test="${course.active eq 'false'}">
                <fmt:message key="label.item.availability"/> <fmt:message key="label.management.course.inactive"/>
            </c:if>
        </div>
    </div>
    <c:if test="${userRole eq 'USER'}">
        <c:if test="${not empty subscribe}">
            <form action="${pageContext.request.contextPath}/controller?command=subscribe" method="post">
                <input type="hidden" name="courseId" value="${course.id}">
                <button class="buttons button-green"><fmt:message key="label.item.subscribe"/></button>
            </form>
        </c:if>
        <c:if test="${not empty unsubscribe}">
            <form action="${pageContext.request.contextPath}/controller?command=unsubscribe" method="post">
                <input type="hidden" name="courseId" value="${course.id}">
                <button class="buttons button-red"><fmt:message key="label.item.unsubscribe"/></button>
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
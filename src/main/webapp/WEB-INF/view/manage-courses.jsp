<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<%@ include file="jspf/common/lang-header-footer.jspf" %>

    <link rel="stylesheet" href="${contextPath}/static/css/manage-page.css">
    <title>Aduliner</title>
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>
<div class="body_wrapper">
    <div class="table_wrapper">
        <table class="fl-table">
            <thead>
            <tr>
                <th><fmt:message key="label.management.course.title"/></th>
                <th><fmt:message key="label.management.course.format"/></th>
                <th><fmt:message key="label.management.course.technology"/></th>
                <th><fmt:message key="label.management.course.active"/></th>
                <th><fmt:message key="label.management.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
            <tr>
                <td class="td_title">${course.title}</td>
                <td class="td_description">${course.courseFormat}</td>
                <td>${course.technology.getDecodingValue()}</td>
                <td><c:if test="${course.active}">
                    <div class="img_wrapper"><img class="img_status"
                                                  src="${contextPath}/static/images/unlocked.png"/></div>
                </c:if>
                    <c:if test="${course.active eq 'false'}">
                        <div class="img_wrapper"><img class="img_status"
                                                      src="${contextPath}/static/images/padlock.png"/></div>
                    </c:if>
                </td>
                <td class="td_action">
                    <div class="button_actions">
                        <form action="${contextPath}/controller?command=manageCoursesAction" method="post">
                            <c:if test="${course.active eq 'true'}">
                                <input type="hidden" name="formAction" value="disable">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button class="buttons"><fmt:message
                                        key="label.management.course.action.disable"/></button>
                            </c:if>
                            <c:if test="${course.active eq 'false'}">
                                <input type="hidden" name="formAction" value="enable">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button class="buttons"><fmt:message
                                        key="label.management.course.action.enable"/></button>
                            </c:if>
                        </form>
                        <form action="${contextPath}/controller?command=manageCoursesAction" method="post">
                            <c:if test="${course.deleted eq 'false'}">
                                <input type="hidden" name="formAction" value="delete">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button class="buttons"><fmt:message
                                        key="label.management.course.action.delete"/></button>
                            </c:if>
                        </form>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
    <div class="table_wrapper">
        <div class="text"><fmt:message key="label.management.course.archive"/></div>
        <table class="fl-table">
            <tbody>
            <c:forEach var="course" items="${deletedCourses}">
            <tr>
                <td class="td_title">${course.title}</td>
                <td class="td_description">${course.courseFormat}</td>
                <td>${course.technology.getDecodingValue()}</td>
                <td><c:if test="${course.active}">
                    <div class="img_wrapper"><img class="img_status"
                                                  src="${contextPath}/static/images/unlocked.png"/></div>
                </c:if>
                    <c:if test="${course.active eq 'false'}">
                        <div class="img_wrapper"><img class="img_status"
                                                      src="${contextPath}/static/images/padlock.png"/></div>
                    </c:if>
                </td>
                <td class="td_action">
                    <div class="button_actions">
                        <form action="${contextPath}/controller?command=manageCoursesAction" method="post">
                            <input type="hidden" name="formAction" value="restore">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <button class="buttons"><fmt:message key="label.management.course.action.restore"/></button>
                        </form>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
</div>

<div class="modal-window">
    Are you sure?
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
</body>
</html>
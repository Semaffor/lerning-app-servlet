<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<%@ include file="jspf/common/lang-header-footer.jspf" %>

<link rel="stylesheet" href="${contextPath}/static/css/manage-page.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>
<div class="body_wrapper">
    <div class="table_wrapper">
        <table class="fl-table">
            <thead>
            <tr>
                <th><fmt:message key="label.management.user.username"/></th>
                <th><fmt:message key="label.management.user.role"/></th>
                <th><fmt:message key="label.management.user.status"/></th>
                <th><fmt:message key="label.management.action"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>
                    <form action="${contextPath}/controller?command=editUserRole" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <select id="select_roles" name="chosenRole">
                            <c:forEach var="role" items="${roles}">
                                <c:if test='${user.role != role}'>
                                    <option value="${role.getCode()}">${role}</option>
                                </c:if>
                                <c:if test='${user.role == role}'>
                                    <option value="${role.getCode()}" disabled selected>${role}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <button class="buttons"><fmt:message key="label.management.user.edit"/></button>
                    </form>

                </td>
                <td>
                    <c:if test="${user.blocked}">
                        <div class="img_wrapper"><img class="img_status"
                                                      src="${contextPath}/static/images/padlock.png"/></div>
                    </c:if>
                    <c:if test="${user.blocked eq 'false'}">
                        <div class="img_wrapper"><img class="img_status"
                                                      src="${contextPath}/static/images/unlocked.png"/></div>
                    </c:if>
                </td>
                <td width="200px">
                    <div class="button_actions">
                        <form action="${contextPath}/controller?command=manageUsersAction" method="post">
                            <c:if test="${user.blocked eq 'true'}">
                                <input type="hidden" name="formAction" value="disable">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button class="buttons button-green"><fmt:message
                                        key="label.management.course.action.enable"/></button>
                            </c:if>
                            <c:if test="${user.blocked eq 'false'}">
                                <input type="hidden" name="formAction" value="enable">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button class="buttons button-red"><fmt:message
                                        key="label.management.course.action.disable"/></button>
                            </c:if>
                        </form>
                        <button class="buttons button-red openModal"><fmt:message
                                key="label.management.course.action.delete"/></button>
                        <div id="myModal" class="modal">
                            <!-- Modal content -->
                            <div class="modal-content">

                                <h3 class="modal-text"><fmt:message key="label.modal.question.confirm"/>
                                    "${user.username}"?</h3>
                                <form action="${contextPath}/controller?command=manageUsersAction" method="post">
                                    <c:if test="${user.deleted eq 'false'}">
                                        <input type="hidden" name="formAction" value="delete">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <button class="buttons button-red"><fmt:message
                                                key="label.management.course.action.delete"/></button>
                                        <button type="button" id="closeModal" class="buttons">
                                            <fmt:message key="label.modal.action.deny"/></button>
                                    </c:if>
                                </form>
                            </div>
                        </div>
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
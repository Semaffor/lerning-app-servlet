<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<html lang="${param.lang}">
<head>
    <%@ include file="jspf/common/head.jspf" %>
    <%@ include file="jspf/head-and-foot-deps.jspf" %>
    <title><fmt:message key="page.title.course.management"/></title>
    <link rel="stylesheet" href="${contextPath}/static/css/course-page.css">
    <link rel="stylesheet" href="${contextPath}/static/css/label.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
    <div class="body_elements">
        <c:if test="${empty course}">
            <h2><fmt:message key="label.couch.courses.empty"/></h2>
            <button id="forOpenEdit"><fmt:message key="label.couch.courses.create.course"/></button>
        </c:if>
        <c:if test="${not empty course}">
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
            <button id="forOpenEdit"><fmt:message key="label.couch.courses.edit"/></button>
            <button id="forOpenCreateTask"><fmt:message key="label.couch.courses.task.create"/></button>
            <div class="notification_wrapper">
            <c:if test="${not empty incorrectValues}">
                <div class="notification error"> <fmt:message key="label.couch.courses.edit.unsuccessfully"/></div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="notification"><fmt:message key="label.couch.courses.edit.success"/></div>
            </c:if>
            </div>
        </c:if>
        <div id="form_edit">
            <c:if test="${not empty course}"><c:set var="command" value="editCourse"/></c:if>
            <c:if test="${empty course}"><c:set var="command" value="createCourse"/></c:if>
            <form class="course editor" action="${contextPath}/controller?command=${command}" method="POST">
                <input type="hidden" name="courseId" value="${course.id}">
                <fmt:message key="label.management.course.title"/>:
                <input type="text" name="title_course" value="${course.title}">
                <fmt:message key="label.management.course.description"/>:
                <textarea name="description_course">${course.description}</textarea>
                <fmt:message key="label.management.course.duration"/>:
                <input type="number" name="duration" value="${course.duration}" max="100" min="1">
                <fmt:message key="label.management.course.technology"/>:
                <select name="chosenTechnology">
                    <c:forEach var="technology" items="${technologies}">
                        <c:if test='${course.technology != technology}'>
                            <option value="${technology.getCode()}">${technology.getDecodingValue()}</option>
                        </c:if>
                        <c:if test='${course.technology == technology}'>
                            <option value="${technology.getCode()}" selected>${technology.getDecodingValue()}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <fmt:message key="label.management.course.format"/>:
                <select name="chosenFormat">
                    <c:forEach var="format" items="${formats}">
                        <c:if test='${course.courseFormat != format}'>
                            <option value="${format.getCode()}">${format}</option>
                        </c:if>
                        <c:if test='${course.courseFormat == format}'>
                            <option value="${format.getCode()}" selected>${format}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <fmt:message key="label.management.course.max-pupils-quantity"/>:
                <input type="number" name="maxPupilsQuantity" value="${course.maxPupilsQuantity}" max="100" min="10">
                <div class="button_wrapper">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
        <div id="form_creator">
            <form action="${contextPath}/controller?command=createTask" method="POST">
                <input type="hidden" name="courseId" value="${course.id}">
                <fmt:message key="label.couch.tasks.title"/>
                <input type="text" name="title_task" maxlength="45" >
                <fmt:message key="label.couch.tasks.description"/>
                <textarea name="description_task" maxlength="255"></textarea>
                <fmt:message key="label.couch.tasks.deadline"/>
                <input type="datetime-local" name="deadline">
                <div class="button_wrapper">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="${contextPath}/static/js/common.js"></script>
</body>
</html>
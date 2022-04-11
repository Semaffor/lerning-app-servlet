<%@ taglib prefix="if" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="jspf/common/page.jspf" %>
<%@ include file="jspf/common/taglib.jspf" %>

<%@ include file="jspf/common/lang-header-footer.jspf" %>

    <link rel="stylesheet" href="${contextPath}/static/css/course-page.css">
    <link rel="stylesheet" href="${contextPath}/static/css/label.css">
</head>
<body>
<%@ include file="jspf/common/navbar.jspf" %>

<div class="body_wrapper">
    <div class="body_elements">
        <c:if test="${empty course}">
            <h2><fmt:message key="label.couch.courses.empty"/></h2>
            <button class="buttons" id="forOpenCreateCourse"><fmt:message key="label.couch.courses.create.course"/></button>
        </c:if>
        <c:if test="${not empty course}">
            <div class="course_block">
                <div class="course_item"><fmt:message key="label.management.course.title"/>: ${course.title}</div>
                <div class="course_item"><fmt:message key="label.management.course.description"/>: ${course.description}</div>
                <div class="course_item"><fmt:message key="label.management.course.duration"/>: ${course.duration}
                    <fmt:message key="label.management.course.duration.days"/>
                </div>
                <div class="course_item"><fmt:message key="label.management.course.technology"/>: ${course.technology.getDecodingValue()}</div>
                <div class="course_item"><fmt:message key="label.management.course.format"/>: ${course.courseFormat}</div>
                <div class="course_item"><fmt:message key="label.management.course.current-pupils-quantity"/>: ${course.currentPupilsQuantity}</div>
                <div class="course_item"><fmt:message key="label.management.course.max-pupils-quantity"/>: ${course.maxPupilsQuantity}</div>
                <div class="course_item"><fmt:message key="label.management.course.active"/>:
                        <c:if test="${course.active eq true}"><fmt:message key="label.management.course.active"/></c:if>
                        <c:if test="${course.active eq false}"><fmt:message key="label.management.course.inactive"/></c:if></div>
            </div>
            <button id="forOpenEdit" class="buttons"><fmt:message key="label.couch.courses.edit"/></button>
            <button id="forOpenCreateTask" class="buttons"><fmt:message key="label.couch.courses.task.create"/></button>
            <c:if test="${course.active eq false}">
                <form action="${contextPath}/controller?command=activateCourse" method="post">
                    <input type="hidden" name="courseId" value="${course.id}">
                    <button class="buttons button-green"><fmt:message key="label.management.course.action.enable"/> </button>
                </form>
            </c:if>
            <div class="notification_wrapper">
                <c:if test="${not empty param.incorrectValues}">
                    <div class="notification error"> <fmt:message key="label.couch.courses.task.create.unsuccessful"/></div>
                </c:if>
                <c:if test="${not empty param.incorrectValues}">
                    <div class="notification error"> <fmt:message key="label.couch.courses.task.create.successful"/></div>
                </c:if>
            <c:if test="${not empty param.incorrectValues}">
                <div class="notification error"> <fmt:message key="label.couch.courses.edit.unsuccessfully"/></div>
            </c:if>
            <c:if test="${not empty param.success}">
                <div class="notification"><fmt:message key="label.couch.courses.task.edit.success"/></div>
            </c:if>
            </div>
        </c:if>
        <div id="form_edit">
            <c:if test="${not empty course}"><c:set var="command" value="editCourse"/></c:if>
            <c:if test="${empty course}"><c:set var="command" value="createCourse"/></c:if>
            <form class="course_editor" action="${contextPath}/controller?command=${command}" method="POST">
                <input type="hidden" name="courseId" value="${course.id}">
                <fmt:message key="label.management.course.title"/>:
                <input type="text" name="title_course" value="${course.title}">
                <fmt:message key="label.management.course.description"/>:
                <textarea name="description_course">${course.description}</textarea>
                <fmt:message key="label.management.course.duration"/>:
                <input type="number" name="duration" value="${course.duration}" max="100" min="10">
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
                    <input type="submit" value="Submit">
            </form>
        </div>
        <div id="form_creator">
            <form class="course_editor" action="${contextPath}/controller?command=createTask" method="POST">
                <input type="hidden" name="courseId" value="${course.id}">
                <fmt:message key="label.couch.tasks.title"/>
                <input type="text" name="title_task" maxlength="45" required>
                <fmt:message key="label.management.course.description"/>
                <textarea name="description_task" maxlength="255" required></textarea>
                <fmt:message key="label.couch.tasks.deadline"/>
                <input type="datetime-local" name="deadline" required>
                    <input type="submit" value="Submit">
            </form>
        </div>
        <c:if test="${param.successTask eq true}">
            <div class="label notification">
                <fmt:message key="label.couch.courses.task.create.successful"/>
            </div>
        </c:if>
        <c:if test="${param.successTask eq 'date'}">
            <div class="label warning">
                <fmt:message key="label.couch.courses.task.incorrect.date"/>
            </div>
        </c:if>
        <c:if test="${param.successTask eq 'title'}">
            <div class="label warning">
                <fmt:message key="label.couch.courses.task.incorrect.title"/>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="jspf/common/footer.jspf" %>
<script src="${contextPath}/static/js/common.js"></script>
<script src="${contextPath}/static/js/create-course.js"></script>
</body>
</html>
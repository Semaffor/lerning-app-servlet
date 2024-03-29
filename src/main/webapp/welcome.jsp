<%@include file="WEB-INF/view/jspf/common/page.jspf" %>
<%@include file="WEB-INF/view/jspf/common/taglib.jspf" %>

<html lang="${sessionScope.lang}">
<head>
    <jsp:include page="WEB-INF/view/jspf/common/head.jspf"/>
    <title><fmt:message key="label.nav.menu.logIn"/></title>
    <link rel="shortcut icon" href="${contextPath}/static/images/logo.png">
    <link rel="stylesheet" href="static/css/welcome.css">
    <link rel="stylesheet" href="static/css/label.css">
    <link rel="stylesheet" href="static/css/dropbox.css">
</head>
<body>
<div class="navbar_block">
    <%@ include file="WEB-INF/view/jspf/common/language-box.jspf" %>
</div>
<div class="container-wrapper">
    <div class="container">
        <form action="${contextPath}controller?command=login" method="post">
            <div class="title"><fmt:message key="label.login.title.enter"/></div>
            <div class="input-box underline">
                <input type="text" placeholder="<fmt:message key="label.login.username"/>" name="username"
                       autocomplete="off" required>
                <div class="underline"></div>
            </div>
            <div class="input-box">
                <input type="password" placeholder="<fmt:message key="label.login.password"/>" name="password"
                       autocomplete="off" required>
                <div class="underline"></div>
            </div>

            <c:if test="${not empty errorBlocked}">
                <div class="label blocked"><fmt:message key="label.login.blocked"/></div>
            </c:if>
            <c:if test="${not empty errorInvalid}">
                <div class="label warning"><fmt:message key="label.login.invalid"/></div>
            </c:if>
            <c:if test="${not empty param.logout}">
                <div class="label notification"><fmt:message key="label.login.message.logout"/></div>
            </c:if>
            <c:if test="${not empty param.registration}">
                <div class="label notification"><fmt:message key="label.registration.success"/></div>
            </c:if>
            <div class="input-box button">
                <input type="submit" value="<fmt:message key="label.login.enter"/>">
            </div>
        </form>
        <div class="title forgot">
            <a class="underlineHover" href="${contextPath}controller?command=registration"><fmt:message key="label.login.registration"/></a>
        </div>
    </div>
</div>
</body>
<body>
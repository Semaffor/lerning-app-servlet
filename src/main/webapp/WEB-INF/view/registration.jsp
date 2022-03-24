<%@include file="jspf/common/page.jspf" %>
<%@include file="jspf/common/taglib.jspf" %>

<html lang="${sessionScope.lang}">
<head>
    <jsp:include page="jspf/common/head.jspf"/>
    <title><fmt:message key="label.nav.menu.logIn"/></title>
    <link rel="stylesheet" href="static/css/welcome.css">
    <link rel="stylesheet" href="static/css/label.css">
    <link rel="stylesheet" href="static/css/dropbox.css">
</head>
<body>
<div class="navbar_block">
    <%@ include file="jspf/common/language-box.jspf" %>
</div>
<div class="container-wrapper">
    <div class="container">
        <form action="${pageContext.request.contextPath}controller?command=createUser" method="post">
            <div class="title"><fmt:message key="label.registration.title"/></div>
            <div class="input-box underline">
                <input type="text" placeholder="<fmt:message key="label.registration.username"/>" name="username"
                       autocomplete="off" required>
                <div class="underline"></div>
            </div>
            <div class="input-box">
                <input type="password" placeholder="<fmt:message key="label.registration.password"/>" name="password"
                       autocomplete="off" minlength="3" maxlength="28"required>
                <div class="underline"></div>
            </div>
            <div class="input-box">
                <input type="password" placeholder="<fmt:message key="label.registration.password.repeat"/>" name="repeatedPassword"
                       autocomplete="off" minlength="3" maxlength="28" required>
                <div class="underline"></div>
            </div>

            <c:if test="${not empty param.alreadyExists}">
                <div class="label warning"><fmt:message key="label.registration.invalid.username"/></div>
            </c:if>
            <c:if test="${not empty param.invalidPassword}">
                <div class="label warning"><fmt:message key="label.registration.invalid.password.incorrect"/></div>
            </c:if>
<%--            <c:if test="${invalidPassword eq 'Empty password.'}">--%>
<%--                <div class="label warning"><fmt:message key="label.registration.invalid.password.empty"/></div>--%>
<%--            </c:if>--%>
<%--            <c:if test="${invalidPassword eq 'Passwords are not similar.'}">--%>
<%--                <div class="label warning"><fmt:message key="label.registration.invalid.password.different"/></div>--%>
<%--            </c:if>--%>
<%--            <c:if test="${not empty invalidPassword}">--%>
<%--                <div class="label warning"><fmt:message key="label.registration.invalid.password.length"/></div>--%>
<%--            </c:if>--%>
            <div class="input-box button">
                <input type="submit" value="<fmt:message key="label.signUp"/>">
            </div>
        </form>
        <div class="title forgot">
            <a class="underlineHover" href="${contextPath}/"><fmt:message key="label.registration.cancel"/></a>
        </div>
    </div>
</div>
</body>
<body>
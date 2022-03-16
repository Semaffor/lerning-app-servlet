<%@ include file="../jspf/common/page.jspf" %>
<%@ include file="../jspf/common/taglib.jspf" %>

<!doctype html>
<html lang="en">
<head>
   <%@ include file="../jspf/common/head.jspf" %>
   <link rel="stylesheet" href="${contextPath}/static/css/error.css">
   <title>500</title>
</head>
<body>

<div class="center">
   <div class="error">
      <div class="number">5</div>
      <div class="illustration">
         <div class="circle"></div>
         <div class="clip">
            <div class="paper">
               <div class="face">
                  <div class="eyes">
                     <div class="eye eye-left"></div>
                     <div class="eye eye-right"></div>
                  </div>
                  <div class="rosyCheeks rosyCheeks-left"></div>
                  <div class="rosyCheeks rosyCheeks-right"></div>
                  <div class="mouth"></div>
               </div>
            </div>
         </div>
      </div>
      <div class="number">0</div>
   </div>

   <div class="text"><fmt:message key="label.error.500"/> </div>
   <a class="button" href="${contextPath}/controller?command=showMain"><fmt:message key="label.item.back"/></a>
</div>
</body>
</html>
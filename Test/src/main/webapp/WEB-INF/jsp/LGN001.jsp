<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/test.css"> 
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            
          </div>
        </div>
        
        <form:form class="login-form" action="Ulog" method="post" modelAttribute="LGN">
          <form:input placeholder="User Name" path="name" required="true"/>
          <form:password placeholder="Password" path="pas" required="true"/>
          <button type="submit">login</button>
          <p class="message">Not registered? <a href="reg">Create an account</a></p>
        </form:form>
      </div>
    </div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/test.css"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>User Registration</title>
</head>

<body class="login-page-body"> 
    
   
          <%@ page import="java.io.*" %>
          <%@ page import="com.DAO.UserDAO" %>
          <%
          UserDAO uDao = new UserDAO();
          String u_id = uDao.generateUsrId();
          request.getSession().setAttribute("id", u_id);
          %>
        
       
       
      
      <div class="main_contents">
    <div id="sub_content">
    <p><b style="color:red;">${error}</b></p>
    <p><b style="color:blue;">${msg}</b></p>
        <form:form action="Ureg1" method="post" modelAttribute="USR001">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4" style="color:white">User Registration</h2>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="id" class="col-md-2 col-form-label" style="color:white">ID</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" path="id" id="id" value="${sessionScope.id}" readonly="readonly"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="email" class="col-md-2 col-form-label" style="color:white">Name</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" path="name" id="email" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="email" class="col-md-2 col-form-label" style="color:white">Email</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" path="email" id="email" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="pas" class="col-md-2 col-form-label" style="color:white">Password</form:label>
                <div class="col-md-4">
                    <form:password class="form-control" path="pas" id="password" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="c_pas" class="col-md-2 col-form-label" style="color:white">Confirm Password</form:label>
                <div class="col-md-4">
                   <form:password class="form-control" path="c_pas" id="comfirmPassword" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="role" class="col-md-2 col-form-label" style="color:white">User Role</form:label>
                <div class="col-md-4">
                    <form:select path="role" class="form-select" aria-label="Education" id="userRole">
                    	<form:option value="Admin">Admin</form:option>
                        <form:option value="User">User</form:option>
                    </form:select>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-6">
                   
    
                    <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Add</button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Registration</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                   
                                   <h5 style="color: rgb(127, 209, 131);">Registered Successfully !</h5>
                                </div>
          
                            </div>
                        </div>
                        </div>
                </div>
    
            </div>
            </form:form>
    </div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>

</html>
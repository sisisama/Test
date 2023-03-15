<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
        <title>Course Registration</title>
</head>

<body>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="umenu"><h3>Student Registration</h3></a>
        </div>  
              
        
        <div class="col-md-6">
           <p>User : ${sessionScope.name}</p>
            <p>CurrentDate : ${sessionScope.date}</p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='/Test/'">
        </div>        
    </div>
</div>

</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
            <div class="dropdown-container">
          <a href="stu1">Student Registration </a>
        </div>
      </div>
      <div class="main_contents">
    <div id="sub_content">
   <p><b style="color:red;">${error}</b></p>
    <p><b style="color:blue;">${msg}</b></p>
        <form:form action="stureg2" method="post" enctype="multipart/form-data" modelAttribute="STU002">
        <%@ page import="java.io.*" %>
         <%@ page import="com.DAO.CourseDAO" %>
          <%@ page import="com.DAO.StudentDAO" %>
           <%@ page import="com.DTO.CourseResponseDTO" %>
           <%@ page import="java.util.ArrayList" %>
         
        <%
        
		CourseDAO dao = new CourseDAO();
		StudentDAO sdao = new StudentDAO();
		ArrayList<CourseResponseDTO> list = dao.ShowCourse();
		request.getSession().setAttribute("course", list);
		String stu_id = sdao.generateCusId();
		request.getSession().setAttribute("id", stu_id);
        %>

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Registration</h2>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="id" class="col-md-2 col-form-label">Student ID</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" path="id" id="id" value="${sessionScope.id}" readonly="readonly"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="name" class="col-md-2 col-form-label">Name</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" id="name" path="name" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="dob" class="col-md-2 col-form-label">DOB</form:label>
                <div class="col-md-4">
                    <form:input class="form-control" id="dob" path="dob" type="date"/>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input"  path="gender" id="gridRadios1" value="Male"
                            />
                        <form:label class="form-check-label" path="gender">
                            Male
                        </form:label>
                    </div>
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input"  path="gender" id="gridRadios2" value="Female"/>
                        <form:label class="form-check-label" path="gender">
                            Female
                        </form:label>
                    </div>
    
                </div>
            </fieldset>
    
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="phone" class="col-md-2 col-form-label">Phone</form:label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="phone" path="phone" required="true"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="education" class="col-md-2 col-form-label">Education</form:label>
                <div class="col-md-4">
                    <form:select path="education" class="form-select" aria-label="Education" id="education">
                        <form:option value="Bachelor of Information Technology">Bachelor of Information Technology</form:option>
                        <form:option value="Diploma in IT">Diploma in IT</form:option>
                        <form:option value="Bachelor of Computer Science">Bachelor of Computer Science</form:option>
    
                    </form:select>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
                
                <div class="col-md-4">
                <c:forEach items="${sessionScope.course}" var="data" >
                        <form:checkbox class="form-check-input" path="attend" value="${data.name}"/>
                        ${data.name}          
                </c:forEach>               
                </div>                
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="photo" class="col-md-2 col-form-label">Photo</form:label>
                <div class="col-md-4">
                    <form:input type="file" class="form-control" id="name" path="photo" required="true"/>
               
                    
                </div>
            </div>
    		
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                    <button type="reset" class="btn btn-danger ">
    
                        Reset
                    </button>
                   <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Add
                    </button>
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
                                <h5 style="color: rgb(127, 209, 131);">Registered Succesfully !</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                               
                            </div>
                        </div>
                    </div>
            </div>
                </div>

    
            </div>
    
    
    
    
    
            </form:form>
    </div>
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


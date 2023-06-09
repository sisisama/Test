<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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
           <p style="text-color: black;">User : ${sessionScope.name}</p>
            <p style="text-color: black;">CurrentDate : ${sessionScope.date}</p>
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
          <a href="cour">Course Registration </a>
          <a href="stu">Student Registration </a>
          <a href="sstb">Student Search </a>
        </div>
        <a href="stb">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
      <form action="ssearch" method="post"  class="row g-3 mt-3 ms-2" >
      
        <div class="col-auto">
          <label class="visually-hidden">studentID</label>
          <input type="text" class="form-control" name="id" placeholder="Student ID"/>
        </div>
        <div class="col-auto">
          <label class="visually-hidden">studentName</label>
          <input type="text" class="form-control" name="name" placeholder="Student Name" />
        </div>
        <div class="col-auto">
            <label class="visually-hidden">Course</label>
            <input type="text" class="form-control"  name="attend" placeholder="Course"/>
          </div>
        <div class="col-auto">
          <button type="submit"  class="btn btn-success mb-3">Search</button>
        </div>
        <div class="col-auto">
          <button type="reset" class="btn btn-secondary mb-3" onclick="location.href='sstb'">Reset</button>
        </div>
	</form>
  <p><b style="color:red;">${error}</b></p>
  
    <p><b style="color:blue;">${msg}</b></p>
      <table class="table table-striped" id="stduentTable">
        <thead>
          <tr>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <c:forEach items="${list}" var="data">
        <tbody>
          <tr>
            <td>${data.id}</td>
            <td>${data.name}</td>
    		<td>${data.attend}</td>
            <td>
                    <button type="button" class="btn btn-success  " onclick="location.href = 'sform?id=${data.id}';">
                        See More
                    </button>
                </td>
          </tr>
         
        </tbody>
        </c:forEach>
      </table>
      
    
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
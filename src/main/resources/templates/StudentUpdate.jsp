<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link th:href="@{/resources/stylesheets/test.css}" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Student Details Page</title>
</head>
<body>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <h3><a th:href="@{/welcomePage}">Student Registration</a></h3>
        </div>  
        <div class="col-md-6">
            <p>User : <span th:text="${session.res.userId}"></span>  <span th:text="${session.res.userName}"></span></p>
            <p>Current Date : <span th:text="${#dates.format(#dates.createNow(), 'dd MMM yyyy HH:mm')}"></span></p>
        </div>  
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick="location.href='UserLogoutServlet'">
        </div>        
    </div>
    </div>
    
    </div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
            <div class="dropdown-container">
				<a th:href="@{/CourseRegistration}">Course Registration</a> 
				<a th:href="@{/AddStudent}">Student Registration</a> 
				<a th:href="@{/SearchStudentServlet}">Student Search </a>
			</div>
		<a th:href="@{/SearchUser}">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
        <form action="#" th:action="@{/UpdateStudentServlet}" method="post" th:object="${bean}">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Update</h2>
             <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label"></label>
                <div class="col-md-4">
                    <h5 style="color:red;" th:text="${error}"></h5>
					<h5 style="color:darkblue" th:text="${msg}"></h5>
		   <!-- <form:errors path="*" cssClass="errorblock" element="div" />  -->
                </div>
             </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{studentId}" class="form-control"  id="studentID" placeholder="Enter your id" />
                    <span class="span1 text-danger" th:if="${#fields.hasErrors('studentId')}" th:errors="*{studentId}"></span>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{studentName}" class="form-control" id="name" placeholder="Enter your name" />
                    <span class="span1 text-danger" th:if="${#fields.hasErrors('studentName')}" th:errors="*{studentName}"></span>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" th:field="*{studentDob}" class="form-control" id="dob" />
                    <span class="span1 text-danger" th:if="${#fields.hasErrors('studentDob')}" th:errors="*{studentDob}"></span>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                     <div class="form-check-inline">
                        <input type="radio" class="form-check-input" th:field="*{studentGender}" id="gridRadios1" th:value="'Male'" />
                        <label  class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input type="radio" class="form-check-input" th:field="*{studentGender}" id="gridRadios2" th:value="'Female'" />
                        <label  class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                    </div>
                </div>
            </fieldset>
    
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" th:field="*{studentPhone}" class="form-control" id="phone" placeholder="Enter your phone number" />
                    <span class="span1 text-danger" th:if="${#fields.hasErrors('studentPhone')}" th:errors="*{studentPhone}"></span>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select th:field="*{studentEducation}" class="form-select" aria-label="Education" id="education">
                        <option th:value="'Bachelor of Information Technology'" >Bachelor of Information Technology</option>
                        <option th:value="'Diploma in IT'">Diploma in IT</option>
                        <option th:value="'Bachelor of Computer Science'">Bachelor of Computer Science</option>
                    </select>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>	
	             <div class="col-md-6">
	             	<div class="span1 text-danger" th:if="${#fields.hasErrors('courses')}" th:errors="*{courses}"></div>
	             	<div class="form-check-inline col-md-3 mb-3" th:each="course : ${AllCourses}">
	                    <input type="checkbox" class="form-check-input" th:field="*{courses}" id="gridRadios1" th:value="${course.courseId}" />
	                    <label class="form-check-label" for="gridRadios1" th:text="${course.courseName}"></label>
	                </div>
                </div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                        <button type="submit" class="btn btn-secondary">
                            <span>Update</span>
                        </button>
                   <!-- Button trigger modal -->
                    <a th:href="@{/DeleteStudentServlet(studentId=${bean.studentId})}" class="btn btn-danger" >
                       <span>Delete</span> 
                    </a>
                    <button type="button" class="btn btn-info" onclick="location.href = '/SearchStudentServlet'">
                		Back
            		</button>
    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete?
                                </div>
                                <div class="modal-footer">
                                    <a>
                                    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
                                   	</a>
                                    <button type="button" class="btn btn-danger">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
            </form>
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
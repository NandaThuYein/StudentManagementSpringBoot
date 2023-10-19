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
        
        <!-- Data Table css1 js1 -->
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Student List</title>
        <style>
        	#sub_content{
        		padding-right:200px;
        	}
        </style>
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
      <div id="sub_content">
      <form class="row g-3 mt-3 ms-2">
      </form>
	<div class="container">
		<div class="row">
				<div class="col-md-10">
      <table class="table border" id="studentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col" style="color:darkblue;">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details<span style="color:darkblue;" th:text="${msg}"></span><span style="color:red;" th:text="${noStudent}"></span></th>
          </tr>
        </thead>
        <tbody>
	          <tr th:each="data, dataStat : ${studentList}">
	            <td scope="row" th:text="${dataStat.count}">1</td>
	            <td th:text="${data.studentId}"></td>
	            <td th:text="${data.studentName}"></td>
	            <td>[[${data.courses}]]</td>
	            <td>
	              <a th:href="@{/updateStudent(studentId=${data.studentId})}"><button type="submit" class="btn btn-secondary mb-2">See More</button></a> 
	            </td>
	          </tr>
        </tbody>
      </table>
      	</div>
      </div>
      </div>
    </div>
    </div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <!-- JQuery js1 -->
		<script src="https://code.jquery.com/jquery-3.6.1.min.js" type="text/javascript"></script>
		<!-- Data Table css1 js1 -->
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js" type="text/javascript"></script>
		
		<script src="https://cdn.datatables.net/buttons/2.3.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js" type="text/javascript"></script>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js" type="text/javascript"></script>
		
		<script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.html5.min.js" type="text/javascript"></script>
		
		<script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.print.min.js" type="text/javascript"></script>
        <script>
		        $(document).ready(function(){
					$('#studentTable').DataTable({
						        dom: 'Bfrtip',
						        buttons: [
						            'excel', 'pdf', 'print'
						        ]
						    });
				});
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
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link th:href="@{/resources/stylesheets/test.css}" rel="stylesheet" />
        <link th:href="@{https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css}" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>User Search Page</title>
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
        <form class="row g-3 mt-3 ms-2" action="#" th:action="@{/SearchUserServlet}" method="post" th:object="${bean}">
            <div class="col-auto">
                <label for="staticEmail2" class="visually-hidden">User Id</label>
                <input type="text"  th:field="*{userId}" class="form-control" id="staticEmail2" placeholder="User ID" />
            </div>
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">User Name</label>
                <input type="text" th:field="*{userName}" class="form-control" id="inputPassword2" placeholder="User Name" />
            </div>
    
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Search</button>
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-secondary" onclick="location.href = '/UserRegistration';">
                    Add
                </button>
    
            </div>
            <div class="col-auto">
                <button type="reset" class="btn btn-danger mb-3">Reset</button>
            </div>

            <div class="col-auto">
                <a th:href="@{'/ExportServlet/' + ${excel}}" class="btn btn-info mb-3">Excel</a>
            </div>
             <div class="col-auto">
                <a th:href="@{'/ExportServlet/' + ${pdf}}" class="btn btn-success mb-3">PDF</a>
            </div>
        </form>
    
        <table class="table table-striped" id="stduentTable">
            <thead>
                <tr>
                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Details <span style="color:red;" th:text="${error}"></span><span style="color:darkblue;" th:text="${msg}"></span><span style="color:darkblue;" th:text="${succ}"></span></th>
                    
                </tr>
            </thead>
            <tbody th:if="${list != null }">
                <tr th:each="data : ${list}">
                    <td th:text="${data.userId}"></td>
                    <td th:text="${data.userName}"></td>
                <td>
                    <a th:href="@{/setupUpdateUser(id=${data.userId})}" class="btn btn-success">
                        Update
                    </a>
                </td>
                <td>
                	<a th:href="@{/DeleteUserServlet(id=${data.userId})}" class="btn btn-secondary mb-3">
                    	Delete
                    </a>
                </td>
                </tr>
            </tbody>
        </table>
    
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
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
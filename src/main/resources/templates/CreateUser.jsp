<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link th:href="@{/resources/stylesheets/test.css}" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<title> Create User Account </title>
</head>
<body class="login-page-body"> 
    <div class="container-fluid">
            <form action="#" th:action="@{/CreateUserServlet}" method="post" th:object="${bean}">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Create User Account</h2>
                <div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label"></label>
						<div class="col-md-4">
							<h5 style="color:red;" th:text="${error}"></h5>
							<!-- <form:errors path="*" cssClass="errorblock" element="div" />  -->
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">ID</label>
						<div class="col-md-4">
							<input type="text" th:field="*{userId}" class="form-control" id="email" placeholder="Enter your Id" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('userId')}" th:errors="*{userId}"></span>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" th:field="*{userName}" class="form-control" id="email" placeholder="Enter your name" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('userName')}" th:errors="*{userName}"></span>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">Email</label>
						<div class="col-md-4">
							<input type="email" th:field="*{userEmail}" class="form-control" id="email" placeholder="Enter your email" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('userEmail')}" th:errors="*{userEmail}"></span>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="Passowrd" class="col-md-2 col-form-label">Password</label>
						<div class="col-md-4">
							<input type="password" name="userPassword" th:value="${bean.userPassword}" class="form-control" id="name" placeholder="Enter your password" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('userPassword')}" th:errors="*{userPassword}"></span>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="confirmPassword" class="col-md-2 col-form-label">Confirm Password</label>
						<div class="col-md-4">
							<input type="password" name="confPassword" th:value="${bean.confPassword}" class="form-control" id="confirmPassword" placeholder="Enter your confirm password" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('confPassword')}" th:errors="*{confPassword}"></span>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
							<label for="userRole" class="col-md-2 col-form-label">User Role</label>
						<div class="col-md-4">
							<input type="text" th:field="*{userRole}" class="form-control" id="email" th:value="${userRole}" readonly="true" />
							<span class="span1 text-danger" th:if="${#fields.hasErrors('userRole')}" th:errors="*{userRole}"></span>
						</div>
					</div>
            <div class="row mb-2">
                <div class="col-md-4"></div>
                <div class="col-md-6">
                    <input type="submit" class="btn btn-secondary col-md-2" value="Create" />
                    <a th:href="@{/}"><button type="button" class="btn btn-primary col-md-2">Back</button></a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
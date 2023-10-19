<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link th:href="@{/resources/stylesheets/test.css}" rel="stylesheet" />
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p th:text="${error}"></p>
            <h5 style="color:darkblue;" th:text="${succ}"></h5>
          </div>
        </div>
        <form class="login-form" action="#" th:action="@{/UserLoginServlet}" method="post" th:object="${bean}" >
          <input type="text" th:field="*{userName}" placeholder="User Name"/>
          <input type="password" th:field="*{userPassword}" placeholder="Password" />
          <button type="submit">login</button>
          <p class="message">Not registered? <a th:href="@{/CreateUser}">Create an account</a></p>
        </form>
      </div>
    </div>
</body>
</html>
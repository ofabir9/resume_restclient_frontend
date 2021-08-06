<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://code.iconify.design/1/1.0.7/iconify.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" ref="/resources/static/css/style.css" />
</head>
<body>
	<!-- registration modal body -->
		

		
	<!--  -->
	<div class="box-border overflow-hidden bg-gray-400 ">
	  <a class="active float-left block text-black text-center p-7 p-8 text-2xl no-underline hover:bg-gray-700 hover:text-white" href="#home">Home</a>
	  <div class="float-right mr-3">
	  <c:set var="loggedUser" scope="session" value="Abir2"/>
	  
	   <c:choose>
			  <c:when test="${loggedUser!='Abir'}">
			   	<form:form action="/action_page.php" method="post" modelAttribute="userLoginDTO">
				     <form:input class="p-1 mt-3 text-1xl" path="email" type="text" placeholder="Email" name="email" id="email" required="required"></form:input>
				     <form:input class="p-1 mt-3 text-1xl" path="password" type="text" placeholder="Password" name="psw"></form:input>
				     <button class="float-right p-1 mt-3 ml-3 text-1xl text-white bg-green-700 hover:bg-green-900" type="submit">Login</button>
				</form:form>
<%-- 				<form:form action="/action_page.php"> --%>
<!-- 					<button class="float-right p-1 mt-3 ml-3 text-1xl text-white bg-green-700 hover:bg-green-900" type="submit">Register</button> -->
<%-- 				</form:form> --%>
				
				<!-- registration modal button-->
				<!-- Click on Modal Button -->
				<button type="button" class="float-right p-1 mt-3 ml-3 text-1xl text-white bg-green-700 hover:bg-green-900" data-bs-toggle="modal" data-bs-target="#modalForm">
				   	Register
				</button>
				<!-- Modal -->
				<div class="modal fade" id="modalForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				    <div class="modal-dialog">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h5 class="modal-title" id="exampleModalLabel">Employee Registration</h5>
				                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				            </div>
				            <div class="modal-body">
				                <form:form action="/action_page.php" method="post" modelAttribute="userRegistrationDTO">
				                	<div class="mb-3">
				                        <label class="form-label">First name</label>
				                        <form:input path="firstName" type="text" class="form-control" id="firstname" name="firstname" placeholder="First name" />
				                    </div>
				                    <div class="mb-3">
				                        <label class="form-label">Last Name</label>
				                        <form:input path="lastName" type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name" />
				                    </div>
				                    <div class="mb-3">
				                        <label class="form-label">Email Address</label>
				                        <form:input path="email" type="text" class="form-control" id="email" name="email" placeholder="Email" />
				                    </div>
				                    <div class="mb-3">
				                        <label class="form-label">Password</label>
				                        <form:input path="password" type="password" class="form-control" id="password" name="password" placeholder="Password" />
				                    </div>
				                    <div class="mb-3">
				                        <label class="form-label">Repeat Password</label>
				                        <form:input path="repeatPassword" type="password" class="form-control" id="repeatPassword" name="repeatPassword" placeholder="Repeat Password" />
				                    </div>
				                    
				                    <div class="modal-footer d-block">
				                        
				                        <button type="submit" class="btn btn-warning float-end">Sign up</button>
				                    </div>
				                </form:form>
				            </div>
				        </div>
				    </div>
				</div>	
				<!--  -->
				
			</c:when>
			<c:otherwise>
				  <form action="/action_page.php">
				     <h1>${loggedUser}</h1>
				     <button class="float-right p-1 mt-3 ml-3 text-1xl bg-red-500 hover:bg-red-700" type="submit">LogOut</button>
				   </form>
			</c:otherwise>
		</c:choose>
	  </div>
	</div>
</body>
</html>
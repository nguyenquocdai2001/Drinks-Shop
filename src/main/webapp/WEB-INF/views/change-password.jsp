<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	 <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Change Password Page</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-light ">
            <div class="container">
                <a href="/products/getAllProducts"><img src="https://mylogo.vn/wp-content/uploads/2018/09/mau-logo-tra-sua-dep-va-de-thuong-29.jpg" class="logo" height="36" alt="Responsive image"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link text-dark" href="/products/getAllProducts">Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark" href="/Profile/${idUser.id}">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark" href="/shopping-cart/views">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark" href="/shopping-cart/getAllReceipt">History </a>
                    </li>
                    </ul>
                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          Hi, <c:out value = "${helloUser}"/>
                        </button>
                        <div class="dropdown-menu text-dark" aria-labelledby="dropdownMenuButton">
                        	<a class="dropdown-item text-dark" href="/changepassword">Change Password</a>
                          	<a class="dropdown-item text-dark" href="/logout">Log out</a>
                        </div>
                        
                      </div>
                </div>
            </div>
        </nav>
        
	<div class="container" style="margin-top: 10px;">
		<div class="row bg-light"
			style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
			<div class="col-sm-12">
				<header class="text-center py-3 rounded ">
					<h1 class="display-5 font-weight-bold text-secondary text-center">Change Password</h1>
				</header>
				<form action="checkChangePassword" method="post">
							<div class="form-group">
								<label class="text-dark ">Username</label> 
								<input type="text" class="form-control" name="username" placeholder="Enter Username" id="username" value="">
							</div>
							
							<div class="form-group">
								<label class="text-dark ">Old password</label> 
								<input type="password"
									class="form-control" name="OldPassword" placeholder="Enter Old Password">
							</div>
							
							<div class="form-group">
								<label class="text-dark ">Email</label> 
								<input type="text" class="form-control" name="email" placeholder="Enter Email" id="email" value="">
							</div>
							
							<div class="form-group">
								<label class="text-dark ">New password</label> 
								<input type="password"
									class="form-control" name="NewPassword" placeholder="Enter New Password">
							</div>
							
							<div class="form-group">
								<label class="text-dark ">Confirm New password</label> 
								<input type="password"
									class="form-control" name="ConfirmNewPassword" placeholder="Enter Confirm New Password">
							</div>
							
					<c:if test='${not empty message}'>  
								<div
									class="alert alert-${message.type} alert-dismissible show"
									role="alert">
									${message.content}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
					</c:if>
					
					<div class="form-group">
						<button type="submit" class="btn btn-success">Save</button>
						<button type="reset" class="btn btn-secondary">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


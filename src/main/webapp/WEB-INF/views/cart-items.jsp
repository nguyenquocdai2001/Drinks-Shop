<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        
         <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
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
        
	<div class="container">
		<header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
			<h1 class="display-5 font-weight-bold">DRINK CART</h1>
		</header>
		
		<div class="rows">
			<div class="col-sm-12">
				<table class="table table-striped table-hover text-center">
					<thead>
						<tr class="text-light bg-dark">
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${CART_ITEMS}">
							<form action="/shopping-cart/update" method="post">
								<input type="hidden" name="id" value="${item.productID}"/>
								<tr>
									<td>${item.productID }</td>
									<td>${item.name }</td>
									<td>${item.price}</td>
									<td><input name="qty" value="${item.qty}"
										onblur="this.form.submit()" style="width: 50px;"></td>
									<td>${item.price*item.qty}</td>
									<td><a class="btn btn-dark btn-sm"
										href="/shopping-cart/delete/${item.productID }">Remove</a></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>
				</table>
				<p class="text-danger font-weight-bold">Total Amount: ${TOTAL}</p>
				<hr />
				<a class="btn btn-dark btn-sm" href="/shopping-cart/clear">Clear Cart</a> 
				<a class="btn btn-dark btn-sm" href="/products/getAllProducts">Add more</a>
				<a class="btn btn-dark btn-sm" href="/shopping-cart/checkout">Check out</a>	
			</div>
		</div>

	</div>
</body>
</html>
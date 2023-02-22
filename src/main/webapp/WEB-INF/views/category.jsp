<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">    
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
    
    <title>Category list</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a href="/list"><img src="https://mylogo.vn/wp-content/uploads/2018/09/mau-logo-tra-sua-dep-va-de-thuong-29.jpg" class="logo" height="36" alt="Responsive image"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/list">Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/getUserByUserID/${idUser.id}">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/list">List User</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/categories">Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/shopping-cart/getAllReceiptAdmin">Receipt</a>
                    </li>
                    </ul>
                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          Hi, <c:out value = "${helloUser}"/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                          <a class="dropdown-item" href="/logout">Log out</a>
                        </div>
                      </div>
                </div>
            </div>
        </nav>
        
	<div class="container">
		<header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
			<h1 class="display-5 font-weight-bold ">CATEGORY LIST</h1>
		</header>
		<p>
            <a class="btn btn-dark" href="/products/insertProduct">New Product</a>
    		<a class="btn btn-dark" href="/categories/insertCategory">New Category</a>
        </p>
        
        <table class="table table-striped table-borderless table-hover text-center">
        	<thead>
                <tr class="text-light bg-dark">
                	<th>ID</th>
		            <th>Name</th>
		            <th>Description</th>
		            <th>Actions</th>
                </tr>
            </thead>
            
            <tbody>
	            <c:forEach var="category" items="${categories}">
		            <tr>
		            		<td>${category.getCategoryID()}</td>
			                <td>${category.getCategoryName()}</td>
			                <td>${category.getDescription()}</td>
			                <td>
			                  <a class="btn btn-dark btn-sm" href="/products/getProductsByCategoryID/${category.getCategoryID()}">
			                      Detail
			                  </a>
			                  <a class="btn btn-dark btn-sm" href="../../categories/changeCategory/${category.getCategoryID()}">
			                      Edit
			                  </a>
			                </td>
		            </tr>
	            </c:forEach> 
            </tbody>
        </table>
	</div>
</body>
</html>

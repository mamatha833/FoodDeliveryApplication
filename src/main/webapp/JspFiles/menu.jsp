<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Menu,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Styles/menu.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<nav>
		<div class="logo">TapFoods</div>
		<div class="search-bar">
			<input type="text" placeholder="Search menu items...">
			<button>
				<i class="fas fa-search"></i>
			</button>
		</div>
		<ul class="nav-links">
			<li><a href="home">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/signOut">Sign
					Out</a></li>
			<li><a href="JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
			<li><a href="${pageContext.request.contextPath}/cart"><i
					class="fas fa-shopping-cart"></i></a></li>
		</ul>
	</nav>

	<section class="menu-header">
		<h1>Our Menu</h1>
		<p>Discover our delicious selection of dishes prepared with the
			finest ingredients</p>
	</section>

	<main class="menu-container">
		<div class="menu-grid">
			<%
			
			
			int resId=(Integer)request.getAttribute("restaurantId");
			
                List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
                if (menuList != null) {
                    for (Menu menuItem : menuList) {
            %>
			<div class="menu-item">
				<img src="<%= menuItem.getImagePath() %>"
					alt="<%= menuItem.getItemName() %>">
				<div class="item-info">
					<h3><%= menuItem.getItemName() %></h3>
					<p class="description"><%= menuItem.getDescription() %></p>
					<div class="rating">
						<i class="fas fa-star"></i> <span><%= menuItem.getRatings() %></span>
					</div>
					<div class="price-status">
						<span class="price">₹<%= menuItem.getPrice() %></span> <span
							class="status <%= menuItem.getIsAvailable() ? "available" : "out-of-stock" %>">
							<%= menuItem.getIsAvailable() ? "Available" : "Out of Stock" %>
						</span>
					</div>
					<form action="${pageContext.request.contextPath}/cart" method="post">
						 <input type="hidden" name="action" value="add">
						 <input type="hidden" name="quantity" value="1">
						 <input type="hidden" name="restaurantId" value=<%=menuItem.getRestaurantId()%>>
						 <input type="hidden" name="menuId" value=<%=menuItem.getMenuId()%>>
						 <button class="add-to-cart"<%= !menuItem.getIsAvailable() ? "disabled" : "" %>>Add to Cart</button>
					</form>

				</div>
			</div>
			<%
                    }
                } else {
            %>
			<p>No menu items available.</p>
			<%
                }
            %>
		</div>
	</main>

	<footer class="footer">
		<div class="footer-container">
			<div class="footer-logo">
				<h2>TapFoods</h2>
			</div>
			<div class="footer-links">
                <h3>Quick Links</h3>
                <ul>
                    <li><a href="home">Home</a></li>
                    <li><a href="JspFiles/signUp.jsp">Sign Up</a></li>
                    <li><a href="JspFiles/signIn.jsp">Sign In</a></li>
                    <li><a href="${pageContext.request.contextPath}/cart">Cart</a></li>
                </ul>
            </div>
			<div class="footer-contact">
				<h3>Contact Us</h3>
				<p>Email: support@tapfoods.com</p>
				<p>Phone: +1-800-FOODIE</p>
			</div>
		</div>
		<p class="footer-bottom">&copy; 2025 TapFoods. All rights
			reserved.</p>
	</footer>
</body>
</html>

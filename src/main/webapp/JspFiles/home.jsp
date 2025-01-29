<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurants</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav>
        <div class="logo">TapFoods</div>
        <div class="search-bar">
            <input type="text" placeholder="Search for restaurants...">
            <button><i class="fas fa-search"></i></button>
        </div>
        <ul class="nav-links">
            <li><a href="home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/signOut">Sign Out</a></li>
            <li><a href="${pageContext.request.contextPath}/JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
            <li><a href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i></a></li>
        </ul>
    </nav>

    <section class="welcome-message">
        <h1>Welcome to TapFoods!</h1>
        <p>Your one-stop destination for discovering the best restaurants and delicious cuisines. Order now and enjoy your meal from the comfort of your home!</p>
    </section>

    <main>
        <div class="restaurant-grid">
            <!-- Dynamically Generate Restaurant Cards -->
            <%
                List<Restaurant> allRestaurants = (List<Restaurant>) request.getAttribute("allRestaurants");
                if (allRestaurants != null) {
                    for (Restaurant res : allRestaurants) {
            %>
            <div class="restaurant-card">
                <img src="<%= res.getImagePath() %>" alt="<%= res.getName() %>">
                <div class="restaurant-info">
                    <h3><%= res.getName() %></h3>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <span><%= res.getRating() %></span>
                    </div>
                    <div class="delivery-time">
                        <i class="fas fa-clock"></i>
                        <span><%= res.getEta() %> mins</span>
                    </div>
                    <p class="cuisine"><%= res.getCusineType() %></p>
                    <span class="status <%= res.getIsActive() ? "available" : "closed" %>">
                        <%= res.getIsActive() ? "Available" : "Closed" %>
                    </span>
                   <button class="view-menu" onclick="window.location.href='MenuServlet?restaurantId=<%= res.getRestaurantId() %>'">View Menu</button>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <p>No restaurants available.</p>
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
        <p class="footer-bottom">&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>

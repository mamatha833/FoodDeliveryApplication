<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.daoImplementation. UserDAOImplementation,com.tap.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav>
        <div class="logo">TapFoods</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
            <li><a href="${pageContext.request.contextPath}/signOut">Sign Out</a></li>
        </ul>
    </nav>
    <%
    int userId = (Integer)session.getAttribute("userId");
    UserDAOImplementation udi = new UserDAOImplementation();
    User user = udi.getUser(userId);
    
    %>

    <main class="profile-container">
        <div class="profile-card">
            <h2>User Profile</h2>
            <h4><%= request.getAttribute("message") != null ? "<p style='color:green;font-family:cursive;text-align:center'>" + request.getAttribute("message") + "</p>" : "" %></h4>
                 <h4><%= request.getAttribute("error") != null ? "<p style='color:red;font-family:cursive;text-align:center'>" + request.getAttribute("error") + "</p>" : "" %></h4>
            <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" value=<%= user.getEmail() %> disabled="disabled">
                </div>
            
            
            <form class="profile-form" action = "${pageContext.request.contextPath}/profile" method = "post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name = "username"value=<%= user.getUsername()%> disabled="disabled">
                </div>

                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" value=<%= user.getName() %> required>
                </div>
                
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" value=<%= user.getPhone() %> required>
                </div>
                
           
                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea id="address" rows="3" name="address" required><%= user.getAddress() %></textarea>
                </div>

                <div class="form-group">
                    <label for="current-password">Current Password</label>
                    <input type="password" id="current-password" name="current-password" required>
                </div>

                <div class="form-group">
                    <label for="new-password">New Password</label>
                    <input type="password" id="new-password" name="new-password" required>
                </div>
				<div class="button-group">
                    <button type="submit" class="save-btn">Save Changes</button>
                    <button type="reset" class="cancel-btn"><a href ="${pageContext.request.contextPath}/JspFiles/profile.jsp">Cancel</a></button>
                </div>
            </form>
        </div>
    </main>

    <footer class="footer">
        <p class="footer-bottom">&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>
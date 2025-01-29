<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/signUp.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    

    <main class="signup-container">
        <div class="signup-card">
            <h2>Create Account</h2>
            <h4><%= request.getAttribute("error") != null ? "<p style='color:red;text-align:center'>" + request.getAttribute("error") + "</p>" : "" %></h4>
       
           
            <form class="signup-form" action="${pageContext.request.contextPath}/signUpServlet" method="POST">
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" required></textarea>
                </div>

                <button type="submit" class="signup-btn">Sign Up</button>
            </form>
            <p class="signin-link">Already have an account? <a href="${pageContext.request.contextPath}/JspFiles/signIn.jsp">Sign In</a></p>
        </div>
    </main>

    <footer class="footer">
        <p class="footer-bottom">&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>

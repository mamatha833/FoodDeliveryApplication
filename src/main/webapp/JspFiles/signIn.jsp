<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/signIn.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    

    <main class="signin-container">
    
        <div class="signin-card">
            <h2>Welcome Back</h2>
            <h4><%= request.getAttribute("error") != null ? "<p style='color:red;text-align:center'>" + request.getAttribute("error") + "</p>" : "" %></h4>
             <h4><%= request.getAttribute("message") != null ? "<p style='color:green;text-align:center'>" + request.getAttribute("message") + "</p>" : "" %></h4>
            <form class="signin-form" action="${pageContext.request.contextPath}/signInServlet" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="signin-btn">Sign In</button>
            </form>
            <p class="signup-link">Don't have an account? <a href="${pageContext.request.contextPath}/JspFiles/signUp.jsp">Sign Up</a></p>
        </div>
       
    </main>
    <footer class="footer">
        <p class="footer-bottom">&copy; 2025 TapFoods. All rights reserved.</p>
    </footer>
</body>
</html>
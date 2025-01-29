<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.tap.model.CartItem, java.util.Map , java.util.Collection,com.tap.model.Cart"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Styles/cart.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<nav>
		<div class="logo">TapFoods</div>
		<ul class="nav-links">
			<li><a href="home">Home</a></li>
			<li><a href="JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
			<li><a href="${pageContext.request.contextPath}/orderHistory">My Orders</a></li>
		</ul>
	</nav>


	<main class="cart-container">

		<h1>Your Cart</h1>

		<div class="cart-items">

			<%
			Cart cart =(Cart)session.getAttribute("cart");
			if(cart != null){
				
			 Map<Integer, CartItem> cartItems = cart.getItems();
			  if(!cartItems.isEmpty()){

				Collection<CartItem> items = cartItems.values();

				for (CartItem item : items) {
			%>

			<div class="cart-item">
				<img src="<%=item.getImagePath()%>" alt="Pizza">
				<div class="item-details">
					<h3></h3>
					<p class="restaurant"><%=item.getItemName()%></p>
					<div class="quantity">
					
					
					<form action="cart" method="post">
					    <input type="hidden" name="action" value="update">
					    <input type="hidden" name="quantity" value="<%=item.getQuantity() - 1%>">
					    <input type="hidden" name="restaurantId" value="<%=item.getRestaurantId() %>">
					    <input type="hidden" name="menuId" value=<%=item.getMenuId()%>>
					    <button class="qty-btn">-</button>
					</form>
					
						
                    <span><%=item.getQuantity() %></span>						
						
				
					<form action="cart" method="post">
					    <input type="hidden" name="action" value="update">
					    <input type="hidden" name="quantity" value="<%=item.getQuantity() + 1%>">
					    <input type="hidden" name="restaurantId" value="<%=item.getRestaurantId() %>">
					    <input type="hidden" name="menuId" value=<%=item.getMenuId()%>>
					    <button class="qty-btn">+</button>
					</form>
					
					
					</div>
				</div>
				<div class="price">₹<%=item.getPrice()*item.getQuantity()%></div>
				
				
				
					<form action="cart" method="post">
					    <input type="hidden" name="action" value="remove">
					    <input type="hidden" name="restaurantId" value="<%=item.getRestaurantId() %>">
					    <input type="hidden" name="menuId" value=<%=item.getMenuId()%>>
					   	
				<button class="remove-btn">
					<i class="fas fa-trash"></i>
				</button>
					</form>
				
			</div>


			<%
			}
			%>


			<div class="add-more-container">
				<form action="${pageContext.request.contextPath}/MenuServlet"method="post">
					<input type="hidden" name="restaurantId"value="<%=request.getParameter("restaurantId")%>">
					<button class="add-more-btn" type="submit">
						<i class="fas fa-plus"></i> Add More Items
					</button>
				</form>
			</div>


			<div class="cart-summary">
				<div class="summary-item">
					<span>Subtotal:</span> 
					<span>₹<%= (float)cart.getSubTotalPrice() %></span>
				</div>
				<div class="summary-item">
					<span>Delivery Fee:</span> <span>₹5.00</span>
				</div>
				<div class="summary-item total">
					<span>Total:</span> <span>₹<%= (float)cart.getSubTotalPrice() + 5 %></span>
				</div>
				
				<form action="JspFiles/checkOut.jsp">
				 <button class="proceed-btn">Proceed to Checkout</button>
				
				</form>
				
			</div>
			</div>
	</main>


	<%
	}
			  
			  else{
				 %>
				 <p class="empty">Your cart is empty !!</p>

			 <% }
			  
			}
		else{
			
	%>

	<p class="empty">Your cart is empty !!</p>

	<%
	}
	%>


</body>
</html>
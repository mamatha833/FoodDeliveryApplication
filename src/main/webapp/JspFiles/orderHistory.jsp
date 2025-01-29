<%@page import="com.tap.daoImplementation.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "com.tap.model.*,java.util.List,com.tap.dao.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order History</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Styles/orderHistory.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<nav>
		<div class="logo">FoodieHub</div>
		<ul class="nav-links">
			<li><a href="home">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
			<li><a href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i></a></li>
		</ul>
	</nav>

	<main class="order-history-container">
		<h1>Order History</h1>

		<div class="order-list">
		
			
	<%
        List<Order> orders = (List<Order>)request.getAttribute("orders");
	    if(!orders.isEmpty()){
	    	
	    	for(Order order : orders){
	    		
	    	int restaurantId = order.getRestaurantId();
	    	RestaurantDAO rdi = new RestaurantDAOImplementation();
	    	Restaurant restaurant = rdi.getRestaurant(restaurantId);
	    	
	    	OrderItemDAO odi = new OrderItemDAOImplementation();
	    	List<OrderItem> orderItems = odi.getOrderItemByOrderId(order.getOrderId());
	    	
   
   %>
		
			<div class="order-item">
				<div class="order-header">
					<div class="order-info">
						<h3>Order #ORD100<%=order.getOrderId()%></h3>
						<p class="order-date"><%=order.getOrderDate() %></p>
					</div>
					<div class="order-status delivered">
						<i class="fas fa-check-circle"></i> <%=order.getStatus() %>
					</div>
				</div>
				<div class="order-summary">
				
					<div class="restaurant-info">
						<p class="restaurant-name"> <i class="fas fa-utensils"></i><%=restaurant.getName() %></p>
						<p class="items-count"> items : <%=orderItems.size() %></p>
					</div>
					
					<div class="order-total">
						<p>Total: ₹<%=order.getTotalAmount()%></p>
						<form action = "JspFiles/orderDetails.jsp" method ="post">
						<input type="hidden" name="orderId" value=<%=order.getOrderId()%>>
						<button class="view-details-btn">View Details</button>
						</form>
						
					</div>
					
				</div>
			</div>	
			
			<%
			
	    	}
	    }
	    else{
	    	
	    	
			%>
		
			<p class="empty">Looks like you haven't placed an order yet!!</p>
	
	<%
	    }
	
		%>

		</div>
		
		
	</main>
	
	
	
	
			
			
	


</body>
</html>
<%@page import="com.tap.dao.*"%>
<%@page import="com.tap.daoImplementation.*"%>
<%@ page import = "com.tap.model.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/orderDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav>
        <div class="logo">FoodieHub</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
            <li><a href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i></a></li>
        </ul>
    </nav>

    <main class="order-details-container">
        <div class="back-button">
            <a href="${pageContext.request.contextPath}/orderHistory"><i class="fas fa-arrow-left"></i> Back to Orders</a>
        </div>
        
       
        <%
         int  orderId = Integer.parseInt(request.getParameter("orderId"));
         OrderDAO orderdi = new OrderDAOImplementation();
         Order order = orderdi.getOrder(orderId);
         
         RestaurantDAO rdi = new RestaurantDAOImplementation();
	     Restaurant restaurant = rdi.getRestaurant(order.getRestaurantId());
	     
	     OrderItemDAO orderItemdi = new OrderItemDAOImplementation();
	     List<OrderItem> orderItems = orderItemdi.getOrderItemByOrderId(orderId);
	    	
        
        %>  
        
        <div class="order-details-card">
            <div class="order-header">
                <div class="order-info">
                    <h2>Order #ORD100<%=order.getOrderId()%></h2>
                    <p class="order-date"><%=order.getOrderDate() %></p>
                    <p class="restaurant-name"><%=restaurant.getName() %></p>
                </div>
                <div class="order-status delivered">
                    <i class="fas fa-check-circle"></i> <%=order.getStatus() %>
                </div>
            </div>

            <div class="order-items">
                <h3>Order Items</h3>
            
            
            <%
               for(OrderItem orderItem : orderItems){
            	   
            	    MenuDAO mdi = new MenuDAOImplementation();
            	    Menu menu = mdi.getMenu(orderItem.getMenuId());
            	    
            	   
            
            %>
                
                <div class="item">
                    <img src=<%=menu.getImagePath()%> alt="Pepperoni Pizza" class="item-image">
                    <div class="item-content">
                        <div class="item-info">
                            <h4><%=menu.getItemName() %></h4>
                            <p class="item-description">₹<%=menu.getPrice() %></p>
                        </div>
                        <div class="item-details">
                            <span class="quantity">x<%=orderItem.getQuantity()%></span>
                            <span class="price">₹<%=menu.getPrice()*orderItem.getQuantity()%></span>
                        </div>
                    </div>
                </div>
                
                <%
               }
                %>

                
            </div>

            <div class="order-summary">
                <div class="summary-item">
                    <span>Subtotal</span>
                    <span>₹<%=order.getTotalAmount()-5 %></span>
                </div>
                <div class="summary-item">
                    <span>Delivery Fee</span>
                    <span>₹5.00</span>
                </div>
                <div class="summary-item total">
                    <span>Total</span>
                    <span>₹<%=order.getTotalAmount() %></span>
                </div>
            </div>

            <div class="delivery-info">
                <h3>Delivery Information</h3>
                <p><i class="fas fa-map-marker-alt"></i><%=order.getAddress()%></p>
                <p><i class="fas fa-phone"></i><%=order.getPhone() %></p>
                <p><i class="fas fa-comment"></i> Leave at door</p>
            </div>
        </div>
    </main>
</body>
</html>
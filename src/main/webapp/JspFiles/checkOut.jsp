<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.tap.model.Cart" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/checkOut.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav>
        <div class="logo">TapFoods</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/JspFiles/profile.jsp"><i class="fas fa-user"></i></a></li>
            <li><a href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i></a></li>
        </ul>
    </nav>
    <main class="checkout-container">
        <h1>Checkout</h1>
        <form action="${pageContext.request.contextPath}/checkout" method="post">
            <div class="checkout-form">
                <section class="delivery-address">
                    <h2>Delivery Address</h2>
                    <div class="form-group">
                        <input type="text" placeholder="Full Name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="Street Address" name="street" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <input type="text" placeholder="City" name="city" required>
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="State" name="state" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <input type="text" placeholder="ZIP Code" name="zip" required>
                        </div>
                        <div class="form-group">
                            <input type="tel" placeholder="Phone Number" name="phone" required>
                        </div>
                    </div>
                </section>

                <section class="payment-method">
                    <h2>Payment Method</h2>
                    <div class="payment-options">
                        <div class="payment-option">
                            <input type="radio" id="card" name="payment" value="card" checked>
                            <label for="card">Credit/Debit Card</label>
                        </div>
                        <div class="payment-option">
                            <input type="radio" id="upi" name="payment" value="upi">
                            <label for="upi">UPI</label>
                        </div>
                        <div class="payment-option">
                            <input type="radio" id="cod" name="payment" value="cod">
                            <label for="cod">Cash on Delivery</label>
                        </div>
                    </div>

                    <div id="card-form" class="payment-form card-details">
                        <div class="form-group">
                            <input type="text" placeholder="Card Number" maxlength="16" >
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <input type="text" placeholder="MM/YY" maxlength="5" >
                            </div>
                            <div class="form-group">
                                <input type="text" placeholder="CVV" maxlength="3" >
                            </div>
                        </div>
                    </div>

                    <div id="upi-form" class="payment-form upi-details" style="display: none;">
                        <div class="upi-options">
                            <div class="upi-option">
                                <input type="radio" id="gpay" name="upi-method" value="gpay">
                                <label for="gpay">Google Pay</label>
                            </div>
                            <div class="upi-option">
                                <input type="radio" id="phonepe" name="upi-method" value="phonepe">
                                <label for="phonepe">PhonePe</label>
                            </div>
                            <div class="upi-option">
                                <input type="radio" id="paytm" name="upi-method" value="paytm">
                                <label for="paytm">Paytm</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Enter UPI ID (example@upi)">
                        </div>
                    </div>
                </section>
                
                <%
                
                Cart cart =(Cart)session.getAttribute("cart");
                
                %>

                <section class="order-summary">
                    <h2>Order Summary</h2>
                    <div class="summary-row">
                        <span>Subtotal</span>
                        <span>₹<%= (float)cart.getSubTotalPrice() %></span>
                    </div>
                    <div class="summary-row">
                        <span>Delivery Fee</span>
                        <span>₹5.00</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total</span>
                        <span>₹<%= (float)cart.getSubTotalPrice() + 5 %></span>
                    </div>
                </section>

                <button class="place-order-btn">Place Order</button>
            </div>
        </form>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const paymentOptions = document.querySelectorAll('input[name="payment"]');
            const cardForm = document.getElementById('card-form');
            const upiForm = document.getElementById('upi-form');

            function togglePaymentForms(selectedValue) {
                cardForm.style.display = selectedValue === 'card' ? 'block' : 'none';
                upiForm.style.display = selectedValue === 'upi' ? 'block' : 'none';
            }

            paymentOptions.forEach(option => {
                option.addEventListener('change', function () {
                    togglePaymentForms(this.value);
                });
            });

            // Initialize with the default checked option
            togglePaymentForms(document.querySelector('input[name="payment"]:checked').value);
        });
    </script>
</body>
</html>

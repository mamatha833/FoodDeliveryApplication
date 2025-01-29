# 🍔 Food Delivery Application

A **Java-based** web application that allows users to explore restaurants, order food online, and manage their orders.

## ✨ Features
- ✔️ **User Authentication**: Sign-up, sign-in, and sign-out functionality.
- ✔️ **Home Page**: Displays a list of different restaurants.
- ✔️ **Menu Page**: View food items of a selected restaurant.
- ✔️ **Add to Cart**: Users can add items to their cart.
- ✔️ **Checkout & Order Confirmation**: Proceed to checkout and confirm the order.
- ✔️ **Order History**: Users can view past orders with all details.
- ✔️ **Profile Management**: Users can edit their profile information.

## 🔧 Technologies Used
- **Frontend**: JSP, CSS
- **Backend**: Java (Servlets, JDBC)
- **Database**: MySQL
- **Server**: Apache Tomcat

## 📝 Project Structure
```
FoodDeliveryApplication/
├── src/main/java/
│   ├── com.tap.model/           # Model classes (User, Order, Restaurant, etc.)
│   ├── com.tap.dao/             # DAO interfaces
│   ├── com.tap.daoImplementation/  # DAO implementation classes
│   ├── com.tap.utility/         # Database connection utility
│   ├── com.tap.servlets/        # Servlets handling requests
│
├── webapp/
│   ├── assets/                  # Images, animation video, sound files
│   ├── jspFiles/                # All JSP pages
│   ├── styles/                  # CSS files
│   ├── WEB-INF/
│   │   ├── lib/                 # MySQL Connector JAR
│   │   ├── web.xml              # Deployment descriptor
```

## ⚡ How to Run the Project
Follow these steps to set up and run the project on your system:

### 🛠 Prerequisites
- Install **Java JDK** (11 or higher)
- Install **Apache Tomcat** (9 or higher)
- Install **MySQL Server**
- Install **Eclipse IDE** (or any other IDE that supports Java Web Development)
- Add **MySQL Connector JAR** to the `lib/` folder

### 📝 Step 1: Clone the Repository
```sh
git clone https://github.com/your-username/FoodDeliveryApplication.git
```

### 🔧 Step 2: Set Up the MySQL Database
1. Open **MySQL Workbench** or any MySQL client.
2. Create a new database:
   ```sql
   CREATE DATABASE FoodDeliveryDB;
   ```
3. Switch to the new database:
   ```sql
   USE FoodDeliveryDB;
   ```
4. Create the required tables:
   ```sql
   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100) UNIQUE,
       password VARCHAR(255),
       phone VARCHAR(15),
       address TEXT
   );
   
   CREATE TABLE restaurants (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       location VARCHAR(255)
   );
   
   CREATE TABLE menu (
       id INT AUTO_INCREMENT PRIMARY KEY,
       restaurant_id INT,
       name VARCHAR(100),
       price DECIMAL(10,2),
       FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
   );
   
   CREATE TABLE orders (
       id INT AUTO_INCREMENT PRIMARY KEY,
       user_id INT,
       total_price DECIMAL(10,2),
       status VARCHAR(50),
       order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```

### 🛠 Step 3: Import the Project in Eclipse
1. Open **Eclipse IDE**.
2. Click **File → Import → Existing Projects into Workspace**.
3. Select the **FoodDeliveryApplication** folder.
4. Click **Finish**.

### 🌐 Step 4: Configure Tomcat Server
1. In Eclipse, go to **Window → Show View → Servers**.
2. Right-click and **Add New Server** → Select **Apache Tomcat**.
3. Set the **Tomcat installation directory**.
4. Click **Finish**.

### 🚀 Step 5: Run the Application
1. Right-click the project → **Run As → Run on Server**.
2. Choose **Apache Tomcat** and click **Finish**.
3. Open your browser and visit:
   ```
   http://localhost:8080/FoodDeliveryApplication/
   ```

## 💡 Additional Notes
- Make sure MySQL is **running** before launching the project.
- Update `DatabaseConnection.java` in `com.tap.utility` with your **MySQL credentials**:
  ```java
  String url = "jdbc:mysql://localhost:3306/FoodDeliveryDB";
  String user = "root";
  String password = "your_password";
  ```
- If you face any errors, check the **Tomcat logs** in Eclipse.

## 💼 Contributing
Pull requests are welcome! If you find any issues, feel free to open an **issue**.

## 💌 Contact
For any queries, reach out at [your-email@example.com](mailto:your-email@example.com).




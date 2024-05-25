# Point of Sale System
## Overview
This Point of Sale (POS) System is a Java-based application with a graphical user interface (GUI) that allows users to manage food and drink orders in a restaurant setting. The system includes features for adding items to an order, viewing the total price, and generating sales reports. The application uses MySQL for database management to store order and sales data.

## Features
1. Food Menu: Allows users to add food items to an order.
2. Drinks Menu: Allows users to add drink items to an order.
3. Order Summary: Displays the current order list and the total price.
4. Place Order: Saves the current order to the database.
5. Cancel Order: Clears the current order list.
6. Sales Report: Generates a report of all sales, including item details and total sales amount.
  
## Technologies Used
Java: Programming language used for developing the application.
Swing/AWT: Used for building the graphical user interface.
MySQL: Database management system for storing orders and sales data.
JDBC: Java Database Connectivity for connecting the Java application to the MySQL database.

## Prerequisites
Java Development Kit (JDK): Ensure JDK is installed on your system.
MySQL Server: Ensure MySQL server is installed and running.
MySQL Connector/J: JDBC driver for MySQL.

# Setup Instructions
## 1. Database Setup
1. Create Database: Create a database named salessystem in your MySQL server.

2. Create Tables: Create the necessary tables (orders and sales) using the following SQL commands:
Ex. 

CREATE TABLE orders (
    orderNum INT AUTO_INCREMENT PRIMARY KEY,
    total FLOAT NOT NULL
);

CREATE TABLE sales (
    saleID INT AUTO_INCREMENT PRIMARY KEY,
    orderNum INT,
    itemName VARCHAR(100),
    quantity INT,
    subtotal FLOAT,
    FOREIGN KEY (orderNum) REFERENCES orders(orderNum)
);


## 2. Application Setup
1. Clone the Repository: Clone this repository to your local machine.
2. Import Project: Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Add MySQL Connector/J: Ensure that the MySQL Connector/J JAR file is added to your project's build path.
4. Update Database Credentials: Update the database connection credentials in the System class.

## 3. Running the Application
1. Run Main Class: Run the PointOfSale main class to start the application.

# Usage
## Adding Items to Order
- Food Menu: Click the "Food Menu" button and select the desired food items.
- Drinks Menu: Click the "Drinks Menu" button and select the desired drink items.
## Managing Orders
- Place Order: Click the "Place Order" button to save the current order to the database.
- Cancel Order: Click the "Cancel Order" button to clear the current order list.
## Viewing Sales Report
- Sales Report: Click the "Sales Report" button to generate and view a report of all sales, including item details and total sales amount.
  

# Authors
Flores, Toyogon, Uyguangco, Ylanan

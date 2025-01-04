# ProductInventoryAndOrderManagement
This project provides a set of REST APIs for managing products, stock levels, and sales orders for a business. It includes APIs for:

* Product Management: Create, update, list, and delete products.
* Order Management: Create orders and retrieve order details.
## Prerequisites
Before running this project, make sure you have the following software installed:

* Java 17 
* Maven 
* MySQL Database
* Postman

## Database Setup
* Install MySQL, ensure that the MySQL server is running.
* Create Database:
  * Open MySQL Workbench or any MySQL client and run the SQL command to create a new database.
* Set Up Database Configuration:
  * Update the application.properties file in the src/main/resources folder to configure the database connection.
* Database Tables:
  * The application will automatically create the required tables (such as products, orders, etc.) based on the entity classes defined in the project, thanks to the spring.jpa.hibernate.ddl-auto=update property.
## Running the Application
* Clone the Repository:
  * Clone the project repository to your local machine.
* Build the Application:
  * Run the following command to build the application using Maven: mvn clean install
* Run the Application:
  * Start the Spring Boot application.
  * The application will start running on http://localhost:8080
* Access the API Endpoints:
  * You can now test the following API endpoints using Postman or any other API testing tool.

## Product Management APIs:

* POST /api/products - Create a new product
* PATCH /api/products/{id} - Update an existing product
* GET /api/products - List all products (with search support and pagination)
* DELETE /api/products/{id} - Soft delete a product (mark as inactive) 
## Order Management APIs:

* POST /api/orders - Create a new order
* GET /api/orders/{id} - Get the details of a specific order
* GET /api/orders - List all orders(paginated)

## Dependencies
* Spring Boot: For building the RESTful backend.
* Spring Data JPA: For interacting with the MySQL database.
* MySQL: Database for storing product and order data.
* Lombok: For reducing boilerplate code in model classes.

## API Documentation(Postman)
* https://documenter.getpostman.com/view/30333618/2sAYJ99xg7#82c5b7b3-d99b-4ce3-9cf0-198e053c9f7a

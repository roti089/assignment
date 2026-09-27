PIMS - Pharmacy Inventory Management System
1. Project Description
PIMS is a desktop application programmed in Java for assisting the management of medicine, suppliers, inventory, users, sales, and business reports in a pharmacy.

The application offers different roles where the administrator manages medicine, suppliers and users whereas the cashier manages the sale using the point of sale (POS) system.

2. Technology Used
Java JDK 17

Java Swing

MySQL

MySQL Connector/J

IntelliJ IDEA

JDBC

3. System Requirements
Prior to executing PIMS application, make sure that the following software is installed on the machine:

Windows 10 and above

Java Development Kit (JDK) 17

MySQL Server

MySQL Workbench (Optional)
IntelliJ IDEA

4. Database Configuration
Step 1: Installation of MySQL

Install MySQL server and ensure that the MySQL service is running.

Step 2: Create PIMS database

Launch MySQL Workbench or MySQL Command line Client.

Open the database.sql provided in the project and run the entire script.

This script will:

Create the pims database.
Create the necessary tables.
Create the administrator and cashier user accounts.
Create some sample suppliers.
Create some sample medicines.
Establish the necessary primary keys and foreign key relations.

PIMS Database consists of the following main tables:

users
suppliers
medicines
sales
sale_items
5. Database Configuration

PIMS connects to MySQL database via JDBC.

Default database connection settings are:

Database: pims
Host: localhost
Port: 3306
Username: root
Password: Your MySQL Password

If your MySQL username, password or port number is different from the default one then change the database connection properties in:

src/com/pims/database/DatabaseConnection.java

Ensure that MySQL Server is running before launching PIMS.

6. Default Login Credentials
Administrator
Username: admin
Password: admin123
Role: Admin
Access to:

Dashboard

Medicine Management

Supplier Management

User Management

Reports

Cashier
Username: cashier
Password: cash123
Role: Cashier
Access to:

Dashboard

Sales / POS

Stock Check

Logout

7. Running PIMS in IntelliJ IDEA
Step 1
Open the PIMS project in IntelliJ IDEA.

Step 2
Ensure that JDK 17 is set as the project SDK.

Step 3
Ensure that the MySQL Connector/J library is accessible to the project.

Step 4
Ensure that the MySQL Server is up and running and the pims database is created using database.sql.

Step 5
Run the main class of the application.

The application launches the PIMS login form.

8. Log In
Provide a valid username and password in the login form.

The system detects the role of the user and loads the corresponding dashboard.

Invalid credentials will show a login error message.

9. User Management
System users can be managed by the administrator in the User Management section.

The administrator will be able to:

Add users

Update users

Delete users

Set user as an Admin or Cashier

10. Medicine Management
The section of Medicine Management helps the authorised user manage pharmacy inventory information, including:

Medicine Name

Company

Type of Medicine

Price

Stock quantity

Reorder Quantity

Expiry Date

Supplier

11. Supplier Management
This section helps the pharmacy store supplier information, including:

Name of the Supplier

Contact Person

Phone Number

Email Address

Address

12. Sales/POS
The cashier can manage pharmacy sales using Point of Sale system.

Using POS the cashier will be able to:

Choose a medicine

Enter quantity

Add it to the sales

See the transactions

Complete the sales

Print a bill/receipt

All sales are stored in the database.

13. Reports
PIMS provides four reports on pharmacy management that include:

Sales Report

Item-wise Report

Inventory Report

Low Stock Report

These reports are based on the data present in the PIMS database.

14. Database Relationships
Major database relationships are:

Suppliers
   |
   | 1
   |
   | Many
Medicines
   |
   | 1
   |
   | Many
Sale Items
   |
   | Many
   |
   | 1
Sales
   |
   | Many
   |
   | 1
Users
One supplier supplies many medicines.

One sale contains many sale items.

One sale is linked to one user processing the sale.

15. Troubleshooting
Database connection error
When PIMS shows a database connection error:

Ensure that MySQL Server is running.

Ensure that pims database is created.

Ensure the correct username and password used in DatabaseConnection.java.

Ensure that MySQL uses the correct port, usually 3306.

Ensure that MySQL Connector/J is added to the project.

Login failure
In case the default login credentials don’t work:

Ensure that database.sql ran without any problem.

Ensure that users table has default user records.

Ensure that database connection is established.

16. Project Structure
The project has the following Java package structure:

src/
└── com/
    └── pims/
        ├── database/
        ├── gui/
        ├── model/
        ├── LoginFrame.java
        ├── DashboardFrame.java
        └── Main.java

Purpose of the Software
The Pharmacy Inventory Management System is an example of how a Java desktop application can be developed using object-oriented programming techniques and GUI, JDBC connectivity to database, relational database management system, inventory management and point-of-sale operations.

Author
Pharmacy Inventory Management System

Academic software development project.

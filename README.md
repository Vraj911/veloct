Veloct ecommerce JSP servlet project

This is a Java JSP and servlet ecommerce web application.

The application uses JSP files for pages, servlet classes for request handling, DAO classes for database queries, entity classes for data objects, and one database helper class for the MySQL connection.

Project structure

The src main java com ecommerce control folder contains servlet controllers.

The src main java com ecommerce dao folder contains database access classes.

The src main java com ecommerce entity folder contains model classes used by JSP pages and servlets.

The src main java com ecommerce database folder contains the database connection class.

The src main webapp folder contains JSP pages and static frontend assets.

The src main webapp templates folder contains reusable JSP template parts such as header, footer, head, scripts, featured products, and collection sections.

The Dump20210903.sql file contains the MySQL table structure and sample data.

The pom.xml file defines the Maven WAR project and dependencies for Jakarta Servlet, JSTL, and MySQL Connector J.

Database connection

The database connection file is src main java com ecommerce database Database.java.

The connection URL currently points to jdbc mysql 127.0.0.1 port 3306 database name veloct.

The database username is root.

The database password is root.

The MySQL driver class used by the project is com.mysql.cj.jdbc.Driver.

Before running the project, create a MySQL database named veloct, import Dump20210903.sql into it, and make sure the username and password in Database.java match your local MySQL setup.

Build check

Run mvn clean package from the project root.

The project should build a WAR file under the target folder.

The code has been checked with mvn clean package after the fixes.

Detailed project flow and testing guide

The full application flow, login signup explanation, every feature test flow, and file purpose list are written in PROJECT_FLOW_AND_TESTING.txt.

Important notes

This project stores plain text passwords. That is acceptable only for learning projects. A real ecommerce application must hash passwords.

Some DAO methods still build SQL using string concatenation for product, category, and order filters. Login and signup username checks now use prepared statements, but the rest should also be converted before production use.

The project expects Tomcat 10 or another Jakarta Servlet compatible server because it imports jakarta servlet packages.

The database dump header mentions an older database name, but the Java code currently uses veloct. Either create the database as veloct or change Database.java to your chosen database name.

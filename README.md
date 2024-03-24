# Library App 
Project for ManyToMany mapping testing, without using any ORM.

Java 17
Maven
Mockito
Tomcat 10+
Testcontainers
Mapstruckt

DB: PostgreSQL

Base CRUD operations, with ManyToMany and OneToMany mapping.

<h1>
<b> Set-up </b>
</h1>

- All DB query commands for schemas are saved in ```postgres/ddl```
- Configure DB in ``` src/main/resources/database.properties ```
- Set up Tomcat server, using builded .war
- Operate the app with Postman or other API platform.

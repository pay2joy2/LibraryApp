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

- All DB query commands for schemas are saved in ```resources/databaseCreation.sql```
- Configure DB in ``` src/main/resources/database.properties ```
- Set up Tomcat server, using builded .war
- Operate the app with Postman or other API platform.

- <h1> <b> Usage --- Postman commands </b> </h1>

    <p1> <b> "/author" </b></p1>
    

| GET              | POST                  | PUT                      | DEL                 |
|------------------|-----------------------|--------------------------|---------------------|
| "authorId":Long  | "authorId":Long       | "authorId":Long          | "authorId":Long     |
|                  | "authorName":"String" | "authorName":"String"    |                     |
| Get author by id | Create author         | Update authors name      | Delete author by id |



<p1><b> "/book"</b></p1>



| GET              | POST                    | PUT                      | DEL                 |
|------------------|-------------------------|--------------------------|---------------------|
| "bookId":Long    | "bookId":Long           | "bookId":Long            | "bookId":Long       |
|                  | "bookTitle":"String"    | "BookTitle":"String"     |                     |
|                  | "publisherId":Long      |                          |                     |
|                  | "authorsId":[Long, ...] |                          |                     |
| Get book by id   | Create book             | Update books title       | Delete book by id   |



<p1><b> "/publisher"</b></p1>


| GET                      | POST                     | PUT                      | DEL                    |
|--------------------------|--------------------------|--------------------------|------------------------|
| "publisherId":Long       | "publisherId":Long       | "publisherId":Long       | "publisherId":Long     |
| "publisherName":"String" | "publisherName":"String" | "publisherName":"String" |                        |
| Get publisher by id      | Create publisher         | Update publisher name    | Delete publisher by id |



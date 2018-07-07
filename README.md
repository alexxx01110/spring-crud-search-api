# alexm-spring-crud-restfull

> Spring Boot CRUD rest-full app which implement simple CRUD operations and FullText Search with a Article entity.
>  
##What's inside
This project is based on the Spring Boot project and uses these packages:
- Maven
- Spring Core
- Spring Data (Hibernate & PostgresSQL)
- Spring MVC (Tomcat)
- Jackson Core (Jackson API for JSON)
- Tomcat
- REST-API

## Installation
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies.

## Database configuration

Create a PostgresSQL database with the name **alexm** and add the credentials to /resources/db.properties.
The default ones are:
```
pgsql.driver=org.postgresql.Driver
pgsql.url=jdbc:postgresql://localhost:5432/alexm
pgsql.dialect = org.hibernate.dialect.PostgreSQL95Dialect
pgsql.user=postgres
pgsql.password=admin
```
Alternatively, there is a configuration for MySQL and already installed dependencies:
```
mysql.driver=com.mysql.cj.jdbc.Driver
mysql.url=jdbc:mysql://localhost:3306/alexm?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
mysql.dialect = org.hibernate.dialect.MySQL5Dialect
mysql.user=root
mysql.password=admin
```
If you want to change database driver, you need to edit file ../config/AppConfig.class:  
```
...
props.put(DRIVER, env.getProperty("mysql.driver"));
props.put(URL, env.getProperty("mysql.url"));
props.put(USER, env.getProperty("mysql.user"));
props.put(PASS, env.getProperty("mysql.password"));

props.put(DIALECT, env.getProperty("mysql.dialect"));
...
```
If you want to work with any other database, you need to edit maven config file pow.xml to implement dependencies. Then add config to /resources/db.properties and edit ../config/AppConfig.class similarly as write above.
## Usage API call
Run the project through the IDE and head out to http://you-host-name:
```
# Get list of articles
GET /article

# Get article with ID:
GET /article/[id]

# Create new article
POST /article

# Edit existing article
PUT /article/[id]

# Delete article
DELETE /article/[id]

# FullText Search on "title" field with [query]
GET /article/search?query=[query]
```
## Example
Working API on Heroku: (http://spring-vue-quasar.herokuapp.com/article)

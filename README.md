# mylibrary
The simple library manager

Used for accounting of the books

#### Definitions
Book is a entity, that contains unique id, title, year of publication and author 

#### Basic operations
* Maintenance of books(add, remove, delete);
* Searching for the books by title, year of publication and author;  
* Reporting with a list of books, grouped by:
  * Author;
  * Year of publication.  

#### Used technologies
##### Backend:
* Spring MVC
* Hibernate
* Spring Data JPA
* Spring Data JPA Specification 
* Gradle 
* hsqldb in memory
##### Frontend:
* Easy UI Jquery plugin
* Bootstrap

#### Run and build 
The
[gradle-cargo-plugin](https://github.com/bmuschko/gradle-cargo-plugin) 
using command:
```
gradle build cargoRunLocal
```







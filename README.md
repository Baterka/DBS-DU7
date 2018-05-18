# DBS-DU7 (WIP)
## Java Database CRUD Controller
Homework #7 for subject [B0B36DBS](http://www.ksi.mff.cuni.cz/~svoboda/courses/172-B0B36DBS/) on [CTU in Prague](https://cvut.cz)

### Task
-   Assignment:
    -   **Implement a simple desktop application in Java with JPA access to the database**
    -   Create and annotate at least  **2 entities and 1 relationship**  in between them
        -   Cardinality of this relationship must be  _@ManyToMany_
    -   Allow for a  **complex processing of 1 of the selected entities**
        -   I.e. implement all the following CRUD use cases...
        -   _CREATE_: insertion of a new entity instance
        -   _READ_: browsing of a list of all entity instances
        -   _UPDATE_: modification of attributes of an existing entity instance
        -   _DELETE_: removal of an existing entity instance
    -   Allow for a  **complex processing of the selected relationship**
        -   I.e. implement all the following CRUD use cases...
        -   _CREATE_: insertion of a new relationship instance by choosing both the involved entity instances
        -   _READ_: browsing of a list of all related entity instances for a given entity instance
        -   _DELETE_: removal of an existing relationship instance
-   Requirements:
    -   Your application must have a  **graphical user interface**  (e.g. using  _Swing_)
    -   Use must connect to our server at  _**********_  and only use your assigned database
    -   It is insufficient to use just  _JDBC_  and not  _JPA_
    -   You may modify your database schema (from the third assignment) if necessary
    -   Fill all the involved tables with  **sample realistic data**  (not necessarily from your application)
    -   **Comment your source files**

### Installation
- Clone project and open in [Netbeans IDE](https://netbeans.org/)
- Create PostgreSQL database and import all sql files from [database schema](https://github.com/Baterka/DBS-DU7/tree/master/src/main/resources/DBSchema)
- Rename [`persistence.example.xml`](https://github.com/Baterka/DBS-DU7/blob/master/src/main/resources/META-INF/persistence.example.xml) to `persistence.xml`
- Edit `persistence.xml`
- Launch [`Main.java`](https://github.com/Baterka/DBS-DU7/blob/master/src/main/java/controller/Main.java)

### TO-DO
- Finish **Persons in Banks** tab
- Code cleanup
- Testing



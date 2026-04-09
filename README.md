# Java Rental DBMS (SQLite + JDBC)

A small console-based Java database application for managing customer profiles, employee profiles, equipment rentals, deliveries/pickups, and simple business reports.

## Important note
Despite the repository name, the current codebase uses **SQLite** through JDBC, not MySQL.

## Features
- Create, search, edit, and delete **customer** profiles
- Create, search, edit, and delete **employee** profiles
  - Warehouse Manager
  - Service Employee
- Create new **equipment rentals**
- Return rented equipment
- Schedule **delivery** and **pickup** updates for rentals
- Run several built-in reports, including:
  - total items rented by a customer
  - most popular item
  - most popular manufacturer
  - most popular drone
  - member with most items checked out
  - equipment by type before a given year

## Project structure
```text
mySQL-with-Java-DBMS/
├── src/
│   ├── App.java
│   ├── SQL.java
│   ├── Customer.java
│   ├── Employee.java
│   ├── Equipment.java
│   └── Drone.java
├── rentalCompany.db
├── .classpath
├── .project
└── README.md
```

## Main files
- `App.java` – console menu and user interaction flow
- `SQL.java` – database queries and updates
- `Customer.java` – customer profile helper methods
- `Employee.java` – employee profile helper methods
- `Equipment.java` – equipment model / input helper
- `Drone.java` – currently just a placeholder class

## Requirements
- Java 8+
- SQLite JDBC driver on your classpath
- A Java IDE or command-line setup


## How to run

### Option 1: Eclipse
1. Clone or download the repository.
2. Open Eclipse.
3. Import the project as an existing Java project.
4. Add the SQLite JDBC JAR to the build path.
5. Run `App.java`.

### Option 2: Command line
Compile with your SQLite JDBC driver on the classpath, then run `App`.

Example shape:
```bash
javac -cp ".;sqlite-jdbc.jar" src/*.java
java -cp ".;src;sqlite-jdbc.jar" App
```

On macOS/Linux, replace semicolons with colons in the classpath.

## Menu overview
When you launch the program, the main menu provides:
- Personal Information
- Rent/Return
- Deliver/Pickup
- Reports

## Status
This is a solid class project foundation, but it still needs cleanup and a few correctness fixes before it is ready to show as a polished public portfolio project.

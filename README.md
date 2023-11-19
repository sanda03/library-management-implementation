# Getting Started

Set the value of the env:
- **DB_URL** ( ex: DB_URL=jdbc:postgresql://localhost:5432/library_management )
- **DB_PASSWORD**
- **DB_USERNAME**

Use all files [here](database) to implements the database

## Build the project 

```bash
javac -cp libs/lombok.jar:libs/jdbc.jar src/Sanda/*.java src/Sanda/model/*.java  src/Sanda/repository/*.java -d out 
jar cfm library_management.jar Manifest.txt -C out . 
```

## Run the project

```bash
java -cp library_management.jar:libs/lombok.jar:libs/jdbc.jar Sanda/Main 
```

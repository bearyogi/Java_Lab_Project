# Travel Agency Application
Java client-server application for 3rd year university lessons.

- Working on task - Michał Mamla


## Prerequirements
### Techstack:
- JDK 11
- Maven
- JavaFX
- MySQL 
- Lombok
- TestNG
- JUnit

### Database
Create database 'filmdb' via MySql CMD on your localhost and make sure it has all priviliges.

create database filmdb;

create table filmdb.users(\
idUser int PRIMARY KEY NOT NULL,\
userLogin varchar(45) NOT NULL,\
userPassword varchar(45) NOT NULL,\
userName varchar(45) NOT NULL,\
userSurname varchar(45) NOT NULL,\
userEmail varchar(45) NOT NULL\
);

create table filmdb.reservations(\
reservationsId int NOT NULL PRIMARY KEY,\
idUser int NOT NULL,\
tourId int NOT NULL,\
totalPrice varchar(45) NOT NULL,\
date date NOT NULL,\
status varchar(45) NOT NULL,\
FOREIGN KEY(idUser) REFERENCES users(idUser),\
FOREIGN KEY(tourId) REFERENCES tours(tourID)\
);

create table filmdb.tours(\
tourId int NOT NULL PRIMARY KEY,\
title varchar(45) NOT NULL,\
text varchar(500) NOT NULL,\
distance varchar(45) NOT NULL,\
days varchar(45) NOT NULL,\
price varchar(45) NOT NULL,\
availableTickets varchar(45) NOT NULL,\
image varchar(45) NOT NULL\
);

### vm options
--module-path "path-to-your-javafx" --add-modules=javafx.controls,javafx.fxml

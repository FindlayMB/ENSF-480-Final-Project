DROP DATABASE IF EXISTS FlightSystemDatabase;
CREATE DATABASE FlightSystemDatabase;
USE FlightSystemDatabase;

/* Create tables */
DROP TABLE IF EXISTS planes;
CREATE TABLE planes (
    PlaneID     INT NOT NULL AUTO_INCREMENT,
    PlaneType   VARCHAR(30),
    NumRegular  INT,
    NumComfort  INT,
    NumBusiness INT,
    primary key(PlaneID)
);

DROP TABLE IF EXISTS airports;
CREATE TABLE airports (
    AirportCode     CHAR(3) NOT NULL,
    AirportName     VARCHAR(30) NOT NULL,
    Country         VARCHAR(30),
    primary key(AirportCode)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    UserID      INT NOT NULL AUTO_INCREMENT,
    FirstName   VARCHAR(30) NOT NULL,
    LastName    VARCHAR(30) NOT NULL,    
    Email       VARCHAR(60) NOT NULL,



    Role        ENUM('member','employee','admin'),
    primary key(UserID)
);


DROP TABLE IF EXISTS crews;
CREATE TABLE crews (
    CrewID      INT NOT NULL AUTO_INCREMENT,
    Pilot1ID    INT NOT NULL,
    Pilot2ID    INT NOT NULL,
    Pilot3ID    INT,

    primary key(CrewID),
    foreign key(Pilot1ID) references users(UserID),
    foreign key(Pilot2ID) references users(UserID),
    foreign key(Pilot3ID) references users(UserID)
);

DROP TABLE IF EXISTS flights;
CREATE TABLE flights (
    FlightID        INT NOT NULL AUTO_INCREMENT,
    Destination     CHAR(3) NOT NULL ,
    ArrivalTime     CHAR(7) NOT NULL,
    ArrivalDate     CHAR(10) NOT NULL,
    Origin          CHAR(3) NOT NULL,
    DepartureTime   CHAR(7) NOT NULL,
    DepartureDate   CHAR(10) NOT NULL,
    CrewID          INT NOT NULL,
    PlaneID         INT NOT NULL,

    primary key(FlightID),
    foreign key(Destination) references airports(AirportCode),
    foreign key(Origin) references airports(AirportCode),
    foreign key(CrewID) references crews(CrewID),
    foreign key(PlaneID) references planes(PlaneID)
);



/* Insert values into tables */
INSERT INTO planes VALUES
(1,'Boeing 747',20,10,5),
(2,'Boeing 777',20,10,5);

INSERT INTO airports VALUES
('YYC', 'Calgary Airport', 'Canada'),
('AAL','Aalborg','Denmark');




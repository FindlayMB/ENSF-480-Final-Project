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
    AirportCode         CHAR(3)     NOT NULL,
    AirportName         VARCHAR(30) NOT NULL,
    City                VARCHAR(30) NOT NULL,
    Country             VARCHAR(30) NOT NULL,
    primary key(AirportCode)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    UserID              INT         NOT NULL AUTO_INCREMENT,
    FirstName           VARCHAR(30) NOT NULL,
    LastName            VARCHAR(30) NOT NULL,    
    Email               VARCHAR(60) NOT NULL,
    SignUpDate          DATE        NOT NULL,

    CreditCardNumber    VARCHAR(19),
    Role                ENUM('member','employee','admin'),
    primary key(UserID)
);


DROP TABLE IF EXISTS crews;
CREATE TABLE crews (
    CrewID          INT NOT NULL,
    CrewMemberID    INT NOT NULL,
    primary key(CrewID, CrewMemberID),
    foreign key(CrewMemberID) references users(UserID)
);

DROP TABLE IF EXISTS flights;
CREATE TABLE flights (
    FlightID        INT     NOT NULL AUTO_INCREMENT,
    Destination     CHAR(3) NOT NULL ,
    ArrivalTime     TIME    NOT NULL,
    ArrivalDate     DATE    NOT NULL,
    Origin          CHAR(3) NOT NULL,
    DepartureTime   TIME    NOT NULL,
    DepartureDate   DATE    NOT NULL,
    CrewID          INT     NOT NULL,
    PlaneID         INT     NOT NULL,

    primary key(FlightID),
    foreign key(Destination) references airports(AirportCode),
    foreign key(Origin) references airports(AirportCode),
    foreign key(CrewID) references crews(CrewID),
    foreign key(PlaneID) references planes(PlaneID)
);

DROP TABLE IF EXISTS passengerlist;
CREATE TABLE passengerlist (
    FlightID    INT NOT NULL,
    UserID      INT NOT NULL,
    SeatNumber  INT NOT NULL,
    primary key(FlightID, UserID),
    foreign key(FlightID) references flights(FlightID),
    foreign key(UserID) references users(UserID)
);







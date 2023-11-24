DROP DATABASE IF EXISTS FlightSystem;
CREATE DATABASE FlightSystem;
USE FlightSystem;

/* Create tables */

DROP TABLE IF EXISTS planes;
CREATE TABLE planes (
    PlaneID     INT NOT NULL AUTO_INCREMENT,
    PlaneType   VARCHAR(30),
    NumRegular  INT,
    NumComfort  INT,
    NumBusiness INT,
    PRIMARY KEY(PlaneID)
);

DROP TABLE IF EXISTS airports;
CREATE TABLE airports (
    AirportCode         CHAR(3)     NOT NULL,
    AirportName         VARCHAR(30) NOT NULL,
    City                VARCHAR(30) NOT NULL,
    Country             VARCHAR(30) NOT NULL,
    PRIMARY KEY(AirportCode)
    
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    UserID              INT         NOT NULL AUTO_INCREMENT,
    Username            VARCHAR(30) NOT NULL,
    Password            VARCHAR(30) NOT NULL,
    FirstName           VARCHAR(30) NOT NULL,
    LastName            VARCHAR(30) NOT NULL,    
    Email               VARCHAR(60) NOT NULL,
    SignUpDate          DATE        NOT NULL,

    CreditCardNumber    VARCHAR(19),
    Role                ENUM('member','employee','admin'),
    PRIMARY KEY(UserID)
);

DROP TABLE IF EXISTS crews;
CREATE TABLE crews (
    CrewID          INT NOT NULL,
    CrewMemberID    INT NOT NULL,
    Job             VARCHAR(30) NOT NULL,
    PRIMARY KEY(CrewID, CrewMemberID),
    FOREIGN KEY(CrewMemberID) REFERENCES users(UserID)
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

    PRIMARY KEY(FlightID),
    FOREIGN KEY(Destination) REFERENCES airports(AirportCode),
    FOREIGN KEY(Origin) REFERENCES airports(AirportCode),
    FOREIGN KEY(CrewID) REFERENCES crews(CrewID),
    FOREIGN KEY(PlaneID) REFERENCES planes(PlaneID)
);

DROP TABLE IF EXISTS passengerlist;
CREATE TABLE passengerlist (
    FlightID    INT NOT NULL,
    UserID      INT NOT NULL,
    SeatNumber  INT NOT NULL,
    Insurance   BOOL NOT NULL DEFAULT false,
    PRIMARY KEY(FlightID, UserID),
    FOREIGN KEY(FlightID) REFERENCES flights(FlightID),
    FOREIGN KEY(UserID) REFERENCES users(UserID)
);


/* 
 * If we make user login connecting to the database 
 * then we need to use this 
 */
-- Base role creation
DROP ROLE IF EXISTS administrator;
DROP ROLE IF EXISTS employee;
DROP ROLE IF EXISTS member;
CREATE ROLE administrator, employee, member;

DROP USER IF EXISTS 'admin'@localhost;
CREATE USER 'admin'@localhost IDENTIFIED BY 'admin' DEFAULT ROLE administrator;

DROP USER IF EXISTS 'employee'@'localhost';
CREATE USER 'employee'@'localhost' IDENTIFIED BY 'data' DEFAULT ROLE employee;

DROP USER IF EXISTS 'member'@localhost;
CREATE USER 'member'@localhost IDENTIFIED BY 'data' DEFAULT ROLE member;

GRANT ALL ON FlightSystem.* TO administrator;
GRANT SELECT ON FlightSystem.flights TO employee;
GRANT SELECT ON FlightSystem.flights TO member;

ALTER USER 'admin'@localhost ACCOUNT UNLOCK;
ALTER USER 'employee'@localhost ACCOUNT UNLOCK;
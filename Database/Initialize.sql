USE FlightSystem;

/* Insert values into tables */

INSERT INTO planes (PlaneID, PlaneType, NumRegular, NumComfort, NumBusiness)
VALUES
(1,'Boeing 747',20,10,5),
(2,'Boeing 777',20,10,5);

INSERT INTO airports (AirportCode, AirportName, City, Country)
VALUES
('YYC', 'Calgary Airport', 'Calgary' ,'Canada'),
('AAL','Aalborg Airport', 'Aalborg','Denmark');


INSERT INTO users (UserID, Username, Password, FirstName, LastName, Email, SignUpDate, CreditCardNumber, Role)
VALUES
(1,'p1','p1','Passenger','1','Passenger1@gmail.com','2023-11-20',NULL,'member'),
(2,'p1','p1','Passenger','2','Passenger2@gmail.com','2023-11-20',NULL,'member'),
(3,'m1','m1','Member','1','Member1@gmail.com','2023-11-20',NULL,'member'),
(4,'m2','m2','Member','2','Member2@gmail.com','2023-11-20',NULL,'member'),
(5,'e1','e1','Employee','1','Employee1@companymail.com','2023-11-21',NULL,'employee'),
(6,'e2','e2','Employee','2','Employee2@companymail.com','2023-11-21',NULL,'employee'),
(7,'e3','e3','Employee','3','Employee3@companymail.com','2023-11-21',NULL,'employee'),
(8,'a1','a1','Admin','1','Admin1@companyemail.com','2023-11-15',NULL,'admin');


INSERT INTO crews (CrewID, CrewMemberID)
VALUES
(1,5,"Pilot"),
(1,6,"Pilot"),
(2,5,"Pilot");

INSERT INTO flights (FlightID, Destination, ArrivalTime, ArrivalDate, Origin, DepartureTime, DepartureDate, CrewID, PlaneID)
VALUES
(1,'YYC','7:50','2023-01-01','AAL','0:50','2023-01-01',1,1),
(2,'YYC','7:50','2023-01-01','AAL','0:50','2023-01-01',1,1);

INSERT INTO passengerlist (FlightID, UserID, SeatNumber, Insurance)
VALUES
(1,1,1,false),
(1,2,2,true);
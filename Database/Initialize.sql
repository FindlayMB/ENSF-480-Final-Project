USE FlightSystem;

/* Insert values into tables */

INSERT INTO planes (PlaneID, PlaneType, NumRegular, NumComfort, NumBusiness)
VALUES
(1,'Boeing 747',24,12,6),
(2,'Boeing 777',18,6,6);

INSERT INTO airports (AirportCode, AirportName, City, Country)
VALUES
('YYC', 'Calgary Airport', 'Calgary' ,'Canada'),
('YVR','Vancouver Airport', 'Vancouver','Canada');
('LAX ','Los Angeles Airport', 'Los Angeles','United States');
('AAL','Aalborg Airport', 'Aalborg','Denmark');


INSERT INTO users (UserID, Username, Password, FirstName, LastName, Email, SignUpDate, CreditCardNumber, Role)
VALUES
(1,'p1','p1','Passenger','1','Passenger1@gmail.com','2023-11-20',NULL,'member'),
(2,'p2','p2','Passenger','2','Passenger2@gmail.com','2023-11-20',NULL,'member'),
(3,'p3','p3','Passenger','3','Passenger3@gmail.com','2023-11-20',NULL,'member'),
(4,'p4','p4','Passenger','4','Passenger4@gmail.com','2023-11-20',NULL,'member'),
(5,'p5','p5','Passenger','5','Passenger5@gmail.com','2023-11-20',NULL,'member'),
(6,'p6','p6','Passenger','6','Passenger6@gmail.com','2023-11-20',NULL,'member'),
(7,'p7','p7','Passenger','7','Passenger7@gmail.com','2023-11-20',NULL,'member'),
(8,'p8','p8','Passenger','8','Passenger8@gmail.com','2023-11-20',NULL,'member'),
(9,'m1','m1','Member','1','Member1@gmail.com','2023-11-20',NULL,'member'),
(10,'m2','m2','Member','2','Member2@gmail.com','2023-11-20',NULL,'member'),
(11,'e1','e1','Employee','1','Employee1@companymail.com','2023-11-21',NULL,'employee'),
(12,'e2','e2','Employee','2','Employee2@companymail.com','2023-11-21',NULL,'employee'),
(13,'e3','e3','Employee','3','Employee3@companymail.com','2023-11-21',NULL,'employee'),
(14,'a1','a1','Admin','1','Admin1@companyemail.com','2023-11-15',NULL,'admin');


INSERT INTO crews (CrewID, CrewMemberID, Job)
VALUES
(1,11,"Flight Attendant"),
(1,12,"Flight Attendant"),
(2,13,"Flight Attendant");

INSERT INTO flights (FlightID, Destination, ArrivalTime, ArrivalDate, Origin, DepartureTime, DepartureDate, CrewID, PlaneID, BasePrice)
VALUES
(1,'YYC','7:50','2023-01-01','AAL','0:50','2023-01-03',1,1,50),
(2,'YYC','9:50','2023-02-10','AAL','10:50','2023-2-12',1,1,50).
(3,'YVR','13:00','2023-03-04','LAX','14:50','2023-03-04',1,2,50),
(4,'LAX','10:00','2023-04-05','YYC','14:00','2023-04-05',1,2,50);

INSERT INTO passengerlist (FlightID, UserID, SeatNumber, Insurance)
VALUES
(1,1,1,false),
(1,2,2,true),
(2,3,1,true),
(2,4,2,false),
(3,5,1,true),
(3,6,2,false),
(4,7,1,true),
(4,8,2,false);
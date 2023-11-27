USE FlightSystem;

/* Insert values into tables */

INSERT INTO planes (PlaneID, PlaneType, NumRegular, NumComfort, NumBusiness)
VALUES
(1,'Boeing 747',24,12,6),
(2,'Boeing 777',18,6,6);

INSERT INTO airports (AirportCode, AirportName, City, Country)
VALUES
('YYC', 'Calgary Airport', 'Calgary' ,'Canada'),
('YVR','Vancouver Airport', 'Vancouver','Canada'),
('LAX ','Los Angeles Airport', 'Los Angeles','United States'),
('AAL','Aalborg Airport', 'Aalborg','Denmark');


INSERT INTO users (UserID, Username, Password, FirstName, LastName, Email, SignUpDate, CreditCardNumber,CreditCardExpiry,CreaitCardCSV ,Role)
VALUES
(1,'p1','p1','Passenger','1','Passenger1@gmail.com',NULL,NULL,NULL,NULL,'member'),
(2,'p2','p2','Passenger','2','Passenger2@gmail.com',NULL,NULL,NULL,NULL,'member'),
(3,'p3','p3','Passenger','3','Passenger3@gmail.com',NULL,NULL,NULL,NULL,'member'),
(4,'p4','p4','Passenger','4','Passenger4@gmail.com',NULL,NULL,NULL,NULL,'member'),
(5,'p5','p5','Passenger','5','Passenger5@gmail.com',NULL,NULL,NULL,NULL,'member'),
(6,'p6','p6','Passenger','6','Passenger6@gmail.com',NULL,NULL,NULL,NULL,'member'),
(7,'p7','p7','Passenger','7','Passenger7@gmail.com',NULL,NULL,NULL,NULL,'member'),
(8,'p8','p8','Passenger','8','Passenger8@gmail.com',NULL,NULL,NULL,NULL,'member'),
(9,'p9','p9','Passenger','9','Passenger9@gmail.com',NULL,NULL,NULL,NULL,'member'),
(10,'p10','p10','Passenger','10','Passenger10@gmail.com',NULL,NULL,NULL,NULL,'member'),
(11,'p11','p11','Passenger','11','Passenger11@gmail.com',NULL,NULL,NULL,NULL,'member'),
(12,'p12','p12','Passenger','12','Passenger12@gmail.com',NULL,NULL,NULL,NULL,'member'),
(13,'m1','m1','Member','1','Member1@gmail.com',NULL,NULL,NULL,NULL,'member'),
(14,'m2','m2','Member','2','Member2@gmail.com',NULL,NULL,NULL,NULL,'member'),
(15,'e1','e1','Employee','1','Employee1@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(16,'e2','e2','Employee','2','Employee2@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(17,'e3','e3','Employee','3','Employee3@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(18,'e4','e4','Employee','4','Employee4@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(19,'e5','e5','Employee','5','Employee5@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(20,'e6','e6','Employee','6','Employee6@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(21,'e7','e7','Employee','7','Employee7@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(22,'e8','e8','Employee','8','Employee8@companymail.com',NULL,NULL,NULL,NULL,'employee'),
(23,'a1','a1','Admin','1','Admin1@companyemail.com',NULL,NULL,NULL,NULL,'admin');


INSERT INTO crews (CrewID, CrewMemberID, Job)
VALUES
(1,15,"Flight Attendant"),
(1,16,"Flight Attendant"),
(2,17,"Flight Attendant"),
(2,18,"Flight Attendant"),
(3,19,"Flight Attendant"),
(3,20,"Flight Attendant"),
(4,21,"Flight Attendant"),
(4,22,"Flight Attendant");

INSERT INTO flights (FlightID, Destination, ArrivalTime, ArrivalDate, Origin, DepartureTime, DepartureDate, CrewID, PlaneID, BasePrice)
VALUES
(1,'YYC','7:50','2023-01-03','AAL','0:50','2023-01-01',1,1,50),
(2,'YYC','9:50','2023-02-12','AAL','10:50','2023-2-10',2,1,50),
(3,'YVR','14:50','2023-03-04','LAX','13:00','2023-03-04',3,2,50),
(4,'LAX','14:00','2023-04-05','YYC','10:00','2023-04-05',4,2,50);

INSERT INTO passengerlist (FlightID, UserID, SeatNumber, SeatType, Insurance)
VALUES
(1,1,1,'ordinary',false),
(1,2,2,'comfort',true),
(1,3,3,'business',true),
(2,4,1,'ordinary', true),
(2,5,2,'comfort', false),
(2,6,3,'business',true),
(3,7,1,'ordinary',true),
(3,8,2,'comfort',false),
(3,9,3,'business',true),
(4,10,1,'ordinary',true),
(4,11,2,'comfort',false),
(4,12,3,'business',true);
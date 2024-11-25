USE FlightSystem;

/* Insert values into tables */

INSERT INTO planes (PlaneID, PlaneType, NumRegular, NumComfort, NumBusiness)
VALUES
(1,'Boeing 747',24,12,6),
(2,'Boeing 777',18,6,6);

INSERT INTO airports (AirportCode, AirportName, City, Country)
VALUES
('YYC','Calgary Airport','Calgary','Canada'),
('YVR','Vancouver Airport','Vancouver','Canada'),
('LAX','Los Angeles Airport','Los Angeles','United States'),
('AAL','Aalborg Airport','Aalborg','Denmark');


INSERT INTO users (UserID, FirstName, LastName, Email, Role)
VALUES
(1,'p1','p1','Passenger1@gmail.com','guest'),
(2,'p2','p2','Passenger2@gmail.com','guest'),
(3,'p3','p3','Passenger3@gmail.com','guest'),
(4,'p4','p4','Passenger4@gmail.com','guest'),
(5,'p5','p5','Passenger5@gmail.com','guest'),
(6,'p6','p6','Passenger6@gmail.com','guest'),
(7,'p7','p7','Passenger7@gmail.com','guest'),
(8,'p8','p8','Passenger8@gmail.com','guest'),
(9,'p9','p9','Passenger9@gmail.com','guest'),
(10,'p10','p10','Passenger10@gmail.com','guest'),
(11,'p11','p11','Passenger11@gmail.com','guest'),
(12,'p12','p12','Passenger12@gmail.com','guest'),
(13,'m1','m1','Member1@gmail.com','member'),
(14,'m2','m2','Member2@gmail.com','member'),
(15,'e1','e1','Employee1@companymail.com','employee'),
(16,'e2','e2','Employee2@companymail.com','employee'),
(17,'e3','e3','Employee3@companymail.com','employee'),
(18,'e4','e4','Employee4@companymail.com','employee'),
(19,'e5','e5','Employee5@companymail.com','employee'),
(20,'e6','e6','Employee6@companymail.com','employee'),
(21,'e7','e7','Employee7@companymail.com','employee'),
(22,'e8','e8','Employee8@companymail.com','employee'),
(23,'a1','a1','Admin1@companymail.com','admin');


INSERT INTO registered (UserID, Username, Password, SignUpDate, CreditCardNumber, CVV, ExpiryDate)
VALUES
(13,'m1','m1','2023-11-20',NULL,NULL,NULL),
(14,'m2','m2','2023-11-20',NULL,NULL,NULL),
(15,'e1','e1','2023-11-20',NULL,NULL,NULL),
(16,'e2','e2','2023-11-20',NULL,NULL,NULL),
(17,'e3','e3','2023-11-20',NULL,NULL,NULL),
(18,'e4','e4','2023-11-20',NULL,NULL,NULL),
(19,'e5','e5','2023-11-20',NULL,NULL,NULL),
(20,'e6','e6','2023-11-20',NULL,NULL,NULL),
(21,'e7','e7','2023-11-20',NULL,NULL,NULL),
(22,'e8','e8','2023-11-20',NULL,NULL,NULL),
(23,'a1','a1','2023-11-20',NULL,NULL,NULL);

INSERT INTO crews (CrewID, CrewMemberID, Job)
VALUES
(1,15,"Flight Attendant"),
(2,15,"Flight Attendant"),
(1,16,"Flight Attendant"),
(2,17,"Flight Attendant"),
(2,18,"Flight Attendant"),
(3,19,"Flight Attendant"),
(3,20,"Flight Attendant"),
(4,21,"Flight Attendant"),
(4,22,"Flight Attendant");

INSERT INTO flights (FlightID, Destination, ArrivalTime, ArrivalDate, Origin, DepartureTime, DepartureDate, CrewID, PlaneID, BasePrice)
VALUES
(1,'YYC','07:50','2023-01-03','AAL','00:50','2023-01-01',1,1,50),
(2,'YYC','09:50','2023-02-12','AAL','10:50','2023-02-10',2,1,50),
(3,'YVR','14:50','2023-03-04','LAX','13:00','2023-03-04',3,2,50),
(4,'LAX','14:00','2023-04-05','YYC','10:00','2023-04-05',4,2,50),
(5,'LAX','14:00','2023-04-05','YYC','10:00','2023-04-05',4,2,50);

INSERT INTO passengerlist (FlightID, UserID, SeatNumber, SeatType, Insurance)
VALUES
(1,1,1,'regular',false),
(1,2,2,'comfort',true),
(1,3,3,'business',true),
(2,4,1,'regular',true),
(2,5,2,'comfort',false),
(2,6,3,'business',true),
(3,7,1,'regular',true),
(3,8,2,'comfort',false),
(3,9,3,'business',true),
(4,10,1,'regular',true),
(4,11,2,'comfort',false),
(4,12,3,'business',true);

INSERT INTO promos (UserID, PromoCode, DiscountPercent)
VALUES
(13,'PROMO1',0.1),
(14,'PROMO2',0.2),
(15,'PROMO3',0.3),
(16,'PROMO4',0.4),
(17,'PROMO5',0.5),
(18,'PROMO6',0.6),
(19,'PROMO7',0.7),
(20,'PROMO8',0.8),
(21,'PROMO9',0.9),
(22,'PROMO10',1.0);
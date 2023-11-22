USE FlightSystemDatabase;

/* Insert values into tables */

INSERT INTO planes (PlaneID, PlaneType, NumRegular, NumComfort, NumBusiness)
VALUES
(1,'Boeing 747',20,10,5),
(2,'Boeing 777',20,10,5);

INSERT INTO airports (AirportCode, AirportName, City, Country)
VALUES
('YYC', 'Calgary Airport', 'Calgary' ,'Canada'),
('AAL','Aalborg Airport', 'Aalborg','Denmark');


INSERT INTO users (FirstName, LastName, Email, SignUpDate, CreditCardNumber, Role)
VALUES
("Member","1","Member1@gmail.com","2023-11-20",NULL,"member"),
("Member","2","Member2@gmail.com","2023-11-20",NULL,"member"),
("Employee","1","Employee1@companymail.com","2023-11-21",NULL,"employee"),
("Employee","2","Employee2@companymail.com","2023-11-21",NULL,"employee"),
("Employee","3","Employee3@companymail.com","2023-11-21",NULL,"employee"),
("Admin","1","Admin1@companyemail.com","2023-11-15",NULL,"admin");


INSERT INTO crews (CrewID, CrewMemberID)
VALUES
(1,3),
(1,4),
(2,3);

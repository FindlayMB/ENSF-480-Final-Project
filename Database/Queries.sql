
/* Queries for getting data from data base */


/* 
    Get FirstName, LastName, CrewID of employees.
 */
USE flightsystemdatabase;
Select FirstName,LastName,CrewID FROM crews as c
JOIN
(SELECT UserID,FirstName,LastName FROM users
	WHERE Role = 'employee') AS u
ON c.CrewMemberID = u.UserID
ORDER BY FirstName,LastName;



/*  
    Get Role of a user
    Possible roles are member, employee, and admin
 */
USE flightsystemdatabase;
SELECT UserID,FirstName,LastName,Role FROM users AS Members
	WHERE Role = 'member';

USE flightsystemdatabase;
SELECT UserID,FirstName,LastName,Role FROM users AS Employees
	WHERE Role = 'employee';

USE flightsystemdatabase;
SELECT UserID,FirstName,LastName,Role FROM users AS Admins
	WHERE Role = 'admin';


/* Get full tables */
GET * FROM planes;
GET * FROM airports;
GET * FROM users;
GET * FROM crews;
GET * FROM flights;
GET * FROM passengerlist;


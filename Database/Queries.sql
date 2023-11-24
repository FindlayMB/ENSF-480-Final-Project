
/* Queries for getting data from data base */


/* 
    Get FirstName, LastName, CrewID of employees.
 */
USE FlightSystem;
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
USE FlightSystem;
SELECT UserID,FirstName,LastName,Role FROM users AS Members
	WHERE Role = 'member';

USE FlightSystem;
SELECT UserID,FirstName,LastName,Role FROM users AS Employees
	WHERE Role = 'employee';

USE FlightSystem;
SELECT UserID,FirstName,LastName,Role FROM users AS Admins
	WHERE Role = 'admin';


/* Get full tables */
GET * FROM planes;
GET * FROM airports;
GET * FROM users;
GET * FROM crews;
GET * FROM flights;
GET * FROM passengerlist;



/* 
Create a user for a database 
CREATE USER '<username>'@<host> IDENTIFIED BY '<password>' DEFAULT ROLE <role>;

Grant privileges to user
GRANT <privilege> ON <database>.<table> TO '<username>'@'<host>';

Grant role to  user
GRANT <ROLE> TO '<username>'@'<host>';

Grant privileges to role
GRANT <privilege> ON <database>.<table> TO <role>

*/

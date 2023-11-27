
/* Queries for getting data from data base */

/*
	Get users and registered tables left joined by UserID
    If role != 'guest' then the user is registered and as such the columns
    Username, Password, and SignUpDate are not null.
    
    This is the query for getting users from the database.
    Since we joined the 2 tables it allows for the creation of 
    RegisteredUser objects.
*/
USE FlightSystem;
SELECT u.*,r.Username,r.Password,r.SignUpDate FROM users AS u
LEFT JOIN
(SELECT UserID,Username,Password,SignUpDate FROM registered) AS r
ON u.UserID = r.UserID;



/*
	Get the FlightIDs for crew members.
*/
SELECT f.FlightID,c.CrewMemberID FROM flights as f
JOIN
(SELECT CrewID,CrewMemberID FROM crews) as c
ON f.CrewID = c.CrewID;

/*
Get the UserIDs of crew who are on %d flight

SELECT f.FlightID,c.CrewMemberID FROM flights as f
JOIN
(SELECT CrewID,CrewMemberID FROM crews) as c
ON f.CrewID = c.CrewID
WHERE f.FlightID = %d;
*/



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
USE FlightSystem;
SELECT f.FlightID,c.Job FROM flights as f
JOIN
(SELECT CrewID,Job FROM crews WHERE CrewMemberID = %d) as c
ON f.CrewID = c.CrewID;
*/



/*WHERE
CrewID IN (SELECT CrewID FROM crews WHERE CrewMemberID = 1)
*/




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

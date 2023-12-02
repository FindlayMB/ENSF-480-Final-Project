USE FlightSystem;

/* Triggers for handling events */

/* 
    Add triggers for handling the removal of an entry that is a foreign key 
    of a different table.

    Add trigger for handling the update of a table's foreign key to make sure 
    that it is in the table in reference.

 */



/*
	Handle the deletion of an airport for the flights table.
*/ 
delimiter #
DROP TRIGGER IF EXISTS airportDelete#
CREATE TRIGGER airportDelete
BEFORE DELETE ON airports
FOR EACH ROW 
BEGIN
	DELETE FROM flights WHERE Origin = old.AirportCode;
	DELETE FROM flights WHERE Destination = old.AirportCode;
END#



/*
	Handle the deletion of a plane for the flights table.
*/ 
delimiter #
DROP TRIGGER IF EXISTS planeDelete#
CREATE TRIGGER planeDelete
BEFORE DELETE ON planes
FOR EACH ROW
DELETE FROM flights WHERE PlaneID = old.PlaneID#


/*
	Handle the deletion of a flight for the passengerlist table.
*/
delimiter #
DROP TRIGGER IF EXISTS flightDelete#
CREATE TRIGGER flightDelete
BEFORE DELETE ON flights
FOR EACH ROW
DELETE FROM passengerlist WHERE FlightID = old.FlightID;


/*
    Handle the deletion of a user.
*/
delimiter #
DROP TRIGGER IF EXISTS userDelete#
CREATE TRIGGER userDelete
BEFORE DELETE ON users
FOR EACH ROW
BEGIN
	SET @nestlevel = 1;
    DELETE FROM registered WHERE UserID = old.UserID;
    DELETE FROM passengerlist WHERE UserID = old.UserID;
END#

/*
    Handle the deletion of a registered user.
*/
delimiter #
DROP TRIGGER IF EXISTS registeredUserDelete#
CREATE TRIGGER registeredUserDelete
BEFORE DELETE ON registered
FOR EACH ROW
BEGIN
	IF @nestlevel = 1 then SET @nestlevel = 2; 
    ELSE
    SET @nestlevel = 1;
    END IF;
    SET FOREIGN_KEY_CHECKS=0; -- to disable them
    DELETE FROM crews WHERE CrewMemberID = old.UserID;
    SET FOREIGN_KEY_CHECKS=1; -- to re-enable them
END#


delimiter #
DROP TRIGGER IF EXISTS crewDelete#
CREATE TRIGGER crewDelete
BEFORE DELETE ON crews
FOR EACH ROW
BEGIN
    IF (old.CrewID NOT IN (SELECT CrewID FROM crews WHERE CrewMemberID!=old.CrewMemberID)) THEN
		SET FOREIGN_KEY_CHECKS=0; -- to disable them
        DELETE FROM flights WHERE CrewID = old.CrewID;
        SET FOREIGN_KEY_CHECKS=1; -- to re-enable them
    END IF;
END#


DROP TRIGGER IF EXISTS updateUserAfterRegDelete#
CREATE TRIGGER updateUserAfterRegDelete
AFTER DELETE ON registered
FOR EACH ROW
BEGIN
    IF @nestlevel = 1 THEN
        UPDATE users SET Role='guest' WHERE UserID = old.UserID;
    END IF;
END#





-- delete from airports where AirportCode = "YYC";

-- delete from planes where PlaneID = 2#



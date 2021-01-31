/* 
(1)	Create a database with the same name as a prefix of your University email account. 
    For example, if your University email account isabc001@uow.edu.au then a name of a database should be abc001. 
																				0.1 mark                  
*/

CREATE DATABASE so412;

/* 
(2)	Create two new user accounts. The names of user accounts and the passwords are up to you. 
																				0.1 mark                  
*/

CREATE USER joe@localhost IDENTIFIED BY '1234';
CREATE USER jim@localhost IDENTIFIED BY '1234';

/*
(3)	While connected as a user root, process the scripts dbcreate.sql and dbload.sql to create and to load data into the relational tables 
    later on used in this laboratory class. 
	All relational tables must be located in a database created in step (1). 
	A listing of SQL statements processed by the scripts must NOT be included in a report from processing of a script solution1.sql. 
	It means that before processing of the script you must process notee statement (after use database_name command) to turn the spooling off 
	and after processing of the scripts you must process a statement tee solution1.rpt to turn the spooling on into a report file. 		
																				0.2 marks              
*/

use so412;
notee;
source dbcreate.sql;
source dbload.sql;
tee solution1.rpt;

use so412;
/*
(4)	Next, create two new roles: drinker and pub. Grant to a role drinker the read privileges on the entire database created in step (1). 
    The privileges must be granted such that any owner of a role drinker cannot grant the same privileges to another role or user. 
																				0.2 marks         
*/

CREATE ROLE DRINKER, PUB;
GRANT SELECT ON so412.* TO DRINKER; 

/*
(5)	Next, grant to a role pub a read privilege on the relational table LOCATED in the database created in step (1). 
    The privilege must be granted such that any owner of a role pub can grant the same privileges to another role or user.
																				0.2 marks                                                                                    
*/                                                                        

GRANT SELECT ON so412.LOCATED TO PUB WITH GRANT OPTION;

/*
(6)	Next, grant to a role drinker the read and write privileges on the relational table LIKES in the database created in step (1). 
    The privileges must be granted such that any owner of a role drinker cannot grant the same privileges to another role or user.
																				0.2 marks
*/

GRANT ALL ON so412.LIKES TO DRINKER;

/*
 (7) Next, grant to a role drinker the read privileges on the columns drinker, pub, drink in a relational table ORDERS in the database created in step (1). 
     The privileges must be granted such that any owner of a role drinker cannot grant the same privileges to another role or user.	
																				0.3 marks
*/

GRANT SELECT(DRINKER, PUB, DRINK) ON so412.ORDERS TO DRINKER;

/*
(8)	Next, grant to a role pub insert privilege on a relational table SERVES in the database created in step (1). 
    The privileges must be granted such that any owner of a role pub can propagate the same privileges to another role or user.
																				0.2 marks                                                    
*/

GRANT INSERT ON so412.SERVES TO PUB WITH GRANT OPTION;

/*
(9)	Next, grant to a role pub a privilege to create relational tables in the database created in step (1). 
    The privileges must be granted such that any owner of a role pub cannot grant the same privileges to another role or user. 
																				0.2 marks
*/

GRANT CREATE ON so412.* TO PUB;

/*
(10) Next, grant to a role pub a privilege to create relational views located in the database created in step (1). 
    The privileges must be granted such that any owner of a role pub cannot grant the same privileges to another role or user. 
																				0.2 marks                                                    
*/

GRANT CREATE VIEW ON so412.* TO PUB;

/*
(11) Next, grant to a role pub the read privileges on information about the orders completed in the first three months of 2020. 
    A hint is on create a relational view and grant the read privileges on the view. 
	The privileges must be granted such that any owner of a role pub cannot grant the same privileges to another role or user.	
																				0.5 marks                         
*/

CREATE VIEW view1 
AS (SELECT * 
	FROM ORDERS
	WHERE ORDERS.ODATE BETWEEN STR_TO_DATE('01-JAN-2020', '%d-%M-%Y') AND STR_TO_DATE('31-MAR-2020', '%d-%M-%Y'));

GRANT SELECT ON so412.view1 TO PUB;

/*
(12) Next, grant a role drinker to a role pub and then grant a role pub to one of the users created in step (2) and a role drinker to another user.	
																				0.2 marks                                   
*/

GRANT DRINKER TO PUB;
GRANT PUB TO joe@localhost;
GRANT DRINKER TO jim@localhost;

/*
(13) Next, set a resource limit on maximum total number of concurrent connections available to both users created in step (2). 
    The maximum number of concurrent connections is up to you. 	
																				0.2 marks                                                                                          
*/ 

ALTER USER joe@localhost WITH MAX_USER_CONNECTIONS 5;
ALTER USER jim@localhost WITH MAX_USER_CONNECTIONS 5;

/* 
(14)	 Next, lock the accounts of the new users.
																				0.1 mark                                 
*/

ALTER USER joe@localhost
IDENTIFIED BY '1234'
ACCOUNT LOCK;	
ALTER USER jim@localhost
IDENTIFIED BY '1234'
ACCOUNT LOCK;																			

/*
(15)	 Finally, unlock one of the accounts of the new users.
																				0.1 mark                                 
*/

ALTER USER IF EXISTS joe@localhost
ACCOUNT UNLOCK;

/*
Drop the users, the roles, and the database that created
*/

DROP USER joe@localhost; 
DROP USER jim@localhost;
DROP ROLE DRINKER, PUB;
DROP DATABASE so412;

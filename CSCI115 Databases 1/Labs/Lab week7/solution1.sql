USE csit115;

/*
Insert into the file the implementations of the following modifications of the structures, consistency constraints, and/or the contents of the sample database.

Note, that you are not allowed to modify and/or to drop any consistency constraints. 
      You may need more than one SQL statement to implement a single subtask listed below. 
	  Your implementation must directly follow a comment with a specification of a subtask.
*/

/* 
(1)   Modify a structure and consistency constraint of the sample database such that after a modification it is possible to record in the database information about the SUBURB of a pub. The attributes SUBURB, STREET and BLDG_NO form a candidate key.
*/
ALTER TABLE LOCATED ADD COLUMN SUBURB VARCHAR(20) NOT NULL;
ALTER TABLE LOCATED ADD CONSTRAINT located_ckey1 UNIQUE(SUBURB, STREET, BLDG_NO); 

/* 
(2)   Modify a structure and consistency constraint of the sample database such that after a modification it is possible to store the price of a drink in a pub up to 90000.00. The value of a price must be positive not greater than 90000.00.
*/
ALTER TABLE SERVES ADD CONSTRAINT chk_price CHECK(PRICE >= 0 AND PRICE <= 90000);

/* 
(3)   Modify a structure and consistency constraint of the sample database such that after a modification it is possible to store in the database optional information about the total cost of each drinker. Assume that total cost of each drinker is a positive number not greater than 99999.99.
*/
ALTER TABLE DRINKERS ADD COLUMN TOTALCOST DECIMAL(5, 2) NULL;

/* 
(4)   Modify a structure and consistency constraints of a sample database such it is possible to store in the database information about the value of RATING by a drinker on a drink at least 1, at most 10.
*/
ALTER TABLE LIKES MODIFY RATING DECIMAL(2) NOT NULL;
ALTER TABLE LIKES ADD CONSTRAINT chk_rating CHECK(RATING >= 1 AND RATING <= 10);

/* 
(5)   Modify a structure and consistency constraints of a sample database such that DRINK_NO is a positive number not greater than 999.
*/
ALTER TABLE ORDERS MODIFY DRINK_NO DECIMAL(3) NOT NULL;
ALTER TABLE ORDERS ADD CONSTRAINT chk_drinkno CHECK(DRINK_NO>0);
    
/* 
(6)   Add to the database information about a customer MIKE orders two drinks in a pub. All the other information about the two new orders is up to you.
*/
INSERT INTO LIKES VALUES('MIKE', 'RUM', 9);
INSERT INTO LIKES VALUES('MIKE', 'VODKA', 10);
INSERT INTO ORDERS VALUES('MIKE', 'LITTLE PIRATE', STR_TO_DATE('27-APR-2020', '%d-%M-%Y'), 'RUM', 1);
INSERT INTO ORDERS VALUES('MIKE', 'LITTLE PIRATE', STR_TO_DATE('27-APR-2020', '%d-%M-%Y'), 'RUM', 2);
/* 
(7)   A manager of pub LITTLE PIRATE decides to give 10% discount on all drinks. Modify the price of all drinks served in this pub.
*/
UPDATE SERVES SET PRICE = (9*PRICE/10) WHERE PUB IN ('LITTLE PIRATE'); 

/* 
(8)   A customer JAMES decides to change the rating of a drink RUM to 6.
*/
UPDATE LIKES SET RATING = 6 WHERE (DRINKER = "JAMES") AND (DRINK = "RUM"); 

/* 
(9)   A pub SWEET DREAMS closed down. Remove from the database information about the pub SWEET DREAMS.
*/

DELETE FROM ORDERS WHERE (PUB = "SWEET DREAMS");
DELETE FROM SERVES WHERE (PUB = "SWEET DREAMS");
DELETE FROM LOCATED WHERE (PUB = "SWEET DREAMS");

/* 
(10) Remove from the database information about a drinker JAMES.​
*/
DELETE FROM ORDERS WHERE (DRINKER = "JAMES"); 
DELETE FROM LIKES WHERE (DRINKER = "JAMES");
DELETE FROM DRINKERS WHERE (DRINKER = "JAMES");

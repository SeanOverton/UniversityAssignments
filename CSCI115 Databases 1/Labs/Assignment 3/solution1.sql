/*
(1)	First, write a SQL statement to find the drinker, order date and total number of drinks ordered by each drinker in a same day. 
    Display the results in the descending order of total number of drinks, then in the ascending order of drinker.
*/

SELECT DRINKER, ODATE, COUNT(DRINK) total_drinks
FROM ORDERS
GROUP BY DRINKER, ODATE
ORDER BY total_drinks DESC, DRINKER ASC;

/* 
(2)	Next, write SQL statements to inserts into the sample database information about two new orders for a drinker who has ordered the most of number of drinks in one day. 
    Two orders must be done by a drinker who has already ordered the most number of drinks in the same day.
 */

INSERT INTO ORDERS 
VALUES('PETER', 'LONG JOHN', STR_TO_DATE('19-APR-2020', '%d-%M-%Y'), 'BEER', 8);
INSERT INTO ORDERS 
VALUES('PETER', 'LONG JOHN', STR_TO_DATE('19-APR-2020', '%d-%M-%Y'), 'BEER', 9);

/* 
(3)	Next, the script creates a single column relational table MESSAGE to store variable size strings no longer than 500 characters.
*/

CREATE TABLE MESSAGE(
MESSAGE		VARCHAR(500)	NOT NULL,
CONSTRAINT MESSAGE_PKEY PRIMARY KEY(MESSAGE));

/*
(4)	Next, the script inserts into a relational table MESSAGE information about the contents of a sample database that violate the consistency constraint.											
	"A drinker is not allowed to order more than 8 drinks per day"

    The script must list the outcomes of verification of the consistency constraint as a single column table with the following messages as the rows in the table.

    A drinker <insert drinker name here> has ordered <insert total number of orders in one day here> drinks on <insert order date here>, more than 8 drinks per day.

    For example, if a drinker ABC who has ordered 10 drinks on 1 May 2020, then verification of the consistency constraint must return the following message.

    A drinker ABC has ordered 10 drinks on 1 May 2020, more than 8 drinks per day.

    Use a function CONCAT to create the messages like the one listed above.
*/


INSERT INTO MESSAGE(
	SELECT CONCAT('A drinker ', DRINKER, ' has ordered ', COUNT(DRINK), ' drinks on ', ODATE, ', more than 8 drinks per day.') message
	FROM ORDERS
	GROUP BY DRINKER, ODATE
	HAVING COUNT(DRINK) > 8
    );

/* 
(5) Finally, the script makes the contents of a relational table MESSAGE permanent and lists the contents of the table.
*/

COMMIT;
SELECT * FROM MESSAGE;

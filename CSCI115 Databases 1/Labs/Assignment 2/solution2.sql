/*
Insert into the file the implementations of the following queries as SELECT statements of SQL.

Your implementation must directly follow a comment with a specification of a subtask.

The queries listed below must be implemented as SELECT statements with JOIN or LEFT / RIGHT OUTER JOIN operation.
*/

/*
(1) Find the distinct drinkers who have ordered drinks at a pub on VICTORIA AVE. in March 2020.
*/

SELECT DISTINCT DRINKER
FROM ORDERS JOIN LOCATED 
			ON LOCATED.PUB = ORDERS.PUB
WHERE LOCATED.STREET= "VICTORIA AVE." AND
								ORDERS.ODATE BETWEEN STR_TO_DATE('01-MAR-2020', '%d-%M-%Y') 
                                AND STR_TO_DATE('31-MAR-2020', '%d-%M-%Y');

/*
(2)	Find the drinker, total number of times that the drinker rated drinks for all drinkers.
    Include drinkers who haven’t rated a drink.
*/

SELECT DRINKERS.DRINKER, COUNT(LIKES.DRINKER)
FROM DRINKERS LEFT JOIN LIKES
			ON DRINKERS.DRINKER = LIKES.DRINKER
GROUP BY DRINKERS.DRINKER;  

/*
(3)	Find the drinker and total amount of money spent on drinks for all drinkers in February 2020.
    Include drinkers who haven’t ordered a drink in this period.
*/

SELECT DRINKERS.DRINKER, TOTAL
FROM DRINKERS LEFT JOIN (SELECT DRINKER, SUM(PRICE) as TOTAL
			FROM ORDERS LEFT JOIN SERVES
				ON SERVES.DRINK = ORDERS.DRINK AND SERVES.PUB = ORDERS.PUB
			WHERE ORDERS.ODATE BETWEEN STR_TO_DATE('01-FEB-2020', '%d-%M-%Y') AND STR_TO_DATE('31-FEB-2020', '%d-%M-%Y') 
			GROUP BY DRINKER) a
ON DRINKERS.DRINKER = a.DRINKER
GROUP BY DRINKERS.DRINKER;

/*
(4)	Find the drinkers who haven’t ordered any drinks so far.
*/

SELECT DRINKERS.DRINKER
FROM DRINKERS LEFT JOIN ORDERS
        ON DRINKERS.DRINKER = ORDERS.DRINKER
WHERE ORDERS.ODATE IS NULL;

/*
(5)	Find the drinkers who haven’t order any drinks in April 2020.
*/

SELECT DRINKERS.DRINKER 
FROM DRINKERS 
WHERE DRINKERS.DRINKER NOT IN
	(SELECT DRINKERS.DRINKER
	FROM DRINKERS LEFT JOIN ORDERS
			ON DRINKERS.DRINKER = ORDERS.DRINKER
	WHERE ORDERS.ODATE BETWEEN STR_TO_DATE('01-APR-2020', '%d-%M-%Y') AND STR_TO_DATE('31-APR-2020', '%d-%M-%Y')
	GROUP BY DRINKERS.DRINKER);

/*
(6)	Find the drinker and the drink liked by the drinker but the drinker hasn’t order the drink so far. 
    Sort the drinker and the drink pairs in the ascending order of drinkers and drinks.
*/

SELECT LIKES.DRINKER, LIKES.DRINK 
FROM LIKES LEFT JOIN ORDERS 
        ON LIKES.DRINK=ORDERS.DRINK AND LIKES.DRINKER=ORDERS.DRINKER
WHERE ODATE IS NULL
ORDER BY LIKES.DRINKER, LIKES.DRINK; 
 

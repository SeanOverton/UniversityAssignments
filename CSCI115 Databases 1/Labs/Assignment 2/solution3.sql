/*
Insert into the file the implementations of the following queries as SELECT statements of SQL.

Your implementation must directly follow a comment with a specification of a subtask.
*/

/* The queries listed below must be implemented as nested SELECT statements with 
   IN/NOT IN set membership operation.
*/

/* 
(1)	Find the distinct drinkers who have ordered drinks at a pub on VICTORIA AVE. in March 2020.
*/
SELECT DISTINCT DRINKER 
FROM ORDERS
WHERE PUB IN (SELECT PUB 
			  FROM LOCATED 
			  WHERE STREET="VICTORIA AVE.") AND ODATE BETWEEN STR_TO_DATE('01-MAR-2020', '%d-%M-%Y') 
                                AND STR_TO_DATE('30-MAR-2020', '%d-%M-%Y');

/* 
(2)	Find the drinker and the drink liked by the drinker but the drinker hasn’t order the drink so far. 
    Sort the drinker and the drink pairs in the ascending order of drinkers and drinks.
*/

SELECT DRINKER, DRINK
FROM LIKES
WHERE (DRINKER, DRINK) NOT IN (SELECT DRINKER, DRINK
			     FROM ORDERS)
ORDER BY DRINKER, DRINK;
        
/* The queries listed below must be implemented as nested queries with 
   EXISTS/NOT EXISTS clauses.
*/

/* 
(3)	Find the distinct drinkers who have ordered drinks at a pub on VICTORIA AVE. in March 2020.
*/

SELECT DISTINCT DRINKER 
FROM ORDERS AS O 
WHERE EXISTS (SELECT * 
			FROM LOCATED 
            WHERE O.PUB = LOCATED.PUB AND LOCATED.STREET = "VICTORIA AVE.")
            AND O.ODATE BETWEEN STR_TO_DATE('01-MAR-2020', '%d-%M-%Y') 
                                AND STR_TO_DATE('30-MAR-2020', '%d-%M-%Y');
                                
/* 
(4)	Find the drinker and the drink liked by the drinker but the drinker hasn’t order the drink so far. 
    Sort the drinker and the drink pairs in the ascending order of drinkers and drinks.
*/

SELECT DRINKER, DRINK
FROM LIKES AS L
WHERE NOT EXISTS (SELECT *
			     FROM ORDERS AS O
                 WHERE (L.DRINKER = O.DRINKER) AND (L.DRINK = O.DRINK))
ORDER BY DRINKER, DRINK;

/* A query listed below must be implemented with a set algebra operation.               */

/* 
(5)	Find the distinct drinkers who like either BEER or RED WINE.
*/

SELECT DISTINCT DRINKER
FROM LIKES
WHERE DRINK="BEER" OR DRINK="RED WINE";

/* 
A query listed below must be implemented as a nested query. 
*/

/*
(6)	Find the distinct drinkers that ordered both VODKA and WHISKY.
*/

SELECT DISTINCT DRINKER 
FROM ORDERS
WHERE DRINK IN ("VODKA") AND DRINKER IN (SELECT DRINKER 
		FROM ORDERS
        WHERE DRINK = "WHISKY");

/*
Insert into the file the implementations of the following queries as SELECT statements of SQL.
Your implementation must directly follow a comment with a specification of a subtask.
*/

/*
(1)	Find the pub names which located at KING ST. or OXFORD ST..
*/

SELECT PUB 
FROM LOCATED 
WHERE STREET IN ('OXFORD ST.', 'KING ST.');

/*
(2)	Find the pub names that serve WHISKY, or VODKA, or COGNAC.
	Display each pub name only once.
*/

SELECT DISTINCT PUB 
FROM SERVES
WHERE DRINK IN ('WHISKY', 'VODKA', 'COGNAC');

/*
(3)	Find the drinkers who have ordered drinks in January 2020. 
    Display each drinker only once.
*/

SELECT DISTINCT DRINKER
FROM ORDERS 
WHERE ORDERS.ODATE BETWEEN STR_TO_DATE('01-JAN-2020', '%d-%M-%Y') AND STR_TO_DATE('31-JAN-2020', '%d-%M-%Y');

/*
(4)	Find the drinkers who have ordered the drink WHITE WINE at LONG JOHN. 
    Display each drinker only once.
*/

SELECT DISTINCT DRINKER 
FROM ORDERS
WHERE PUB = "LONG JOHN" AND DRINK = "WHITE WINE";

/*
(5)	Find the drink and the rating of drinks that liked.  
    The results must be displayed in the descending order of the ratings, and 
	for all drinks that have the same rating the results must be displayed in the ascending order of drinks. 
    Display each pair only once.
*/

SELECT DISTINCT DRINK, RATING 
FROM LIKES
ORDER BY RATING DESC, DRINK ASC; 

/* 
(6)	Find the drink and the lowest price of each drink served in pubs. 
*/

SELECT ANY_VALUE(DRINK), MIN(PRICE)
FROM SERVES
GROUP BY PUB; 

/*
(7)	Find the drinker and the total number of drinks ordered by each drinker in the first three months of 2020. 
*/

SELECT ANY_VALUE(DRINKER), COUNT(DRINKER)
FROM ORDERS
WHERE ORDERS.ODATE BETWEEN STR_TO_DATE('01-JAN-2020', '%d-%M-%Y') AND STR_TO_DATE('31-MAR-2020', '%d-%M-%Y')
GROUP BY DRINKER; 

/*
(8)	Find the drink and the total amount of ratings of drinks for each drink. Do not display a drink if it hasn’t been rated.
*/

SELECT DRINK, COUNT(DRINK) AS TOTALAMOUNTOFRATINGS
FROM LIKES
GROUP BY DRINK; 

/*
(9)	Find the pub and the total number of drinks served in each pub that has more than three types of drinks.
*/

SELECT PUB, COUNT(PUB) AS TOTALNUMBEROFDRINKS 
FROM SERVES
GROUP BY PUB
HAVING COUNT(PUB) > 3;

/*
(10)	Find the pub, the drink, and the price of pubs that drinks contain a letter E. 
*/

SELECT *
FROM SERVES 
WHERE (DRINK LIKE "%E%");

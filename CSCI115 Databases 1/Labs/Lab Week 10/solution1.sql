/* 
(1)	Create a new relational table to store information about all the drinkers together with total costs on drinks by each drinker.
    Enforce, the appropriate consistency constraints on the new table. 
    Next, copy into the new table information about all the drinkers, and total costs on drinks by each drinker. 
    Note, that all drinkers who have not ordered any drink yet must be included into the table with the total cost on drinks equal to zero (0).   
    When ready use SELECT statement to list the contents of the relational table created and filled with data in the descending order of the total costs on drinks.	                        
*/

/*CREATES TABLE THAT CAN STORE DRINKER AND THEIR EXPENSES*/
CREATE TABLE EXPENSES(
DRINKER	VARCHAR(30)	NOT NULL,
EXPENDITURE DECIMAL(5, 2) NOT NULL, 
    CONSTRAINT expenses_pkey PRIMARY KEY (DRINKER),
    CONSTRAINT expenses_fkey1 FOREIGN KEY(DRINKER) REFERENCES DRINKERS(DRINKER),
    CONSTRAINT expenditure_check1 CHECK (EXPENDITURE >= 0) 
);        

INSERT INTO EXPENSES (DRINKER, EXPENDITURE)
SELECT DRINKERS.DRINKER, IFNULL(TOTAL, 0)
FROM DRINKERS LEFT JOIN (SELECT DRINKER, SUM(PRICE) as TOTAL
			FROM ORDERS LEFT JOIN SERVES
				ON SERVES.DRINK = ORDERS.DRINK AND SERVES.PUB = ORDERS.PUB 
			GROUP BY DRINKER) a
ON DRINKERS.DRINKER = a.DRINKER
GROUP BY DRINKERS.DRINKER;

SELECT * 
FROM EXPENSES 
ORDER BY EXPENDITURE DESC;

/*
(2)	Use a single SQL statement to create a relational table and to load into the table drink, lowest price for the drink, the name of pub and its location information that 
    serves the drink with lowest price for all drinks. Next, enforce the appropriate consistency constraints on the new table. 

	Note, that all drinks which are not served in any pub must be included into the table with the rest of columns contains NULL values.   
	When ready use SELECT statement to list the contents of the relational table created and filled with data in the ascending order of the drinks.
*/

CREATE TABLE BARGAINS (SELECT * FROM ALLDRINKS LEFT JOIN (SELECT d.DRINK, d.PUB, LOCATED.STREET, d.min_price 
FROM LOCATED LEFT JOIN (SELECT * FROM (SELECT a.DRINK, a.PUB, b.min_price FROM
SERVES AS a LEFT JOIN (SELECT DRINK, MIN(PRICE) min_price
	FROM SERVES 
	GROUP BY DRINK) b 
    ON a.DRINK = b.DRINK AND a.PRICE = b.min_price) c
    WHERE min_price IS NOT NULL) d
    ON LOCATED.PUB = d.PUB
    WHERE d.PUB IS NOT NULL) e
    USING(DRINK)); 

ALTER TABLE BARGAINS
MODIFY COLUMN DRINK		VARCHAR(30)	NOT NULL, 
MODIFY COLUMN PUB			VARCHAR(30) NULL,
MODIFY COLUMN STREET		VARCHAR(30) NULL,
MODIFY COLUMN min_price DECIMAL(5, 2) NULL;

ALTER TABLE BARGAINS
ADD CONSTRAINT bargains_pkey PRIMARY KEY (DRINK),
ADD CONSTRAINT bargains_fkey1 FOREIGN KEY (PUB) REFERENCES LOCATED(PUB),
ADD CONSTRAINT bargains_check CHECK (min_price >= 0); 

SELECT * FROM BARGAINS ORDER BY DRINK ASC;

/* 
(3)	Add to a relational table LOCATED information about the total amount of money sold by each pub. Note, that if a pub has not sold any drink so far then for such a pub, 
    the total amount of money must be set to zero (0). Enforce the appropriate consistency constraints on a relational table LOCATED.    
	Use SELECT statement to list the contents an extended relational table LOCATED in the descending order of the total amount of money.
*/

ALTER TABLE LOCATED
ADD COLUMN TOTAL_INCOME DECIMAL(6, 2) NOT NULL;    

ALTER TABLE LOCATED 
ADD CONSTRAINT chk_1 CHECK (TOTAL_INCOME >= 0);

UPDATE LOCATED 
SET TOTAL_INCOME = (SELECT SUM(PRICE)
FROM ORDERS LEFT JOIN SERVES
ON SERVES.DRINK = ORDERS.DRINK AND SERVES.PUB = ORDERS.PUB
WHERE ORDERS.PUB = LOCATED.PUB
);

SELECT * FROM LOCATED ORDER BY TOTAL_INCOME DESC;

/*
(4)	Delete from the database information about all drinks that haven’t been ordered so far. 
    Information about likes and serves of the drinks must be deleted as well. 
    You are not allowed to drop and/or to suspend any referential integrity constraints. 
    The deletions of the drinks, include likes and serves of the drinks, must be implemented as the nested SELECT statements.
*/

DELETE FROM LIKES WHERE DRINK NOT IN (SELECT DISTINCT DRINK FROM ORDERS);
DELETE FROM SERVES WHERE DRINK NOT IN (SELECT DISTINCT DRINK FROM ORDERS);
DELETE FROM ALLDRINKS WHERE DRINK NOT IN (SELECT DISTINCT DRINK FROM ORDERS);

/*  
(5)	Implement the following query as SELECT statement with WITH clause.
    Find the distinct drinkers that ordered both VODKA and WHISKY.
    The query must be implemented as a sequence of at least two subquery definitions following WITH keyword and ended with the final SELECT. 
*/

WITH T1 AS 
(
	SELECT DRINKER
	FROM ORDERS 
	WHERE DRINK IN ("VODKA")
),
T2 AS
(
SELECT DRINKER 
FROM ORDERS 
WHERE DRINK IN ("WHISKY")
) 
SELECT DISTINCT DRINKER 
FROM T1 LEFT JOIN T2 
	USING(DRINKER);


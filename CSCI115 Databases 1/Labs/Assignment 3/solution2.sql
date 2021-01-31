/* 
(1)	Create a database with the same name as a prefix of your University email account. 
    For example, if your University email account is abc001@uow.edu.au then a name of a database should be abc001.
*/

CREATE DATABASE so412;

/* 
(2)	Use a backup file 2019Dump.sql to load the pre 01 January 2020 contents of relational tables LOCATED, SERVES and ORDERS into the database created in step (1). 
*/

USE so412;
source 2019Dump.sql;

/* 
(3)	Use a SELECT statement to list the pub location information for the pubs that no longer opened after 01 January 2020. 
*/

SELECT * FROM so412.located WHERE PUB NOT IN (SELECT PUB FROM csit115.LOCATED);

/* 
(4)	Use a SELECT statement to list the pub location information for the pubs that opened after 01 January 2020.
*/
	
SELECT * FROM csit115.LOCATED WHERE PUB NOT IN (SELECT PUB FROM so412.located);

/*
(5)	Use a SELECT statement to list the pub, drink, price for the pubs that no longer opened after 01 January 2020.
*/

SELECT PUB, DRINK, PRICE FROM so412.serves WHERE PUB NOT IN (SELECT PUB FROM csit115.LOCATED);

/*
(6)	Use a SELECT statement to list the pub, drink, price for the pubs that opened after 01 January 2020.
*/

SELECT PUB, DRINK, PRICE FROM csit115.SERVES WHERE PUB NOT IN (SELECT PUB FROM so412.located);

/*
(7)	Use a SELECT statement to list the pub, drinker and drink for the pubs that no longer opened after 01 January 2020.
*/

SELECT PUB, DRINKER, DRINK FROM so412.orders WHERE PUB NOT IN (SELECT PUB FROM csit115.LOCATED);

/*
(8)	Use a SELECT statement to list the pub, drinker and drink for the pubs that opened after 01 January 2020.
*/

SELECT PUB, DRINKER, DRINK FROM csit115.ORDERS WHERE PUB NOT IN (SELECT PUB FROM so412.located);

DROP DATABASE so412; 
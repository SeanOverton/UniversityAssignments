SPOOL solution1
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 200
SET PAGESIZE 400

/*THIS IS EXECUTED FROM DB.PC-32*/

CREATE DATABASE LINK "DB.DATA-PC33"
CONNECT TO guest208s
IDENTIFIED BY HH3av2
USING 'data-pc33.adeis.uow.edu.au:1521/db';

CREATE SYNONYM ORDERSDB33 FOR ORDERS@"DB.DATA-PC33";
CREATE SYNONYM LINEITEMDB33 FOR LINEITEM@"DB.DATA-PC33";

/*(3) Copy information about all orders and the contents of all orders submitted before 1
March 1992 from the "host server" to the "remote server".*/
INSERT INTO ORDERSDB33 SELECT * FROM ORDERS WHERE O_ORDERDATE < TO_DATE('1992-03-01','YYYY-MM-DD');
INSERT INTO LINEITEMDB33 SELECT * FROM LINEITEM WHERE L_ORDERKEY IN (SELECT O_ORDERKEY FROM ORDERSDB33);
SELECT * FROM LINEITEMDB33;
SELECT * FROM ORDERSDB33;

/*(4) Delete from the "host server" all orders and the contents of all orders submitted
before 1 March 1992.*/
ALTER TABLE ORDERS
DISABLE CONSTRAINT ORDERS_FKEY1;
ALTER TABLE LINEITEM
DISABLE CONSTRAINT LINEITEM_FKEY1;

DELETE FROM ORDERS WHERE O_ORDERDATE < TO_DATE('1992-03-01','YYYY-MM-DD');
DELETE FROM LINEITEM WHERE L_ORDERKEY IN (SELECT O_ORDERKEY FROM ORDERSDB33);

ALTER TABLE ORDERS
ENABLE CONSTRAINT ORDERS_FKEY1;
ALTER TABLE LINEITEM
ENABLE CONSTRAINT LINEITEM_FKEY1;

/*(5) Implement the following queries as SELECT statements.
(i) Find the total number of orders recorded in both the "host server" and the
�remote server�.*/
SELECT (SELECT COUNT(*) FROM ORDERS) +
  (SELECT COUNT(*) FROM ORDERSDB33) AS TOTAL_COUNT FROM DUAL;

/*(ii) Find all order keys and all prices of all orders in both �host server� and the
�remote server� where a price is greater than 150000. Sort the results in the
ascending order of order keys.*/
(SELECT O_ORDERKEY, O_TOTALPRICE FROM ORDERS WHERE O_TOTALPRICE>150000)
UNION ALL
(SELECT O_ORDERKEY, O_TOTALPRICE FROM ORDERSDB33 WHERE O_TOTALPRICE>150000) 
ORDER BY O_ORDERKEY ASC;

/*(iii) Find all order keys of all nonempty orders, i.e. the orders that include at least
one item. List the order keys together with the total number of items included
in each order. Sort the results in the descending order of the total number of
items included in each order. Sort all orders with the same total number of
items in the ascending order of order keys. Use both "host server" and "remote
server".*/
SELECT L_ORDERKEY, COUNT(*) TOTAL_ITEMS FROM LINEITEM GROUP BY L_ORDERKEY 
UNION ALL
SELECT L_ORDERKEY, COUNT(*) TOTAL_ITEMS FROM LINEITEMDB33 GROUP BY L_ORDERKEY
ORDER BY TOTAL_ITEMS DESC, L_ORDERKEY ASC;

/*(iv) Find the part keys of all parts included in at least one order located in the "host
server" and in at least one order located in the "remote server".*/
SELECT DISTINCT L_PARTKEY FROM LINEITEM WHERE L_PARTKEY IN (SELECT L_PARTKEY FROM LINEITEMDB33);


/*(6) Drop the synonyms and a database links.*/
DROP SYNONYM ORDERSDB33 FORCE;
DROP SYNONYM  LINEITEMDB33 FORCE;

ALTER SESSION CLOSE DATABASE LINK "DB.DATA-PC33";

SPOOL OFF
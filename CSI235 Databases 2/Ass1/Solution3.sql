SPOOL solution3
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 300
SET PAGESIZE 200

/*question 1*/
/*(i) Find and list a query processing plan for SELECT statement without an index.*/
EXPLAIN PLAN FOR
    SELECT O_ORDERDATE, O_CUSTKEY
    FROM ORDERS
    WHERE O_ORDERDATE > '31-DEC-1994' AND
     O_ORDERDATE < '01-JAN-1996';

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(ii) Create an index.*/
CREATE INDEX ORDERS_IDX ON ORDERS(O_ORDERDATE, O_CUSTKEY);

/*(iii) Find and list a query processing plan for SELECT statement with an index.*/
EXPLAIN PLAN FOR
    SELECT O_ORDERDATE, O_CUSTKEY
    FROM ORDERS
    WHERE O_ORDERDATE > '31-DEC-1994' AND
     O_ORDERDATE < '01-JAN-1996';
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(iv) Drop an index.*/
DROP INDEX ORDERS_IDX;

/*question 2*/
/*(i) Find and list a query processing plan for SELECT statement without an index.*/
EXPLAIN PLAN FOR 
    SELECT L_PARTKEY, L_SUPPKEY
    FROM LINEITEM
    WHERE ( L_TAX IN (0.02, 0.06) OR L_DISCOUNT> 0.4 ) AND
    L_EXTENDEDPRICE = 7232;

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(ii) Create an index.*/
CREATE INDEX LINEITEM_IDX ON LINEITEM(L_EXTENDEDPRICE);

/*(iii) Find and list a query processing plan for SELECT statement with an index.*/
EXPLAIN PLAN FOR 
    SELECT L_PARTKEY, L_SUPPKEY
    FROM LINEITEM
    WHERE ( L_TAX IN (0.02, 0.06) OR L_DISCOUNT> 0.4 ) AND
    L_EXTENDEDPRICE = 7232;
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(iv) Drop an index.*/
DROP INDEX LINEITEM_IDX;

/*question 3*/
/*(i) Find and list a query processing plan for SELECT statement without an index.*/
EXPLAIN PLAN FOR
    SELECT DISTINCT PS_AVAILQTY, PS_SUPPLYCOST, PS_SUPPKEY
    FROM PARTSUPP;

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(ii) Create an index.*/
CREATE INDEX PARTSUPP_IDX ON PARTSUPP(PS_AVAILQTY, PS_SUPPLYCOST, PS_SUPPKEY);

/*(iii) Find and list a query processing plan for SELECT statement with an index.*/
EXPLAIN PLAN FOR
    SELECT DISTINCT PS_AVAILQTY, PS_SUPPLYCOST, PS_SUPPKEY
    FROM PARTSUPP;
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(iv) Drop an index.*/
DROP INDEX PARTSUPP_IDX;

/*question 4*/
/*(i) Find and list a query processing plan for SELECT statement without an index.*/
EXPLAIN PLAN FOR
    SELECT P_BRAND, AVG(P_RETAILPRICE)
    FROM PART
    GROUP BY P_BRAND;

SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(ii) Create an index.*/
CREATE INDEX PART_IDX ON PART(P_BRAND);

/*(iii) Find and list a query processing plan for SELECT statement with an index.*/
EXPLAIN PLAN FOR
    SELECT P_BRAND, AVG(P_RETAILPRICE)
    FROM PART
    GROUP BY P_BRAND;
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(iv) Drop an index.*/
DROP INDEX PART_IDX;

/*question 5*/
/*(i) Find and list a query processing plan for SELECT statement without an index.*/
EXPLAIN PLAN FOR
    SELECT *
    FROM SUPPLIER
    ORDER BY S_NAME, S_NATIONKEY;
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(ii) Create an index.*/
CREATE INDEX SUPPLIER_IDX ON SUPPLIER(S_NAME, S_NATIONKEY);

/*(iii) Find and list a query processing plan for SELECT statement with an index.*/
EXPLAIN PLAN FOR
    SELECT *
    FROM SUPPLIER
    ORDER BY S_NAME, S_NATIONKEY;
    
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

/*(iv) Drop an index.*/
DROP INDEX SUPPLIER_IDX;

SPOOL OFF
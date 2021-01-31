set echo on
set feedback on
set linesize 300
set pagesize 500
spool dbcreate

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
/* The relational tables of TPC H benchmark database				 */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

DROP TABLE LINEITEM PURGE;
DROP TABLE ORDERS PURGE;
DROP TABLE CUSTOMER PURGE;
DROP TABLE PARTSUPP PURGE;
DROP TABLE SUPPLIER  PURGE;
DROP TABLE PART  PURGE;
DROP TABLE NATION PURGE;
DROP TABLE REGION  PURGE;

spool off 

SELECT * FROM GLOBAL_NAME;
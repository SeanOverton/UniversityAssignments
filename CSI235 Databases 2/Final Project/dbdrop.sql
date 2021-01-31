spool dbdrop
set echo on
set feedback on
set linesize 100
set pagesize 300

DROP TABLE GAME PURGE;
DROP TABLE REFEREE PURGE;
DROP TABLE COACH PURGE;
DROP TABLE TEAM PURGE;
DROP TABLE CITY PURGE;

spool off

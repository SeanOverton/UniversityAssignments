/*
 (1)	First, the script makes a relational table that contains a general log empty.
*/


/* 
(2)  Next, the script sets the appropriate values of the variables to save a general log in 
     a relational table and to start recording a general log from now. 
*/



/* 
(3) Next, the script makes a database csit115 a default database, it stops recording a report, 
       it executes a script file moreOrders.sql, and it resumes recording a report into a file 
       solution3.rpt.
*/


/* 
(4)	Next, the script sets the appropriate values of all variables to stop recording a general log from now.
*/


/* 
(5)	Finally, the script finds and lists how many times each one of the relational tables included in a sample database 
    have been used by the successfully processed SQL statements included in SQL script moreOrders.sql. 
    You have to consider the relational tables with the following names ALLDRINKS, DRINKERS, LOCATED, SERVES, LIKES, and ORDERS. 
    No other relational tables need to be considered. 
    The script must list the names of relational tables together with the total number of times each table has been used. 
    Please, find a fragment of a sample output listed below.

    +------------+-------+
    | TABLE_NAME | TOTAL |
    +------------+-------+
    | SERVES     |    12 |
    | ORDERS     |     6 |
      ...             ...
    +------------+-------+
    6 rows in set (0.01 sec)

    To simplify this task, assume that a relational table is used no more than one time in SQL statement. 

    The results must be listed in the descending order of the total number of times each one of the relational tables 
    has been used by the successfully processed SQL statements included in a script moreOrders.sql. 
    Note, that some of SQL statements included in a script moreOrders.sql cannot be successfully processed and 
    because of that counting the total number of names of relational tables in the script does not provide the correct results. 
    To find the correct results you must access an earlier recorded general log.
*/

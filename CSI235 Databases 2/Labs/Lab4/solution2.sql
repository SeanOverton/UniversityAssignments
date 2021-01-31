SPOOL solution2
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 100
SET PAGESIZE 200
SET SERVEROUTPUT ON

/*(1) First, the script creates a database trigger that fires whenever a new item is added to
an order. The trigger must verify if a user who creates a new item is the same user
who created an order into which a new item is added.
If a user who attempts to insert a new item into an order created by another user the
trigger must abort the transaction and must display an error message. Otherwise, the
trigger does nothing.*/

CREATE OR REPLACE TRIGGER checkUserName
BEFORE INSERT ON LINEITEM
    FOR EACH ROW
    DECLARE 
        results1 NUMBER := 0;
        results2 NUMBER := 0;
        FAIL EXCEPTION;
    BEGIN
        /*THIS CONFIRMS ORDER EXISTS AT ALL*/
        SELECT COUNT(*) INTO results1 FROM ORDERS WHERE O_ORDERKEY= :NEW.L_ORDERKEY;
        
        /*THIS CHECKS WHETHER ORDER WAS CREATED BY THE CORRECT USER*/
        SELECT COUNT(*) INTO results2 FROM ORDER_EDITOR WHERE ORDER_NAME = USER AND ORDERKEY = :NEW.L_ORDERKEY;
        
        /*checks if order exists and user wasn't original creator otherwise if order doesn't exist it is fine or if it does exist and user was creator it is fine*/
        IF results1 > 0 AND results2 = 0 THEN
            RAISE FAIL;  
        END IF; 
        
        EXCEPTION
            WHEN Fail THEN
                raise_application_error(-20000, 'The order was created by another user or an unknown user');
        COMMIT;
    END;
/

/*(2) Next, the script tests the trigger.
A test must include creation of two new orders.
Then, insertion of a new item into one of the orders created a moment ago must be
successful.
Next, the script updates one of the user names in a relational table created in Task 1.
Next, insertion of another item into an order created by a user whose name has been
updated fails the access control rule.
Finally, insertion of an item to an order whose creator is unknown must also fail the
access control rule.*/

INSERT INTO ORDERS VALUES(300, 1,'F',25000,TO_DATE('1998-05-11','YYYY-MM-DD'),'3-MEDIUM','Clerk#000123456',0,'Please leave in the letter box.');
INSERT INTO ORDERS VALUES(301, 3,'F',15000,TO_DATE('1998-05-11','YYYY-MM-DD'),'2-HIGH','Clerk#00064321',0,'Please leave around the side of the house.');

INSERT INTO LINEITEM VALUES(300,7,1,1,1,38094.00,0.02,0.02,'A','F',TO_DATE('2005-04-19','YYYY-MM-DD'),TO_DATE('2005-05-18','YYYY-MM-DD'),TO_DATE('2005-04-20','YYYY-MM-DD'),'NONE','RAIL','BLAH BLAH THIS IS A GOOD EXAMPLE');

UPDATE ORDER_EDITOR
SET ORDER_NAME='JOHN'
WHERE ORDERKEY=300;
INSERT INTO LINEITEM VALUES(300,7,1,2,2,38094.00,0.02,0.02,'A','F',TO_DATE('2005-04-19','YYYY-MM-DD'),TO_DATE('2005-05-18','YYYY-MM-DD'),TO_DATE('2005-04-20','YYYY-MM-DD'),'NONE','RAIL','BLAH BLAH THIS IS A GOOD EXAMPLE');



INSERT INTO LINEITEM VALUES(291,7,1,4,1,38094.00,0.02,0.02,'A','F',TO_DATE('2005-04-19','YYYY-MM-DD'),TO_DATE('2005-05-18','YYYY-MM-DD'),TO_DATE('2005-04-20','YYYY-MM-DD'),'NONE','RAIL','BLAH BLAH THIS IS A GOOD EXAMPLE');

SPOOL OFF

/*SELECT * FROM ORDER_EDITOR;
SELECT COUNT(*) FROM ORDERS WHERE O_ORDERKEY= 300;
SELECT COUNT(*) FROM ORDER_EDITOR WHERE ORDER_NAME = USER AND ORDERKEY = 300;
DELETE FROM LINEITEM WHERE L_ORDERKEY=300 OR  L_ORDERKEY = 301;
DELETE FROM ORDERS WHERE O_ORDERKEY=300 OR O_ORDERKEY = 301;

DROP TRIGGER checkUserName;*/
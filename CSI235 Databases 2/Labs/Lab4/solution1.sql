SPOOL solution1
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 200
SET PAGESIZE 400
SET SERVEROUTPUT ON

/*(1) First, the script creates a relational table to store information about a new order key
and a name of user who created a new order.*/
CREATE TABLE ORDER_EDITOR(
ORDER_NAME VARCHAR(30) NOT NULL,  
ORDERKEY NUMBER(12)  NOT NULL,
    CONSTRAINT ORDER_EDITOR_PK PRIMARY KEY (ORDERKEY),
    CONSTRAINT ORDER_EDITOR_FKEY2 FOREIGN KEY (ORDERKEY) 
        REFERENCES ORDERS(O_ORDERKEY));


/*(2) Next, the script creates a database trigger, that automatically inserts into a relational
table created in the previous step, a key of a new order and a name of a user who
created the order. If an order is deleted then the trigger must automatically delete
information about an order key and a user who earlier created the order. A type of a
trigger is up to you.*/

CREATE OR REPLACE TRIGGER updateORDEREDITOR
AFTER INSERT OR DELETE ON ORDERS
    FOR EACH ROW
    BEGIN
        IF INSERTING THEN           
                INSERT INTO ORDER_EDITOR VALUES(USER, :NEW.O_ORDERKEY);
        END IF;
        IF DELETING THEN
            DELETE FROM ORDER_EDITOR WHERE ORDERKEY = :OLD.O_ORDERKEY;
        END IF;
    END; 
/

/*(3) Next, the script comprehensively tests the trigger. A test must include listing of the
contents of a table with order keys and user names, insertion of an order, listing of
the contents of the table again, deletion of an order, and listing of the contents of the
table again.*/

SELECT * FROM ORDER_EDITOR;

INSERT INTO ORDERS VALUES(300, 1,'F',25000,TO_DATE('1998-05-11','YYYY-MM-DD'),'3-MEDIUM','Clerk#000123456',0,'Please leave in the letter box.');

SELECT * FROM ORDER_EDITOR;

DELETE FROM ORDERS WHERE O_ORDERKEY=300;

SELECT * FROM ORDER_EDITOR;

SPOOL OFF;

/*DROP TABLE ORDER_EDITOR;
DROP TRIGGER updateORDEREDITOR;*/

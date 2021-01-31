SPOOL solution1
SET SERVEROUTPUT OFF
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 200
SET PAGESIZE 400

/*INSERT_ITEM(order_key,part_key,supplier_key,quantity,price, discount,tax);*/

/*CREATE STORED PROCEDURE*/
CREATE OR REPLACE PROCEDURE INSERT_ITEM(order_key IN NUMBER, 
                        part_key IN NUMBER, 
                        quantity IN INTEGER, 
                        price IN NUMBER, 
                        discount IN NUMBER, 
                        tax IN NUMBER) IS
                        
    lineNumber LINEITEM.L_LINENUMBER%TYPE;
    shipDate LINEITEM.L_SHIPDATE%TYPE;
    receiptDate LINEITEM.L_RECEIPTDATE%TYPE;
    todayDate        DATE := SYSDATE;
    randomData INTEGER;
    Fail EXCEPTION;
BEGIN
    /*ENFORCE CONSISTENCY CONSTRAINTS*/
    /*The same part manufactured by the same supplier cannot be included more than one
time in the same order*/
    BEGIN
        SELECT COUNT(*) INTO randomData
            FROM LINEITEM o JOIN
            (SELECT L_ORDERKEY, L_PARTKEY, L_SUPPKEY FROM LINEITEM GROUP BY L_ORDERKEY, L_PARTKEY, L_SUPPKEY HAVING COUNT(1)>=1) t
            ON  o.L_ORDERKEY=t.L_ORDERKEY AND o.L_PARTKEY=t.L_PARTKEY AND o.L_SUPPKEY=t.L_SUPPKEY
            WHERE o.L_ORDERKEY=order_key AND o.L_PARTKEY=part_key;   
        IF randomData != 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR ! The part key and supplier key already exist in the order.');
            RAISE FAIL;
        END IF;
    END;
    
    /*A value of an attribute L_LINENUMBER must be automatically generated.*/
    SELECT COUNT(*)+1 
    INTO lineNumber 
    FROM LINEITEM
    WHERE L_ORDERKEY=order_key;
    
    /*A value of attribute L_SHIPDATE must be tomorrow's date.*/
    shipDate := trunc(sysdate+1);
    
    /*A value of attribute L_RECEIPTDATE must be one week after shipment date.*/
    receiptDate := trunc(sysdate+7);
    
    /*IF VALID INSERT INTO LINEITEM RELATIONAL TABLE*/
    INSERT INTO LINEITEM VALUES(order_key, part_key, 1, lineNumber, quantity, price, discount, tax , 'A', 'A', shipDate, todayDate, receiptDate, 'A', 'A', 'A');
    COMMIT;
        
    /*INVALID SO THROW AN ERROR*/
    /*exception block for fail or any others*/
EXCEPTION
  WHEN Fail THEN
    DBMS_OUTPUT.PUT_LINE('Failed to insert line item');
    ROLLBACK;
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(SQLERRM);
    ROLLBACK;
    
END INSERT_ITEM;
/

/*TEST STORED PROCEDURE*/
/*(1) One successful insertion must be performed into an already existing order.*/
EXECUTE INSERT_ITEM(1, 6, 2, 0, 0, 0);

/*(2) One successful insertion must be performed into a new order, and it means that you
must insert a new order into a relational table ORDERS first.*/

/*CREATE NEW ORDER*/
INSERT INTO ORDERS VALUES(300, 5, 'O', 130000, SYSDATE, '2-HIGH', 'Clerk#000234', 0, 'yeh hope this works hey');

/*INSERT INTO NEW ORDER*/
EXECUTE INSERT_ITEM(300, 6, 2, 0, 0, 0);

/*(3) One unsuccessful insertion must be performed into an already existing order.*/
EXECUTE INSERT_ITEM(1, 6, 2, 0, 0, 0);

/*(4) Use SELECT statement to display the successfully inserted rows.*/
SELECT * FROM LINEITEM WHERE L_EXTENDEDPRICE=0;

SPOOL OFF

DELETE FROM LINEITEM WHERE L_EXTENDEDPRICE=0;
DELETE FROM ORDERS WHERE O_ORDERKEY=300;
 
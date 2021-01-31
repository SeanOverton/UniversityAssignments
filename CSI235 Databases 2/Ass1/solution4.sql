SPOOL solution4
SET SERVEROUTPUT ON
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 200
SET PAGESIZE 400
SET SERVEROUTPUT ON
 
 /*anonymous pl/sql block for solution 4*/
DECLARE
   regionName REGION.R_NAME%TYPE;
   nationName  NATION.N_NAME%TYPE;
   regionKey REGION.R_REGIONKEY%TYPE;
BEGIN
    FOR rrow IN(SELECT R_NAME, R_REGIONKEY
                FROM REGION
                ORDER BY R_NAME)
    LOOP
        regionName := rrow.R_NAME;
        regionKey := rrow.R_REGIONKEY;
        DBMS_OUTPUT.PUT(regionName || ' : ');
        FOR nrow IN(SELECT N_NAME
                    FROM NATION
                    WHERE (N_REGIONKEY=regionKey AND ROWNUM <=3)
                    ORDER BY N_NAME DESC)
        LOOP
            nationName := nrow.N_NAME;
            DBMS_OUTPUT.PUT(nationName || ' ');
        END LOOP;
    DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
    COMMIT;
END;
/ 
 
 SPOOL OFF
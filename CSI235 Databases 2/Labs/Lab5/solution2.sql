SPOOL solution2-1
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 100
SET PAGESIZE 200
SET SERVEROUTPUT ON

/*anonymous pl/sql block for solution 2*/
DECLARE
    regionName VARCHAR(152);
    nationName  VARCHAR(152);
    regionKey REGION.R_REGIONKEY%TYPE;
    nationKey NATION.N_NATIONKEY%TYPE;
    r_index number := 1;
    n_index number := 0;
BEGIN
    FOR rrow IN(SELECT R_NAME, R_REGIONKEY
                FROM REGION
                ORDER BY R_NAME)
    LOOP
        regionName := TRIM(rrow.R_NAME);
        regionKey := TRIM(rrow.R_REGIONKEY);
        DBMS_OUTPUT.PUT('db.task2.insert({"_id": "' || r_index || '",');
        DBMS_OUTPUT.PUT_LINE('"REGION":{"regionName":"' || regionName || '" ,"nations":[');
        FOR nrow IN(SELECT N_NAME, N_NATIONKEY
                    FROM NATION
                    WHERE (N_REGIONKEY=regionKey)
                    ORDER BY N_NAME DESC)
        LOOP
            n_index := n_index + 1;
            nationName := TRIM(nrow.N_NAME);
            nationKey := TRIM(nrow.N_NATIONKEY);
            IF (n_index != 1) THEN
                DBMS_OUTPUT.PUT(',');
                DBMS_OUTPUT.PUT_LINE('');
            END IF;
            DBMS_OUTPUT.PUT('{"NATION": {"nationKey":"' || nationKey || '", ');
            DBMS_OUTPUT.PUT('"nationName": "' || nationNamE || '"} }');
        END LOOP;
    n_index := 0;
    r_index := r_index + 1;
    DBMS_OUTPUT.PUT(']} } );');
    DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
     COMMIT;
END;
/

 SPOOL OFF
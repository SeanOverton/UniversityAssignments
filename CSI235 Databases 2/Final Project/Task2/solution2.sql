SPOOL solution2
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 100
SET PAGESIZE 200
SET SERVEROUTPUT ON

/*Constraint prevents teams playing games before 
their date of creation as this doesn't make any
sense. */

CREATE OR REPLACE PROCEDURE INSERT_GAME(
                        datetime IN TIMESTAMP, 
                        venue IN VARCHAR, 
                        htname IN VARCHAR, 
                        htscore IN NUMBER, 
                        atname IN VARCHAR, 
                        atscore IN NUMBER,
                        fname IN VARCHAR,
                        lname IN VARCHAR) IS
    dateTime1 DATE;
    dateTime2 DATE;
    Fail EXCEPTION;
BEGIN
    BEGIN
      SELECT CDATE INTO dateTime1
      FROM TEAM
      WHERE NAME = htname;

      SELECT CDATE INTO dateTime2
      FROM TEAM
      WHERE NAME = atname;

      IF datetime <= dateTime1 or datetime <= dateTime2 THEN
        DBMS_OUTPUT.PUT_LINE('ERROR ! The teams can not play before their date of creation!');
        RAISE FAIL;
      END IF;
    END;

    /*IF VALID INSERT INTO GAME RELATIONAL TABLE*/
    INSERT INTO GAME VALUES(datetime, venue, htname, htscore, atname, atscore, fname, lname);
    COMMIT;
        
    /*INVALID SO THROW AN ERROR*/
    /*exception block for fail or any others*/
EXCEPTION
  WHEN Fail THEN
    DBMS_OUTPUT.PUT_LINE('Failed to insert GAME');
    ROLLBACK;
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(SQLERRM);
    ROLLBACK;
    
END INSERT_GAME;
/

CREATE OR REPLACE PROCEDURE UPDATE_GAME(
                        u_datetime IN TIMESTAMP, 
                        u_venue IN VARCHAR, 
                        u_htname IN VARCHAR, 
                        u_htscore IN NUMBER, 
                        u_atname IN VARCHAR, 
                        u_atscore IN NUMBER,
                        u_fname IN VARCHAR,
                        u_lname IN VARCHAR) IS
    dateTime1 DATE;
    dateTime2 DATE;
    Fail EXCEPTION;
BEGIN
    BEGIN
      SELECT CDATE INTO dateTime1
      FROM TEAM
      WHERE NAME = u_htname;

      SELECT CDATE INTO dateTime2
      FROM TEAM
      WHERE NAME = u_atname;

      IF u_datetime <= dateTime1 or u_datetime <= dateTime2 THEN
        DBMS_OUTPUT.PUT_LINE('ERROR ! The teams can not play before their date of creation!');
        RAISE FAIL;
      END IF;
    END;

    /*IF VALID INSERT INTO GAME RELATIONAL TABLE*/
    UPDATE GAME 
        SET HTNAME = u_htname, HTSCORE = u_htscore, ATNAME = u_atname, ATSCORE = u_atscore, FNAME = u_fname, LNAME = u_lname 
        WHERE DATETIME = u_datetime AND VENUE = u_venue;
    COMMIT;
    
    /*INVALID SO THROW AN ERROR*/
    /*exception block for fail or any others*/
EXCEPTION
  WHEN Fail THEN
    DBMS_OUTPUT.PUT_LINE('Failed to insert GAME');
    ROLLBACK;
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(SQLERRM);
    ROLLBACK;
    
END UPDATE_GAME;
/

/*TEST PROCEDURE*/
SELECT * FROM GAME;

/*(1) One successful insertion*/
EXECUTE INSERT_GAME('17-MAR-2020:07:00:00', 'AAMI Park', 'Melbourne Victory', 1, 'Western Sydney Wanderers', 2, 'Craig', 'Smith');
EXECUTE UPDATE_GAME('14-MAR-2020:07:00:00', 'Bankwest Stadium', 'Western Sydney Wanderers', 0, 'Sydney FC', 0, 'Ben', 'Smith');

SELECT * FROM GAME;

/*(2) One unsuccessful insertion.*/
EXECUTE INSERT_GAME('21 -MAR-1990 :07:00:00', 'Bankwest Stadium', 'Western Sydney Wanderers', 2, 'Sydney FC', 1, 'Ben', 'Smith');
EXECUTE UPDATE_GAME('14-MAR-1990:07:00:00', 'Bankwest Stadium', 'Western Sydney Wanderers', 2, 'Sydney FC', 1, 'Ben', 'Smith');

/*(3) Testing DELETE works as well*/
DELETE FROM GAME WHERE VENUE = 'Sydney Cricket Ground';

/*(4) Use SELECT statement to display the successfully inserted rows.*/
SELECT * FROM GAME;

SPOOL OFF

 
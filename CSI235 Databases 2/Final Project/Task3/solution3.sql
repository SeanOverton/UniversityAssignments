SPOOL solution3
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 100
SET PAGESIZE 200
SET SERVEROUTPUT ON

/*(1) Create a derived attribute. */
/*new attribute is total teams in a city*/
ALTER TABLE TEAM 
ADD AVERAGE_GOALS NUMBER(2);

/*(2) Create a stored PL/SQL procedure that computes the values of the derived
attribute so it is possible to corrupt db at read-committed isolation level. */

CREATE OR REPLACE PROCEDURE UPDATE_AVERAGE_GOALS IS
    totalHomeGoals INTEGER;
    totalAwayGoals INTEGER;
    totalGames INTEGER;
BEGIN
    FOR teamRow IN (SELECT NAME FROM TEAM)
        LOOP
            SELECT SUM(HTSCORE) INTO totalHomeGoals
                FROM GAME t
                WHERE t.HTNAME = teamRow.NAME; 
                
            SELECT SUM(ATSCORE) INTO totalAwayGoals
                FROM GAME t
                WHERE t.ATNAME = teamRow.NAME;
                
            SELECT COUNT(*) INTO totalGames
                FROM GAME t
                WHERE t.HTNAME = teamRow.NAME; 
                
            UPDATE TEAM
                SET AVERAGE_GOALS = (totalHomeGoals + totalAwayGoals) / totalGames
                WHERE TEAM.NAME = teamRow.NAME;  
        END LOOP;     
END UPDATE_AVERAGE_GOALS;
/

--ADDING MORE GAMES TO DEMONSTATE THE PROCEDURE BETTER
INSERT INTO GAME VALUES ('17-MAR-2020:07:00:00', 'Bankwest Stadium', 'Western Sydney Wanderers', 3, 'Sydney FC', 3, 'Craig', 'Smith');
INSERT INTO GAME VALUES ('18-MAR-2020:07:30:00', 'AAMI Park', 'Melbourne Victory', 1, 'Western Sydney Wanderers', 2, 'Ben', 'Smith');
INSERT INTO GAME VALUES ('12-MAR-2020:07:00:00', 'Sydney Cricket Ground', 'Sydney FC', 1, 'Western Sydney Wanderersy', 4, 'Ben', 'Smith');

EXECUTE UPDATE_AVERAGE_GOALS();

/*(3) Update a sample database such that a derived attribute must be recomputed after
the update.*/
DELETE FROM GAME WHERE HTSCORE = 1;
COMMIT;

/*(4) -List the values of a derived attribute
      -process a stored procedure above to update the values of a derived attribute
      -list the values of a derived attribute after the updates.*/
SELECT NAME, AVERAGE_GOALS_AT_HOME FROM TEAM;

EXECUTE UPDATE_AVERAGE_GOALS();

SELECT NAME, AVERAGE_GOALS_AT_HOME FROM TEAM;

SPOOL OFF
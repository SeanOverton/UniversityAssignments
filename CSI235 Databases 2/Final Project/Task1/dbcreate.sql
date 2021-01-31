spool solution1
set echo on
set feedback on
set linesize 100
set pagesize 300

/* ---------------------------------------------------------------------------- */
/*                                                                              */
/* CSCI235 Database Systems                                                     */
/* Spring 2020                                                                  */
/* A sample database for the final project                                      */
/*                                                                              */
/* ---------------------------------------------------------------------------- */
/*                                                                              */
/* CITY                                                                         */
/*                                                                              */
CREATE TABLE CITY(
NAME             VARCHAR(60)    NOT NULL, /* City name                          */
POPULATION       NUMBER(9)      NOT NULL, /* Population of a city               */
COUNTRY          VARCHAR(60)    NOT NULL, /* Country where located              */
   CONSTRAINT CITY_PKEY PRIMARY KEY (NAME));
/*                                                                              */
/* TEAM                                                                         */
/*                                                                              */
CREATE TABLE TEAM(
NAME             VARCHAR(60)    NOT NULL, /* Team name                          */
CDATE            DATE           NOT NULL, /* Date when created                  */
CITY             VARCHAR(60)    NOT NULL, /* CITY NAME                          */
   CONSTRAINT TEAM_PKEY PRIMARY KEY (NAME),
   CONSTRAINT TEAM_FKEY FOREIGN KEY(CITY) REFERENCES CITY(NAME));	
/*                                                                              */
/* COACH                                                                        */
/*                                                                              */
CREATE TABLE COACH(                        
MCOACH           VARCHAR(60)    NOT NULL, /* Main coach                         */
TNAME             VARCHAR(60)    NOT NULL, /* Team name                         */
   CONSTRAINT COACH_PKEY PRIMARY KEY (MCOACH),
   CONSTRAINT COACH_FKEY FOREIGN KEY(TNAME) REFERENCES TEAM(NAME) );		
/*                                                                              */
/* REFEREE                                                                      */
/*                                                                              */
CREATE TABLE REFEREE(
FNAME           VARCHAR(30)     NOT NULL, /* First name                         */
LNAME           VARCHAR(30)     NOT NULL, /* Last name                          */
CNAME           VARCHAR(60)     NOT NULL, /* City name                          */
   CONSTRAINT REFEREE_PKEY PRIMARY KEY (FNAME, LNAME),
   CONSTRAINT REFEREE_FKEY FOREIGN KEY(CNAME) REFERENCES CITY(NAME) );
/*                                                                              */
/* GAME                                                                         */
/*                                                                              */
CREATE TABLE GAME(
DATETIME        TIMESTAMP       NOT NULL, /* Date and time when started         */
/* To insert a timestamp use a string that matches the following format:        */
/* 'DD-MON-YYYY:HH:MI:SS', for example,'12-MAR-2020:10:40:00'                   */
VENUE           VARCHAR(60)     NOT NULL, /* Service type                       */
HTNAME          VARCHAR(60)     NOT NULL, /* Home team name                     */
HTSCORE         NUMBER(2)       NOT NULL, /* Home team score                    */
ATNAME          VARCHAR(60)     NOT NULL, /* Away team name                     */
ATSCORE         NUMBER(2)       NOT NULL, /* Away team score                    */
FNAME           VARCHAR(30)     NOT NULL, /* Referee first name                 */
LNAME           VARCHAR(30)     NOT NULL, /* Referee last name                  */
   CONSTRAINT GAME_PKEY PRIMARY KEY(DATETIME, VENUE),
   CONSTRAINT GAME_FKEY1 FOREIGN KEY (HTNAME) REFERENCES TEAM(NAME),
   CONSTRAINT GAME_FKEY2 FOREIGN KEY (ATNAME) REFERENCES TEAM(NAME),
   CONSTRAINT GAME_FKEY3 FOREIGN KEY (FNAME,LNAME) REFERENCES REFEREE(FNAME,LNAME),
   CONSTRAINT GAME_CHECK UNIQUE(HTNAME, ATNAME) );
/*                                                                              */
/* ------------------------- End of script ------------------------------------ */
/*                                                                              */

INSERT INTO CITY VALUES ('Sydney', 200000000, 'Australia');
INSERT INTO CITY VALUES ('Melbourne', 150000000, 'Australia');
INSERT INTO CITY VALUES ('Adelaide', 800000000, 'Australia');
INSERT INTO TEAM VALUES ('Western Sydney Wanderers', TO_DATE('1997/01/01 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'Sydney');
INSERT INTO TEAM VALUES ('Sydney FC', TO_DATE('2000/01/01 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'Sydney');
INSERT INTO TEAM VALUES ('Melbourne Victory', TO_DATE('2002/01/01 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 'Melbourne');
INSERT INTO COACH VALUES ('Joe Doe', 'Western Sydney Wanderers');
INSERT INTO COACH VALUES ('John Smith', 'Sydney FC');
INSERT INTO COACH VALUES ('Wreck-it Ralph', 'Melbourne Victory');
INSERT INTO REFEREE VALUES ('Ben', 'Smith', 'Adelaide');
INSERT INTO REFEREE VALUES ('Craig', 'Smith', 'Melbourne');
INSERT INTO GAME VALUES ('10-MAR-2020:07:00:00', 'Sydney Cricket Ground', 'Sydney FC', 2, 'Melbourne Victory', 1, 'Ben', 'Smith');
INSERT INTO GAME VALUES ('12-MAR-2020:07:30:00', 'AAMI Park', 'Melbourne Victory', 3, 'Sydney FC', 4, 'Ben', 'Smith');
INSERT INTO GAME VALUES ('14-MAR-2020:07:00:00', 'Bankwest Stadium', 'Western Sydney Wanderers', 1, 'Melbourne Victory', 0, 'Craig', 'Smith');

COMMIT;

spool off

SPOOL solution12
SET ECHO ON
SET FEEDBACK ON
SET LINESIZE 100
SET PAGESIZE 200

CREATE TABLE information(
    t_name    VARCHAR(55),
    rowcount  NUMBER(12)
);

INSERT INTO information
(t_name, rowcount)
SELECT TABLE_NAME, NUM_ROWS FROM USER_TABLES;

SELECT *
FROM
    information
ORDER BY
    rowcount;

SPOOL OFF
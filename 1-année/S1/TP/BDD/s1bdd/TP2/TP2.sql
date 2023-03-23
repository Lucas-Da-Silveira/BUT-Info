-- Pour se connecter sur MySQL
-- mysql --user=ldasilve --password=1603 --host=serveurmysql --database=BDD_ldasilve

CREATE TABLE test_numeric (
y NUMERIC(4,2)
);

INSERT INTO test_numeric (y) VALUES (12.2222);
INSERT INTO test_numeric (y) VALUES (-12.222);
INSERT INTO test_numeric (y) VALUES (123.2222);
INSERT INTO test_numeric (y) VALUES (999.9922);
INSERT INTO test_numeric (y) VALUES (-9999999999.2222);

SELECT * FROM test_numeric;
DESCRIBE test_numeric;
DROP TABLE test_numeric;

# y NUMERIC(6,2) ZEROFILL    => conclusion :
 
# Important : différence entre NUMERIC et FLOAT :
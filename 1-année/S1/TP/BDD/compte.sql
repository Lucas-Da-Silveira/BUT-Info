-- mysql --user=ldasilve --password=1603 --host=localhost  --database=BDD_ldasilve

CREATE DATABASE BDD_ldasilve;
CREATE USER 'ldasilve' IDENTIFIED BY  '1603';

GRANT ALL PRIVILEGES ON  *.* To 'ldasilve';
FLUSH PRIVILEGES;
CREATE DATABASE test_expenses_db;
USE test_expenses_db;
CREATE TABLE users(id_user INT not null primary key auto_increment, name_user VARCHAR(40),max_value int);
CREATE TABLE expenses(id_expenses INT not null primary key auto_increment, id_user INT, description VARCHAR(30),type VARCHAR(1),month VARCHAR(2),year VARCHAR(4),value DECIMAL(6,2));
CREATE TABLE credit_score(id_credit INT not null primary key auto_increment, min_value DECIMAL(6,2), max_value DECIMAL(6,2),identify_letter VARCHAR(1) );

--CREATE TABLE max-value(table_name VARCHAR(MAX), max_value INT);


INSERT INTO users VALUES ( 1, 'Will',5 );
INSERT INTO users VALUES ( 2, 'Tony',5 );
INSERT INTO users VALUES ( 3, 'Max',5 );
INSERT INTO users VALUES ( 4, 'Carl',5 );

INSERT INTO expenses VALUES ( 1, 1,'shoes','C','02','2020',81.25 );
INSERT INTO expenses VALUES ( 2, 2,'shoes nike','C','02','2020',141.55 );
INSERT INTO expenses VALUES ( 3, 3,'shoes addidas','C','02','2020',100.35 );
INSERT INTO expenses VALUES ( 4, 4,'mc donalds','C','02','2020',15.75 );

INSERT INTO expenses VALUES ( 5, 1,'salary','D','02','2020',700.00 );
INSERT INTO expenses VALUES ( 6, 2,'salary','D','02','2020',1300.00 );
INSERT INTO expenses VALUES ( 7, 3,'salary','D','02','2020',200.50 );
INSERT INTO expenses VALUES ( 8, 4,'salary','D','02','2020',100.20 );

INSERT INTO credit_score VALUES ( 1, 0.00,100.00,'F');
INSERT INTO credit_score VALUES ( 2, 100.01,300.00,'E');
INSERT INTO credit_score VALUES ( 3, 300.01,600.00,'D');
INSERT INTO credit_score VALUES ( 4, 600.01,900.00,'C');
INSERT INTO credit_score VALUES ( 5, 900.01,1200.00,'B');
INSERT INTO credit_score VALUES ( 6, 1200.01,9999.00,'A');

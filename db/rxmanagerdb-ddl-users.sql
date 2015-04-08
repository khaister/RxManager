CREATE DATABASE rxmanagerdb;

CREATE USER 'rxmandoc'@'localhost' IDENTIFIED BY 'rxmandocpasswd';
GRANT USAGE, SELECT, INSERT, DELETE 
	ON rxmanagerdb.Patients
	TO 'rxmandoc'@'localhost';
GRANT USAGE, SELECT, INSERT, DELETE 
	ON rxmanagerdb.Prescriptions
	TO 'rxmandoc'@'localhost';

CREATE USER 'rxmanpharmacist'@'localhost' IDENTIFIED BY 'rxmanpharmacistpasswd';
GRANT USAGE, SELECT, INSERT
	ON rxmanagerdb.Prescriptions
	TO 'rxmanpharmacist'@'localhost';
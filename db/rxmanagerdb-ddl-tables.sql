USE rxmanagerdb;

CREATE TABLE Doctors
(
	dFirstName VARCHAR(45) NOT NULL,
	dLastName VARCHAR(45) NOT NULL,
	dLicense VARCHAR(45) NOT NULL,
	dDOB DATE NOT NULL,
	dUsername VARCHAR(20),
	dPassword VARCHAR(20),
	
	CONSTRAINT doctor_pk
	PRIMARY KEY (dLicense)
);

CREATE TABLE Patients
(
	pFirstName VARCHAR(45) NOT NULL,
	pLastName VARCHAR(45) NOT NULL,
	pDOB DATE NOT NULL,
	pPhone VARCHAR(45) NOT NULL,
	pAddress VARCHAR(45) NOT NULL,
	pCity VARCHAR(45) NOT NULL,
	pState VARCHAR(45) NOT NULL,
	pZipCode VARCHAR(45) NOT NULL,
	pMedicalNumber VARCHAR(45) NOT NULL,

	CONSTRAINT patients_pk
	PRIMARY KEY (pMedicalNumber)
);

CREATE TABLE Pharmacies
(
	phyName VARCHAR(45) NOT NULL,
	phyAddress VARCHAR(45) NOT NULL,
	phyCity VARCHAR(45) NOT NULL,
	phyState VARCHAR(45) NOT NULL,
	phyZipCode VARCHAR(45) NOT NULL,
	phyBranchID VARCHAR(45) NOT NULL,
	phyPhone VARCHAR(45) NOT NULL,

	CONSTRAINT pharm_pk
	PRIMARY KEY (phyBranchID)
);

CREATE TABLE Pharmacists
(
	phistFirstName VARCHAR(45) NOT NULL,
	phistLastName VARCHAR(45) NOT NULL,
	phistLicense VARCHAR(45) NOT NULL,
	phistDOB Date NOT NULL,
	phistPharmacyBranchID VARCHAR(45) NOT NULL,
	phistUsername VARCHAR(45),
	phistPassword VARCHAR(45),

	CONSTRAINT pharmacist_pk
	PRIMARY KEY (phistFirstName, phistLastName, phistLicense),

	CONSTRAINT phist_phy_fk
	FOREIGN KEY (phistPharmacyBranchID)
	REFERENCES Pharmacies (phyBranchID)
);

CREATE TABLE Prescriptions
(
	RxName VARCHAR (45) NOT NULL,
	RxStrength VARCHAR (45) NOT NULL,
	RxRoute VARCHAR (45) NOT NULL,
	RxFrequency VARCHAR(45) NOT NULL,
	RxQuantity VARCHAR(45) NOT NULL,
	RxMaxRefills INT NOT NULL,
	RxRefills INT NOT  NULL,
	RxDatePrescribed DATE NOT NULL,
	RxDateFilled DATE,
	RxNotes VARCHAR(200),
	RxIsFilled BOOLEAN NOT NULL,
	RxPharmacyID VARCHAR(45) NOT NULL,
	RxDocLicense VARCHAR(45) NOT NULL,
	RxPatientMedNumber VARCHAR(45) NOT NULL,

	CONSTRAINT rx_pk
	PRIMARY KEY (RxName, RxDate, RxPatientMedNumber),

	CONSTRAINT rx_pharm_fk
	FOREIGN KEY (RxPharmacyID)
	REFERENCES Pharmacies (phyBranchID),

	CONSTRAINT rx_doc_fk
	FOREIGN KEY (RxDocLicense)
	REFERENCES Doctors (dLicense),

	CONSTRAINT rx_patient_fk
	FOREIGN KEY (RxPatientMedNumber)
	REFERENCES Patients (pMedicalNumber)
);
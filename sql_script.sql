create database company;

CREATE TABLE Employee(
	id VARCHAR(30),
    firstname VARCHAR(30),
    lastname VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    pwd VARCHAR(50) NOT NULL,
    role VARCHAR(30),

    CONSTRAINT id_pk PRIMARY KEY (id)
);

CREATE TABLE Salary(
	salaryId INT AUTO_INCREMENT,
	employee_id VARCHAR(30) NOT NULL,
    amount INT,
    taxes INT,
    children INT,
    married bool,
    month INT,
    year INT,
    
    CONSTRAINT salaryId_pk PRIMARY KEY (salaryId),
    CONSTRAINT employee_id_fk FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into Employee (id, firstname, lastname, email, pwd, role) values ("12345", "Lisa", "Müller", "test@gmail.de", "OINQVIO§$NQEI", "EMPLOYEE");
insert into Employee (id, firstname, lastname, email, pwd, role) values ("12457", "Vanessa", "Meier", "tim@gmail.de", "OINQVIO§$NQEI", "EMPLOYEE");
insert into Employee (id, firstname, lastname, email, pwd, role) values ("92853", "Tina", "Schmidt", "tina@gmail.de", "OINQVIO§$NQEI", "EMPLOYEE");
insert into Employee (id, firstname, lastname, email, pwd, role) values ("20518", "Tim", "Bauer", "asdf@gmail.de", "OINQVIO§$NQEI", "CEO");
insert into Employee (id, firstname, lastname, email, pwd, role) values ("32054", "Thomas", "Becker", "test@gmail.de", "OINQVIO§$NQEI", "INTERN");
insert into Employee (id, firstname, lastname, email, pwd, role) values ("92063", "Thorsten", "Zieres", "thorsten@stud.fra-uas.de", "OINQVIO§$NQEI", "WORKING_STUDENT");

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12345", 3000, 1000, 0, false, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12345", 3000, 1000, 0, false, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12345", 3000, 1000, 0, false, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12345", 3000, 1000, 1, false, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12345", 3000, 1000, 1, false, 11, 2021);

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12457", 4000, 1000, 0, true, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12457", 4000, 1000, 0, true, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12457", 4000, 1000, 0, true, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12457", 4000, 1000, 1, false, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("12457", 4000, 1000, 1, false, 11, 2021);

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92853", 3500, 1000, 3, false, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92853", 3500, 1000, 3, false, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92853", 3500, 1000, 3, false, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92853", 3500, 1000, 3, false, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92853", 3500, 1000, 3, false, 11, 2021);

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("20518", 10000, 5000, 0, true, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("20518", 10000, 5000, 0, true, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("20518", 10000, 5000, 0, true, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("20518", 10000, 5000, 0, true, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("20518", 10000, 5000, 0, true, 11, 2021);

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("32054", 1500, 200, 0, false, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("32054", 1500, 200, 0, false, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("32054", 1500, 200, 0, false, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("32054", 1500, 200, 0, false, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("32054", 1500, 200, 0, false, 11, 2021);

insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92063", 2000, 1000, 0, false, 7, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92063", 2000, 1000, 0, false, 8, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92063", 2000, 1000, 0, false, 9, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92063", 2000, 1000, 0, false, 10, 2021);
insert into Salary (employee_id, amount, taxes, children, married, month, year) values ("92063", 5000, 2000, 0, false, 11, 2021);

-- Rename column name
ALTER TABLE department
RENAME COLUMN creation_date TO created_at;


-- Step 1: Insert or Update Departments
INSERT INTO department (id, name, created_at)
VALUES
  (1, 'Engineering', CURRENT_DATE),
  (2, 'Marketing', CURRENT_DATE),
  (3, 'Human Resources', CURRENT_DATE),
  (4, 'Finance', CURRENT_DATE)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name,
  created_at = EXCLUDED.created_at;

-- Step 2: Insert or Update Department Heads
INSERT INTO employee (id, name, dob, salary, address, title, joining_date, manager_id, department_id)
VALUES
  (1, 'Alice Smith', '1980-01-01', 100000, '123 Engineering Ave', 'Head of Engineering', '2010-01-01', NULL, 1),
  (2, 'Bob Johnson', '1981-02-01', 95000, '456 Marketing Blvd', 'Head of Marketing', '2011-02-01', NULL, 2),
  (3, 'Carol Lee', '1982-03-01', 90000, '789 HR Lane', 'Head of HR', '2012-03-01', NULL, 3),
  (4, 'David Kim', '1983-04-01', 97000, '101 Finance St', 'Head of Finance', '2013-04-01', NULL, 4)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name, dob = EXCLUDED.dob, salary = EXCLUDED.salary, address = EXCLUDED.address,
  title = EXCLUDED.title, joining_date = EXCLUDED.joining_date, manager_id = EXCLUDED.manager_id, department_id = EXCLUDED.department_id;

-- Step 3: Insert Employees by Department
-- We now use a separate INSERT statement for each team, which is cleaner and safer.
-- Engineering Team (Manager: Alice Smith, id=1)
INSERT INTO employee (id, name, dob, salary, address, title, joining_date, manager_id, department_id)
VALUES
  (5, 'Michael Davis', '1990-05-01', 75000, 'Eng Addr 1', 'Engineer', '2016-01-01', 1, 1),
  (6, 'Jessica Williams', '1991-06-01', 72000, 'Eng Addr 2', 'Engineer', '2017-02-01', 1, 1),
  (7, 'Christopher Brown', '1992-07-01', 71000, 'Eng Addr 3', 'Engineer', '2018-03-01', 1, 1),
  (8, 'Amanda Garcia', '1993-08-01', 73000, 'Eng Addr 4', 'Engineer', '2019-04-01', 1, 1),
  (9, 'Matthew Wilson', '1994-09-01', 76000, 'Eng Addr 5', 'Engineer', '2020-05-01', 1, 1),
  (10, 'Sarah Miller', '1995-10-01', 78000, 'Eng Addr 6', 'Senior Engineer', '2021-06-01', 5, 1),
  (11, 'Kevin Jones', '1996-11-01', 70000, 'Eng Addr 7', 'Intern', '2022-07-01', 5, 1)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name, dob = EXCLUDED.dob, salary = EXCLUDED.salary, address = EXCLUDED.address,
  title = EXCLUDED.title, joining_date = EXCLUDED.joining_date, manager_id = EXCLUDED.manager_id, department_id = EXCLUDED.department_id;

-- Marketing Team (Manager: Bob Johnson, id=2)
INSERT INTO employee (id, name, dob, salary, address, title, joining_date, manager_id, department_id)
VALUES
  (12, 'Emily Thomas', '1989-03-01', 60000, 'Mktg Addr 1', 'Marketer', '2015-08-01', 2, 2),
  (13, 'Daniel White', '1990-04-01', 62000, 'Mktg Addr 2', 'SEO Analyst', '2016-09-01', 2, 2),
  (14, 'Olivia Harris', '1991-05-01', 61000, 'Mktg Addr 3', 'Copywriter', '2017-10-01', 2, 2),
  (15, 'James Martin', '1992-06-01', 59000, 'Mktg Addr 4', 'Graphic Designer', '2018-11-01', 2, 2),
  (16, 'Laura Thompson', '1993-07-01', 63000, 'Mktg Addr 5', 'Marketing Executive', '2019-12-01', 2, 2),
  (17, 'Robert Allen', '1994-08-01', 65000, 'Mktg Addr 6', 'Senior Marketer', '2020-01-01', 9, 2),
  (18, 'Chloe Lewis', '1995-09-01', 58000, 'Mktg Addr 7', 'Intern', '2021-02-01', 9, 2)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name, dob = EXCLUDED.dob, salary = EXCLUDED.salary, address = EXCLUDED.address,
  title = EXCLUDED.title, joining_date = EXCLUDED.joining_date, manager_id = EXCLUDED.manager_id, department_id = EXCLUDED.department_id;

-- HR Team (Manager: Carol Lee, id=3)
INSERT INTO employee (id, name, dob, salary, address, title, joining_date, manager_id, department_id)
VALUES
  (19, 'Megan Hall', '1988-02-01', 55000, 'HR Addr 1', 'HR Executive', '2015-03-01', 3, 3),
  (20, 'Ryan Young', '1989-03-01', 57000, 'HR Addr 2', 'HR Coordinator', '2016-04-01', 3, 3),
  (21, 'Samantha King', '1990-04-01', 53000, 'HR Addr 3', 'Recruiter', '2017-05-01', 3, 3),
  (22, 'Jacob Wright', '1991-05-01', 56000, 'HR Addr 4', 'Compensation Analyst', '2018-06-01', 3, 3),
  (23, 'Hannah Scott', '1992-06-01', 58000, 'HR Addr 5', 'Benefits Admin', '2019-07-01', 3, 3),
  (24, 'Ethan Torres', '1993-07-01', 60000, 'HR Addr 6', 'Senior HR', '2020-08-01', 21, 3),
  (25, 'Lauren Green', '1994-08-01', 52000, 'HR Addr 7', 'Intern', '2021-09-01', 21, 3)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name, dob = EXCLUDED.dob, salary = EXCLUDED.salary, address = EXCLUDED.address,
  title = EXCLUDED.title, joining_date = EXCLUDED.joining_date, manager_id = EXCLUDED.manager_id, department_id = EXCLUDED.department_id;

-- Finance Team (Manager: David Kim, id=4)
INSERT INTO employee (id, name, dob, salary, address, title, joining_date, manager_id, department_id)
VALUES
  (26, 'Joshua Baker', '1987-01-01', 67000, 'Fin Addr 1', 'Accountant', '2014-03-01', 4, 4),
  (27, 'Mia Adams', '1988-02-01', 69000, 'Fin Addr 2', 'Auditor', '2015-04-01', 4, 4),
  (28, 'Nicholas Nelson', '1989-03-01', 71000, 'Fin Addr 3', 'Tax Analyst', '2016-05-01', 4, 4),
  (29, 'Sofia Carter', '1990-04-01', 73000, 'Fin Addr 4', 'Treasury Analyst', '2017-06-01', 4, 4),
  (30, 'William Phillips', '1991-05-01', 75000, 'Fin Addr 5', 'Controller', '2018-07-01', 4, 4),
  (31, 'Grace Roberts', '1992-06-01', 77000, 'Fin Addr 6', 'Senior Accountant', '2019-08-01', 30, 4),
  (32, 'Alexander Turner', '1993-07-01', 68000, 'Fin Addr 7', 'Intern', '2020-09-01', 30, 4)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name, dob = EXCLUDED.dob, salary = EXCLUDED.salary, address = EXCLUDED.address,
  title = EXCLUDED.title, joining_date = EXCLUDED.joining_date, manager_id = EXCLUDED.manager_id, department_id = EXCLUDED.department_id;

-- Step 4: Set department heads
UPDATE department SET department_head_id = 1 WHERE id = 1;
UPDATE department SET department_head_id = 2 WHERE id = 2;
UPDATE department SET department_head_id = 3 WHERE id = 3;
UPDATE department SET department_head_id = 4 WHERE id = 4;
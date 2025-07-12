-- Create department table
CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

-- Create employee table
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    dob DATE,
    salary INTEGER,
    address VARCHAR(1000),
    title VARCHAR(255),
    joining_date DATE,
    manager_id INTEGER,
    department_id INTEGER NOT NULL,
    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employee(id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department(id)
);

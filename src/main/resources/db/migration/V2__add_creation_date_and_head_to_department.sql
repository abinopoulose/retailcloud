-- Add 'creation_date' and 'department_head_id' to department table

ALTER TABLE department
    ADD COLUMN creation_date DATE,
    ADD COLUMN department_head_id INTEGER,
    ADD CONSTRAINT fk_department_head FOREIGN KEY (department_head_id) REFERENCES employee(id);



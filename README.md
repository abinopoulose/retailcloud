# Spring Boot Employee & Department Management APP

A simple REST API for managing employees and departments, built with Spring Boot and PostgreSQL. Runs with Docker Compose.

## Quick Start

```bash
docker compose up --build
```

- App: http://localhost:8080

## Main API Endpoints

### Employees
- `POST /api/employee` — Create employee
- `PATCH /api/employee/{id}` — Update employee
- `GET /api/employee/{id}` — Get employee by ID
- `DELETE /api/employee/{id}` — Delete employee
- `GET /api/employee` — List employees  
    Query parameters: 
  - `page` (int, default: 0): Page number for pagination
  - `size` (int, default: 20): Page size for pagination
  - `name` (string, optional): Filter by employee name
  - `departmentId` (int, optional): Filter by department ID
  - `title` (string, optional): Filter by employee title
  - `lookup` (bool, default: false): Must be true to return employee data

### Departments
- `POST /api/department` — Create department
- `PATCH /api/department/{id}` — Update department
- `DELETE /api/department/{id}` — Delete department 
- `GET /api/department/{id}` — Get department by ID  
  Supports `expand=employees` query parameter to include all employees in the department

-- V1__Initial_Migration.sql
CREATE TABLE example_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

-- Create the 'department' table if not exists
CREATE TABLE IF NOT EXISTS department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manager VARCHAR(255)
);

-- Create the 'employee_address' table if not exists
CREATE TABLE IF NOT EXISTS employee_address (
    id SERIAL PRIMARY KEY,
    street_name VARCHAR(255) NOT NULL,
    house_name VARCHAR(255) NOT NULL,
    pin_code INT NOT NULL
);

-- Create the 'employee' table if not exists
CREATE TABLE IF NOT EXISTS employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    experience VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    data_birth DATE NOT NULL,
    role VARCHAR(50) NOT NULL,
    address_id INT,
    department_id INT,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES employee_address (id) ON DELETE SET NULL,
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE SET NULL
);

-- Create the 'mission' table if not exists
CREATE TABLE IF NOT EXISTS mission (
    id SERIAL PRIMARY KEY,
    name_of_the_mission VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL
);

-- Create the 'employee_mission' join table if not exists
CREATE TABLE IF NOT EXISTS employee_mission (
    employee_id INT NOT NULL,
    mission_id INT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE,
    CONSTRAINT fk_mission FOREIGN KEY (mission_id) REFERENCES mission (id) ON DELETE CASCADE,
    PRIMARY KEY (employee_id, mission_id)
);

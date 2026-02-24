-- =============================================
-- EMPLOYEES
-- =============================================
INSERT INTO employee (first_name, last_name, username, password, role) VALUES ('Admin',      'Adminović',     'admin',     '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq', 'ADMIN');
INSERT INTO employee (first_name, last_name, username, password, role) VALUES ('Cashier',    'Kasirović',     'cashier',   '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq', 'CASHIER');
INSERT INTO employee (first_name, last_name, username, password, role) VALUES ('Principal',  'Direktorović',  'principal', '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq', 'PRINCIPAL');
INSERT INTO employee (first_name, last_name, username, password, role) VALUES ('Cook',       'Kuvović',       'cook',      '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq', 'COOK');

-- =============================================
-- STUDENTS  (id=1 → BUDGET, id=2 → SELF_FINANCE)
-- =============================================
INSERT INTO student (first_name, last_name, index_number, faculty_name, status, city, username, password) VALUES ('Marko', 'Marković',  'E123/2024', 'Faculty of Technical Sciences', 'BUDGET',       'Novi Sad', 'student',  '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq');
INSERT INTO student (first_name, last_name, index_number, faculty_name, status, city, username, password) VALUES ('Ana',   'Anić',      'E456/2024', 'Faculty of Sciences',           'SELF_FINANCE', 'Belgrade', 'student2', '$2a$12$hrCIEjUnL6SbrMIEqhMQGufUlgqY.G5buk/BrYmqXtiLF7zYIUkhq');

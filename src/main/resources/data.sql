DROP TABLE IF EXISTS qr_code;

CREATE TABLE qr_code (
  id BIGSERIAL PRIMARY KEY,
  uniq_code VARCHAR(255) UNIQUE NOT NULL,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE "user"
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(255) UNIQUE   NOT NULL,
    password   VARCHAR(255)          NOT NULL
);

CREATE TABLE role
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES "user"(id),
    role_id BIGINT REFERENCES role(id)
);

INSERT INTO "user" (id, username, password)
VALUES (1, 'admin', '{bcrypt}$2a$10$WJvAKW5R1VM2SSaAWf0WYO/FBcovz6X3BpulRoS2FWdUbcCZPo8V2'),
       (2, 'user', '{bcrypt}$2a$10$WJvAKW5R1VM2SSaAWf0WYO/FBcovz6X3BpulRoS2FWdUbcCZPo8V2');

INSERT INTO role (id, name)
VALUES (1, 'USER'),
       (2, 'ADMIN');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO qr_code(uniq_code, name, path)
    VALUES (45846485, 'burger', 'C:\Users\Dudlis\IdeaProjects\employeemanager\barcodeGenerator\src\main\resources\codeDirectory\burger.jpg'),
           (45846486, 'piza', 'C:\Users\Dudlis\IdeaProjects\employeemanager\barcodeGenerator\src\main\resources\codeDirectory\piza.jpg');
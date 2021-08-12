DROP TABLE IF EXISTS qr_code;

CREATE TABLE qr_code (
  id BIGSERIAL PRIMARY KEY,
  uniq_code VARCHAR(255) UNIQUE NOT NULL,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO qr_code(uniq_code, name, path)
    VALUES (45846485, 'burger', 'C:\Users\Dudlis\IdeaProjects\employeemanager\barcodeGenerator\src\main\resources\codeDirectory\burger.jpg'),
           (45846486, 'piza', 'C:\Users\Dudlis\IdeaProjects\employeemanager\barcodeGenerator\src\main\resources\codeDirectory\piza.jpg');
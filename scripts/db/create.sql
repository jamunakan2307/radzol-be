DROP USER IF EXISTS razdol;
DROP DATABASE IF EXISTS razdol;

CREATE DATABASE DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
GRANT ALL PRIVILEGES ON razdol.* TO razdol IDENTIFIED BY 'Admin1!';
SET SESSION time_zone = UTC;
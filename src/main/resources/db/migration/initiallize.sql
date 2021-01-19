USE mysql;

CREATE DATABASE practice DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

CREATE USER 'dhk'@'%' IDENTIFIED BY 'dhk';
CREATE USER 'dhk'@'localhost' IDENTIFIED BY 'dhk';

-- DB 별 권한 부여
GRANT ALL PRIVILEGES ON practice.* TO 'dhk'@'localhost';
GRANT ALL PRIVILEGES ON practic.* TO 'dhk'@'%';

-- 마무리.
FLUSH PRIVILEGES;
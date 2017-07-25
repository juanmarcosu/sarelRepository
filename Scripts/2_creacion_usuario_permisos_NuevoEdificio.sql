/*

DROP USER 'userlaboratorio'@'localhost';
DROP USER 'userlaboratorio'@'127.0.0.1';
FLUSH PRIVILEGES;

*/

CREATE USER 'userlaboratorio'@'localhost' IDENTIFIED BY 'l@bud$@lud';
CREATE USER 'userlaboratorio'@'127.0.0.1' IDENTIFIED BY 'l@bud$@lud';
GRANT ALL PRIVILEGES  ON websalud.paciente TO 'userlaboratorio'@'localhost' IDENTIFIED BY 'l@bud$@lud';
GRANT ALL PRIVILEGES  ON websalud.paciente TO 'userlaboratorio'@'127.0.0.1' IDENTIFIED BY 'l@bud$@lud';
GRANT ALL PRIVILEGES  ON laboratorio.* TO 'userlaboratorio'@'localhost' IDENTIFIED BY 'l@bud$@lud';
GRANT ALL PRIVILEGES  ON laboratorio.* TO 'userlaboratorio'@'127.0.0.1' IDENTIFIED BY 'l@bud$@lud';
FLUSH PRIVILEGES;


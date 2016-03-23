/*All User's are stored in APP_USER table*/
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   state VARCHAR(30) NOT NULL,  
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
  
/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
  
/* JOIN TABLE for MANY-TO-MANY relationship*/ 
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* Populate CONSULTOR_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('CONSULTOR');
 
INSERT INTO USER_PROFILE(type)
VALUES ('LABORATORISTA');
 
INSERT INTO USER_PROFILE(type)
VALUES ('ADMINISTRADOR');
 
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('juanmarcos','123', 'juanmarcos','AdminYLaboratorista','juanmarcos@xyz.com', 'Active');
 
INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile  
  where USER.sso_id='juanmarcos' and profile.type='ADMINISTRADOR';

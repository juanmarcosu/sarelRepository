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

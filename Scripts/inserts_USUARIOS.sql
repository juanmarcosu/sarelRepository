/* Populate CONSULTOR_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('CONSULTOR');
 
INSERT INTO USER_PROFILE(type)
VALUES ('LABORATORISTA');
 
INSERT INTO USER_PROFILE(type)
VALUES ('ADMINISTRADOR');
 
/* Populate APP_CONSULTOR Table */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('doctor','123', 'doctor','Watcher','doctor@xyz.com', 'Active');
 
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('laboratorista','123', 'laboratorista','Writer','laboratoristay@xyz.com', 'Active');
 
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('administrador','123', 'administrador','soloAdmin','nicloe@xyz.com', 'Active');
 
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
VALUES ('juanmarcos','123', 'juanmarcos','AdminYLaboratorista','juanmarcos@xyz.com', 'Active');

INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile  
  where USER.sso_id='doctor' and profile.type='CONSULTOR';
 
INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile
  where USER.sso_id='laboratorista' and profile.type='LABORATORISTA';
 
INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile
  where USER.sso_id='administrador' and profile.type='ADMINISTRADOR';
 
INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile  
  where USER.sso_id='juanmarcos' and profile.type='LABORATORISTA';
 
INSERT INTO APP_USER_USER_PROFILE (USER_id, USER_profile_id)
  SELECT USER.id, profile.id FROM APP_USER USER, USER_PROFILE profile  
  where USER.sso_id='juanmarcos' and profile.type='ADMINISTRADOR';

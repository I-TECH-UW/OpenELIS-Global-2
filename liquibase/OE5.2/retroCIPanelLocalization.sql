INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Cytobacteriologie', 'Cytobacteriologie', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Cytobacteriologie';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Examen de Selles', 'Examen de Selles', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Examen de Selles';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Hemogramme', 'Hemogramme', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Hemogramme';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Malaria', 'Malaria', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Malaria';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'NFS', 'NFS', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'NFS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Urines', 'Urines', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Urines';
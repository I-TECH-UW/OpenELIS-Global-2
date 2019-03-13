INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Antibiogramme', 'Antibiogramme', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Antibiogramme';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Hemogramme-Manual', 'Hemogramme-Manual', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Hemogramme-Manual';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Malaria', 'Malaria', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Malaria';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Miscellaneous  Bacteriologie', 'Miscellaneous  Bacteriologie', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Miscellaneous  Bacteriologie';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Recherche de virus respiratoire par immunofuorescence directe', 'Recherche de virus respiratoire par immunofuorescence directe', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Recherche de virus respiratoire par immunofuorescence directe';

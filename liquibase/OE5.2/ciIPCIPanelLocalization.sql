INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Antifongigramme', 'Antifongigramme', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Antifongigramme';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Bilan Biochimique', 'Bilan Biochimique', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Bilan Biochimique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', $$Examen d'urines$$, $$Examen d'urines$$, now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = $$Examen d'urines$$;
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'GE / FS', 'GE / FS', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'GE / FS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'KOP', 'KOP', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'KOP';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Mycose Superficielle', 'Mycose Superficielle', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Mycose Superficielle';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Mycoses Profondes', 'Mycoses Profondes', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Mycoses Profondes';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'NFS', 'NFS', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'NFS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie Amibienne', 'Serologie Amibienne', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie Amibienne';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie Bilharzienne', 'Serologie Bilharzienne', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie Bilharzienne';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie Toxoplasmique', 'Serologie Toxoplasmique', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie Toxoplasmique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie VIH', 'Serologie VIH', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie VIH';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Typage lymphocytaire', 'Typage lymphocytaire', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Typage lymphocytaire';
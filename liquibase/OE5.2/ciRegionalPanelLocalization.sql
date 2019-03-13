INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Bilan Biochimique', 'Bilan Biochimique', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Bilan Biochimique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'NFS', 'NFS', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'NFS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie VIH', 'Serologie VIH', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie VIH';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Typage lymphocytaire', 'Typage lymphocytaire', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Typage lymphocytaire';

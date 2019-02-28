INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'CD4', 'CD4', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'CD4';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'NFS', 'NFS', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'NFS';

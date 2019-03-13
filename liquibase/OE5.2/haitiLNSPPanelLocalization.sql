INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'CD4', 'CD4', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'CD4';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Coloration de Gram', 'Coloration de Gram', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Coloration de Gram';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Culture', 'Culture', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Culture';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Hemogramme', 'Hemogramme', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Hemogramme';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Méningite-PCR', 'Méningite-PCR', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Méningite-PCR';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Recherche de Bordetella', 'Recherche de Bordetella', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Recherche de Bordetella';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Selles Routine', 'Selles Routine', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Selles Routine';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Serologie-Virologie', 'Serologie-Virologie', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Serologie-Virologie';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'VIH-1 Charge Virale', 'VIH-1 Charge Virale', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'VIH-1 Charge Virale';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Xpert MTB/RIF', 'Xpert MTB/RIF', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Xpert MTB/RIF';
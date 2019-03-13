INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Virology', 'Virologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.virologie';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'user', 'user', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where name = 'user';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Molecular Biology', 'Biologie Moleculaire', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Moleoularbiology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hemto-Immunology', 'Hemto-Immunology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.hemtoImmunology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Serology-Immunology', 'Serology-Immunology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.serologyImmunology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Immunology', 'Immuno-Virologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.immuno';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'VCT', 'CDV', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.VCT.Haiti';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hematology', 'Hématologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.hematology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Biochemistry', 'Biochimie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.biochemistry';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Cytobacteriology', 'Cytobacteriologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Cytobacteriologie';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Bacteria', 'Bactériologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.bacteria';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'ECBU', 'ECBU', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.ECBU';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Parasitology', 'Parasitologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.parasitology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Mycobacteriology', 'Mycobactériologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.mycobacteriology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Endocrinology', 'Endocrinologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Endocrinology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Liquid Biology', 'Liquides biologique', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Liquidbiology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Serology', 'Sérologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.serology';

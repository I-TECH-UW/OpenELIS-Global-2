INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Immunology', 'Immunologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.immunology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Bacteria', 'Bactériologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.bacteria';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Parasitology', 'Parasitologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.parasitology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'ECBU', 'ECBU', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.ECBU';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'VCT', 'VCT', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.VCT';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Virology', 'Virologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.virologie';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Mycobacteriology', 'Mycobactériologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.mycobacteriology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Molecular Biology', 'Biologie Moleculaire', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Moleoularbiology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Serology', 'Sérologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.serology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hemato-Immunology', 'Hémato-Immunologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.hemato-immunology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Biochemistry', 'Biochimie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.biochemistry';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hematology', 'Hématologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.hematology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'user', 'user', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where name = 'user';

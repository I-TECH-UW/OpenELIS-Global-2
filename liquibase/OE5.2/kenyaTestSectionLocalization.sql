INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Biochemistry', 'Biochemistry', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Biochemistry';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Mycology', 'Mycology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Mycology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Parasitology', 'Parasitology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Parasitology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Molecular Biology', 'Molecular Biology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Molecular';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hematology', 'Hematology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Hematology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Serology-Immunology', 'Serology-Immunology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Serology-Immunology';

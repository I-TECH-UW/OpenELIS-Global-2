INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Biochemistry', 'Biochimie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'testSection.Biochemistry';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Serology', 'Sérologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'testSection.Serology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Hematology', 'Hématologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'testSection.Hematology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Immunology', 'Immunologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'testSection.Immunology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Virology', 'Virologie', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'testSection.Virology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'user', 'user', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key is NULL;

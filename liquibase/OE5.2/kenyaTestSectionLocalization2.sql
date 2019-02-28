INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Microscopy', 'Microscopy', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Microscopy';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Bacteriology', 'Bacteriology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Bacteriology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Blood Bank', 'Blood Bank', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Blood';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test unit name', 'Histology / Cytology', 'Histology / Cytology', now());
UPDATE test_section set name_localization_id = currval('localization_seq') where display_key = 'test.section.Histology';
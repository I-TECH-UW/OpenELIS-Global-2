INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Blood Chemistry', 'Blood Chemistry', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Blood Chemistry';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Blood Smears', 'Blood Smears', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Blood Smears';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Body fluids', 'Body fluids', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Body fluids';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'CSF chemistry', 'CSF chemistry', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'CSF chemistry';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Fluid Cytology', 'Fluid Cytology', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Fluid Cytology';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Genital Smears', 'Genital Smears', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Genital Smears';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Lipid profile', 'Lipid profile', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Lipid profile';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Liver function tests', 'Liver function tests', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Liver function tests';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Skin snips', 'Skin snips', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Skin snips';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Smears', 'Smears', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Smears';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Spleen/bone marrow', 'Spleen/bone marrow', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Spleen/bone marrow';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Stool', 'Stool', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Stool';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Thyroid function tests', 'Thyroid function tests', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Thyroid function tests';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Tissue Histology', 'Tissue Histology', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Tissue Histology';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Urine Chemistry', 'Urine Chemistry', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Urine Chemistry';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Urine Microscopy', 'Urine Microscopy', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Urine Microscopy';
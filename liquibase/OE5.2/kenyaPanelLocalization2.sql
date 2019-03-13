INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Urine Test Strip', 'Urine Test Strip', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Urine Test Strip';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Full blood count', 'Full blood count', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Full blood count';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Coagulation Test', 'Coagulation Test', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Coagulation Test';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Lymphocyte Subsets', 'Lymphocyte Subsets', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Lymphocyte Subsets';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Renal function tests', 'Renal function tests', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Renal function tests';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Thyroid Function Tests', 'Thyroid Function Tests', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Thyroid Function Tests';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Cytology', 'Cytology', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Cytology';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'panel name', 'Histology', 'Histology', now());
UPDATE panel set name_localization_id = currval('localization_seq') where name = 'Histology';

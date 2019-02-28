INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Variable', 'Varié', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Actual type will be selected by user';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'DBS', 'DBS', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'DBS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Dry Tube', 'Tube Sec - Rouge', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Dry Tube';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'EDTA Tube', 'Tube EDTA - Violet', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'EDTA Tube';
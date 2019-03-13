INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Variable', 'Varié', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Actual type will be selected by user';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Biopsie', 'Biopsie', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Biopsie';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'LCR', 'LCR', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'LCR';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Plasma', 'Plasma', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Plasma';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sang total', 'Sang total', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang total';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Scotch Test Anal', 'Scotch Test Anal', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Scotch Test Anal';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Selles', 'Selles', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Selles';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sérum', 'Sérum', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sérum';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urines', 'Urines', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urines';

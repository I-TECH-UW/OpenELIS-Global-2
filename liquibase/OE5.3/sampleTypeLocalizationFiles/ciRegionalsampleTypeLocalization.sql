INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Variable', 'Varié', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Actual type will be selected by user';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Plasma', 'Plasma', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Plasma';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sang total', 'Sang total', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang total';
UPDATE type_of_sample set description='Serum', local_abbrev='Serum' where id = 2;
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Serum', 'Sérum', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description like 'Serum';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urines', 'Urines', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urines';

INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Variable', 'Varié', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Actual type will be selected by user';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Gram Stain', 'Coloration de Gram', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Coloration de Gram';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Culture', 'Culture', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Culture';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'DBS', 'DBS', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'DBS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Eau de riviere', 'Eau de riviere', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Eau de riviere';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillon Nasal', 'Ecouvillon Nasal', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillon Nasal';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillon Naso-Pharynge', 'Ecouvillon Naso-Pharynge', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillon Naso-Pharynge';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillon Pharinge', 'Ecouvillon Pharinge', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillon Pharinge';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillon Pharynge', 'Ecouvillon Pharynge', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillon Pharynge';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Free text', 'Free text', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Free text';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'LCR', 'LCR', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'LCR';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Plasma', 'Plasma', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Plasma';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sang', 'Sang', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sang capillaire', 'Sang capillaire', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang capillaire';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sang Total', 'Sang Total', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang Total';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Selles', 'Selles', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Selles';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Selles 1', 'Selles 1', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Selles 1';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Selles 2', 'Selles 2', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Selles 2';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sérum', 'Sérum', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sérum';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sputum', 'Crachats', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sputum';
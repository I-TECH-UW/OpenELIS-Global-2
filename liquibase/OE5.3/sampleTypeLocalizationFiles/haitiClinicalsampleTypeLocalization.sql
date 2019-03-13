INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Variable', 'Varié', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Actual type will be selected by user';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Aspiration nasopharyngée', 'Aspiration nasopharyngée', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Aspiration nasopharyngée';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Spit', 'Crachat', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Crachat';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Culot Urinaire', 'Culot Urinaire', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Culot Urinaire';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'DBS', 'DBS', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'DBS';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillonage nasal', 'Ecouvillonage nasal', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillonage nasal';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ecouvillonage nosapharyngé', 'Ecouvillonage nosapharyngé', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Ecouvillonage nosapharyngé';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Expectoration', 'Expectoration', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Expectoration';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'In Vivo', 'In Vivo', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'In Vivo';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'CFS', 'LCR', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'LCR';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'LCR/CSF', 'LCR/CSF', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'LCR/CSF';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ambionic fluid', 'Liquide Amniotique', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Amniotique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Ascite fluid', 'Liquide Ascite', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Ascite';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Liquide Biologique', 'Liquide Biologique', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Biologique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Pleural fluid', 'Liquide Pleural', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Pleural';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Liquide Spermatique', 'Liquide Spermatique', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Spermatique';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Synovial fluid', 'Liquide Synovial', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Liquide Synovial';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Plasma', 'Plasma', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Plasma';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Plasma hepariné', 'Plasma hepariné', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Plasma hepariné';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Prélèvement rhinopharyngé', 'Prélèvement rhinopharyngé', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Prélèvement rhinopharyngé';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Pus', 'Pus', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Pus';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Blood', 'Sang', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sang';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sécrétion de la gorge', 'Sécrétion de la gorge', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sécrétion de la gorge';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urethral', 'Secretion Urethrale', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Secretion Urethrale';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Secretion vaginale', 'Secretion vaginale', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Secretion vaginale';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Vaginal', 'Secretion Vaginale', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Secretion Vaginale';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Secretions genito-urinaire', 'Secretions genito-urinaire', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Secretions genito-urinaire';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Stool', 'Selles', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Selles';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Serum', 'Sérum', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Serum';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Serum/Urine Concentre du Matin', 'Sérum/Urine Concentre du Matin', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Serum/Urine Concentre du Matin';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Sputum', 'Crachats', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Sputum';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urine', 'Urine', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urine';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urine concentré du matin', 'Urine concentré du matin', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urine concentré du matin';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urines', 'Urines', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urines';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES(nextval('localization_seq'), 'sampleType name', 'Urines/24 heures', 'Urines/24 heures', now());
UPDATE type_of_sample set name_localization_id = currval('localization_seq') where description = 'Urines/24 heures';
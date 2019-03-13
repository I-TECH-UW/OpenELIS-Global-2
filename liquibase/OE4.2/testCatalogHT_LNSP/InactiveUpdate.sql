update clinlims.test set GUID='PSEUDO_GUID_56' where description =  'VIH-1 PCR Qualitatif(DBS)';
update clinlims.test set GUID='PSEUDO_GUID_57' where description = 'VIH-1 PCR Qualitatif(Sang Total)';
update clinlims.test set GUID='PSEUDO_GUID_58' where description = 'Rougeole(Plasma)';
update clinlims.test set GUID='PSEUDO_GUID_59' where description = 'Rubeole(Plasma)';
update clinlims.test set GUID='PSEUDO_GUID_60' where description = 'Rougeole(Sérum)';
update clinlims.test set GUID='PSEUDO_GUID_61' where description = 'Rubeole(Sérum)';
update clinlims.test set GUID='PSEUDO_GUID_62' where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)';
update clinlims.test set GUID='PSEUDO_GUID_63' where description = 'Dengue(Plasma)';
update clinlims.test set GUID='PSEUDO_GUID_64' where description = 'Dengue(Sérum)';


update clinlims.test set local_code='VIH-1 PCR Qualitatif-DBS' where guid = 'PSEUDO_GUID_56';
update clinlims.test set local_code='VIH-1 PCR Qualitatif-Sang Total' where guid = 'PSEUDO_GUID_57';
update clinlims.test set local_code='Rougeole-Plasma' where guid = 'PSEUDO_GUID_58';
update clinlims.test set local_code='Rubeole-Plasma' where guid = 'PSEUDO_GUID_59';
update clinlims.test set local_code='Rougeole-Sérum' where guid = 'PSEUDO_GUID_60';
update clinlims.test set local_code='Rubeole-Sérum' where guid = 'PSEUDO_GUID_61';
update clinlims.test set local_code='Virus Respiratoire-Ecouvillon Naso-Pharynge' where guid = 'PSEUDO_GUID_62';
update clinlims.test set local_code='Dengue-Plasma' where guid = 'PSEUDO_GUID_63';
update clinlims.test set local_code='Dengue-Sérum' where guid = 'PSEUDO_GUID_64';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'HIV-1 PCR Qualitative', (select name from clinlims.test where guid = 'PSEUDO_GUID_56' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_56';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','HIV-1 PCR Qualitative' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_56' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_56';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'HIV-1 PCR Qualitative', (select name from clinlims.test where guid = 'PSEUDO_GUID_57' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_57';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','HIV-1 PCR Qualitative' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_57' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_57';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Measles', (select name from clinlims.test where guid = 'PSEUDO_GUID_58' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_58';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Measles' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_58' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_58';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Rubella', (select name from clinlims.test where guid = 'PSEUDO_GUID_59' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_59';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Rubella' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_59' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_59';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Measles', (select name from clinlims.test where guid = 'PSEUDO_GUID_60' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_60';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Measles' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_60' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_60';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Rubella', (select name from clinlims.test where guid = 'PSEUDO_GUID_61' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_61';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Rubella' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_61' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_61';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Respiratory virus', (select name from clinlims.test where guid = 'PSEUDO_GUID_62' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_62';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Respiratory virus' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_62' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_62';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Dengue', (select name from clinlims.test where guid = 'PSEUDO_GUID_63' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_63';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Dengue' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_63' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_63';

INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', 'Dengue', (select name from clinlims.test where guid = 'PSEUDO_GUID_64' ), now());
update clinlims.test set name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_64';
INSERT INTO clinlims.localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name','Dengue' , (select reporting_description from clinlims.test where guid = 'PSEUDO_GUID_64' ), now());
update clinlims.test set reporting_name_localization_id = currval( 'localization_seq' ) where guid ='PSEUDO_GUID_64';

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Chikungunya Test Rapide(Sérum)' , 'Chikungunya Test Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Chikungunya Test Rapide' ,(select sort_order + 2 from clinlims.test where description = 'Criptococcus test rapide(Plasma)') , 'Chikungunya Test Rapide' , '');

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Recherche Virus Chikungunya(Sérum)' , 'Recherche Virus Chikungunya' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'Chikungunya Test Rapide' ,(select sort_order + 1 from clinlims.test where description = 'N. meningitidis(LCR)') , 'Recherche Virus Chikungunya' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chikungunya Test Rapide(Sérum)' )  ,    (select id from type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche Virus Chikungunya(Sérum)' )  ,    (select id from type_of_sample where description = 'Sérum')  );

update clinlims.test_result set is_quantifiable = true where id in (select max(id) from clinlims.test_result where test_id in (select id from clinlims.test where description = 'S. pneumonaiae(LCR)'));
update clinlims.test_result set is_quantifiable = true where id in (select max(id) from clinlims.test_result where test_id in (select id from clinlims.test where description = 'H. influenzae(LCR)'));
update clinlims.test_result set is_quantifiable = true where id in (select max(id) from clinlims.test_result where test_id in (select id from clinlims.test where description = 'N. meningitidis(LCR)'));

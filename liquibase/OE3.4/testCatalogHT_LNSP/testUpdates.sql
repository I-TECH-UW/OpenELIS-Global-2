--update panel name
update clinlims.panel set name='Hemogramme' where name='Hemogramme-Auto';

--insert new dictionary
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id )
VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'ADN VIH-1 Indeterminé' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));

--insert new panel
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'panel_seq' ) , 'Méningite-PCR' , 'Méningite-PCR' , now() ,'panel.name.meningitis' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'LCR' ) , (select id from clinlims.panel where name = 'Méningite-PCR' ) );



--row 14
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Western Blot(DBS)' and is_active = 'Y' ) );

--53
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Test Rapide Rotavirus(Selles)' , 'Test Rapide Rotavirus' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Test Rapide Rotavirus' ,505 , 'Test Rapide Rotavirus' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Rotavirus(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Rotavirus(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide Rotavirus(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Rotavirus(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

--58
UPDATE clinlims.result_limits SET normal_dictionary_id = (SELECT id FROM clinlims.dictionary WHERE dict_entry = 'Non-Reactif')
WHERE test_id = ( select id from clinlims.test where description = 'Syphilis RPR(Sérum)');

--60
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Criptococcus test rapide(Sérum)' , 'Criptococcus test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Criptococcus test rapide' ,552 , 'Criptococcus test rapide' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Criptococcus test rapide(Sérum)' )  ,    (select id from type_of_sample where description = 'Sérum')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Sérum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

--61
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Criptococcus test rapide(LCR)' , 'Criptococcus test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Criptococcus test rapide' ,555 , 'Criptococcus test rapide' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Criptococcus test rapide(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(LCR)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

--62
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'Criptococcus test rapide(Plasma)' , 'Criptococcus test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Criptococcus test rapide' ,557 , 'Criptococcus test rapide' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Criptococcus test rapide(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Criptococcus test rapide(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

--63
update clinlims.test set is_active='N' where description = 'VIH-1 PCR Qualitatif(DBS)';

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 1 Qualitatif(DBS)' , 'VIH-1 PCR 1 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 1 Qualitatif' ,560 , 'VIH-1 PCR 1 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 13);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 17);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 1 Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--64

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 2 Qualitatif(DBS)' , 'VIH-1 PCR 2 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 2 Qualitatif' ,562 , 'VIH-1 PCR 2 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 2 Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--65

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 3 Qualitatif(DBS)' , 'VIH-1 PCR 3 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 3 Qualitatif' ,564 , 'VIH-1 PCR 3 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 3 Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );


--66

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 4 Qualitatif(DBS)' , 'VIH-1 PCR 4 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 4 Qualitatif' ,566 , 'VIH-1 PCR 4 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 4 Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--67

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 5 Qualitatif(DBS)' , 'VIH-1 PCR 5 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 5 Qualitatif' ,568 , 'VIH-1 PCR 5 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 5 Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--68
update clinlims.test set is_active='N' where description = 'VIH-1 PCR Qualitatif(Sang Total)';

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 1 Qualitatif(Sang Total)' , 'VIH-1 PCR 1 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 1 Qualitatif' ,570 , 'VIH-1 PCR 1 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 13);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 17);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 1 Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 1 Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--69

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 2 Qualitatif(Sang Total)' , 'VIH-1 PCR 2 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 2 Qualitatif' ,572 , 'VIH-1 PCR 2 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 2 Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 2 Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--70

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 3 Qualitatif(Sang Total)' , 'VIH-1 PCR 3 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 3 Qualitatif' ,574 , 'VIH-1 PCR 3 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 3 Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 3 Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );


--71

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 4 Qualitatif(Sang Total)' , 'VIH-1 PCR 4 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 4 Qualitatif' ,576 , 'VIH-1 PCR 4 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 4 Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 4 Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--72

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'VIH-1 PCR 5 Qualitatif(Sang Total)' , 'VIH-1 PCR 5 Qualitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'VIH-1 PCR 5 Qualitatif' ,578 , 'VIH-1 PCR 5 Qualitatif' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Non-Détecté' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Détecté' )  , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Indeterminé' )  , now() , 15);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR 5 Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR 5 Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) );

--80

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'S. pneumonaiae(LCR)' , 'S. pneumonaiae' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'S. pneumonaiae' ,643 , 'S. pneumonaiae' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'S. pneumonaiae(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'S. pneumonaiae(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15, true );

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'S. pneumonaiae(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'S. pneumonaiae(LCR)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Méningite-PCR') , now(), null,  (select id from test where description = 'S. pneumonaiae(LCR)' and is_active = 'Y' ) );

--81
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'H. influenzae(LCR)' , 'H. influenzae' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'H. influenzae' ,645 , 'H. influenzae' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'H. influenzae(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'H. influenzae(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15, true );

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'H. influenzae(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'H. influenzae(LCR)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Méningite-PCR') , now(), null,  (select id from test where description = 'H. influenzae(LCR)' and is_active = 'Y' ) );

--82

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'test_seq' ) , null , 'N. meningitidis(LCR)' , 'N. meningitidis' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'N. meningitidis' ,648 , 'N. meningitidis' , '');

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'N. meningitidis(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'N. meningitidis(LCR)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 15, true );

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'sample_type_test_seq' ) , (select id from test where description = 'N. meningitidis(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'N. meningitidis(LCR)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Méningite-PCR') , now(), null,  (select id from test where description = 'N. meningitidis(LCR)' and is_active = 'Y' ) );

--98
update clinlims.test set sort_order=773 where description='Monocytes(Sang Total)';

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme') , now(), null,  (select id from test where description = 'Monocytes(Sang Total)' and is_active = 'Y' ) );

--99

update clinlims.test set sort_order=775 where description='Eosinophiles(Sang Total)';

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme') , now(), null,  (select id from test where description = 'Eosinophiles(Sang Total)' and is_active = 'Y' ) );

--100

update clinlims.test set sort_order=778 where description='Basophiles(Sang Total)';

INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme') , now(), null,  (select id from test where description = 'Basophiles(Sang Total)' and is_active = 'Y' ) );

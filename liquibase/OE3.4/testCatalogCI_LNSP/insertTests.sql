INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc, orderable )
  VALUES ( nextval( 'test_seq' ) , null , 'Glucosurie sur bandelette(Urine)' , '' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glucosurie sur bandelette' ,442 , 'Glucosurie sur bandelette' , '', true),
( nextval( 'test_seq' ) , null , 'Genie III(Plasma)' , '' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Genie III' ,503 , 'Genie III' , '', true),
( nextval( 'test_seq' ) , null , 'Genie III(Sérum)' , '' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Genie III' ,504 , 'Genie III' , '', true),
( nextval( 'test_seq' ) , null , 'Genie III(Sang total)' , '' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Genie III' ,505 , 'Genie III' , '', true);

INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action )
  VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)'),'DISPLAY'),
  ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Plasma)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Plasma)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Sérum)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Sérum)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Sang total)'),'DISPLAY'),
( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Genie III(Sang total)'),'DISPLAY');

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
  VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) ),
( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) ),
( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) ),
( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );

INSERT INTO test_code( test_id, code_type_id, value, lastupdated)
  VALUES ( (select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'UB21', now() ),
   ( (select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), 'B10', now() );

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
  VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif  (50 mg/dL)' )  , now() , 20),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif  (100  mg/dL)' )  , now() , 30),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif + (250 mg/dL)' )  , now() , 40),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++ (500 mg/dL)' )  , now() , 50),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +++ (1000 mg/dL)' )  , now() , 60),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucosurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++++ (2000 mg/dL)' )  , now() , 70),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 20),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 30),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 40),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1 et 2' )  , now() , 50),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 20),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 30),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 40),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1 et 2' )  , now() , 50),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 20),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 30),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 40),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1 et 2' )  , now() , 50);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glucosurie sur bandelette(Urine)' )  ,    (select id from type_of_sample where local_abbrev = 'Urines')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Plasma)' )  ,    (select id from type_of_sample where local_abbrev = 'Plasma')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Sérum)' )  ,    (select id from type_of_sample where local_abbrev like 'S%rum')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );




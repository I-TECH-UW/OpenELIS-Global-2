INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc, orderable )
	VALUES ( nextval( 'test_seq' ) , null , 'Culture des Selles(Selles)' , '' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Culture des Selles' ,580 , 'Culture des Selles' , '', true);

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture des Selles(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );

INSERT INTO dictionary_category( id, description, lastupdated, local_abbrev, "name")
  VALUES ( nextval( 'dictionary_category_seq' ), 'Negative bacteriology results', now() , 'negBact', 'negative bacteriology'),
  ( nextval( 'dictionary_category_seq' ), 'Positive bacteriology results', now() , 'posBact', 'positive bacteriology');

INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id )
  VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Negative pour Vibro chlorea O1 Ogawa, Salmonella et Shigella' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'negBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Negative pour Salmonella et Shigella' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'negBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Negative pour Salmonella' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'negBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Negative pour Shigella' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'negBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Negative pour Vibro cholerae O1 Ogawa' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'negBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Vibro cholerae O1 Ogawa' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Vibro cholerae O1 Inaba' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Vibro cholerae O139' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Vibro cholerae O1' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Vibro cholerae Non-O1' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Samonella Typhi' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Samonella Enterica' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Samonella sp.' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Shigella flexneri' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Shigella boydii' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Shigella sonnei' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' )),
  ( nextval( 'dictionary_seq' ) , 'Y' , 'Positive pour Shigella dysenteriae' , now(), ( select id from clinlims.dictionary_category where local_abbrev = 'posBact' ));

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
  VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negative pour Vibro chlorea O1 Ogawa, Salmonella et Shigella' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negative pour Salmonella et Shigella' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negative pour Salmonella' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negative pour Shigella' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negative pour Vibro cholerae O1 Ogawa' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Vibro cholerae O1 Ogawa' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Vibro cholerae O1 Inaba' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Vibro cholerae O139' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Vibro cholerae O1' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Vibro cholerae Non-O1' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Samonella Typhi' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Samonella Enterica' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Samonella sp.' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Shigella flexneri' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Shigella boydii' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Shigella sonnei' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positive pour Shigella dysenteriae' )  , now() , 10);

INSERT INTO analyte( id, "name", is_active,  lastupdated )
  VALUES ( nextval('analyte_seq'), 'Culture des Selles Results', 'Y', now() );

INSERT INTO test_analyte(  id, test_id, analyte_id, result_group, sort_order,   lastupdated )
  VALUES
  ( nextval('test_analyte_seq'),    (select id from clinlims.test where description = 'Culture des Selles(Selles)') ,  (select id from clinlims.analyte where name = 'Culture des Selles Results'), 10, 1, now() );

INSERT INTO test_reflex(  id, tst_rslt_id, flags, lastupdated, test_analyte_id, test_id,  add_test_id, sibling_reflex, scriptlet_id)
  VALUES
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Ogawa') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1 Inaba') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O139') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Vibro cholerae Non-O1') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Typhi') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella Enterica') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Samonella sp.') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella flexneri') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella boydii') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),




  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella sonnei') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null ),






  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline 25 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Imipénème 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Pipéracilline / Tazobactan 75 / 10 µg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Culture des Selles(Selles)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positive pour Shigella dysenteriae') as varchar )),
    'UC',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Culture des Selles(Selles)' )),
    ( select id from clinlims.test where description = 'Culture des Selles(Selles)' ),
    ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)') , null, null );





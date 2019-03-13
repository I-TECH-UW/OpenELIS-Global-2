INSERT INTO clinlims.dictionary(  id, is_active, dict_entry, lastupdated, dictionary_category_id, sort_order)
VALUES ( nextval( 'dictionary_seq' ) , 'Y','Non-Detecte' , now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136300),
  ( nextval( 'dictionary_seq' ) , 'Y', 'Detecte', now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136400),
  ( nextval( 'dictionary_seq' ) , 'Y','Aucun' , now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136500),
  ( nextval( 'dictionary_seq' ) , 'Y','Peu' , now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136600),
  ( nextval( 'dictionary_seq' ) , 'Y','Modere' , now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136700),
  ( nextval( 'dictionary_seq' ) , 'Y','Beaucoup' , now() , (select id from clinlims.dictionary_category where description = 'Haiti Lab'), 136800);

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' ) ,
         'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non-Detecte' )  , now() , 10, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Detecte'  )  , now() , 20, false),

  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Non-Detecte'  )  , now() , 30, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Detecte'  )  , now() , 40, false),

  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Aucun'  )  , now() , 50, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Peu'  )  , now() , 60, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Modere'  )  , now() , 70, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Beaucoup'  )  , now() , 80, false),

  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Aucun'  )  , now() , 10, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Peu'  )  , now() , 20, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Modere'  )  , now() , 30, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Beaucoup'  )  , now() , 40, false),

  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Aucun'  )  , now() , 10, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Peu'  )  , now() , 20, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Modere'  )  , now() , 30, false),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) ,
    'D' ,  ( select max(id) from clinlims.dictionary where dict_entry = 'Beaucoup'  )  , now() , 40, false);



  delete from clinlims.test_result where tst_rslt_type = 'D' and value is null;


INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, lastupdated, normal_dictionary_id )
VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,
         0, 'Infinity' , '' , now(), ( select max(id) from clinlims.dictionary where dict_entry = 'Non-Detecte' ) ),
  ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,
  0, 'Infinity' , '' , now(), ( select max(id) from clinlims.dictionary where dict_entry = 'Non-Detecte' ) );

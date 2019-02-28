delete FROM clinlims.test_result where test_id = ( select id from clinlims.test where name like '%sur bandelette' );

INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
  VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 10),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='+/- 0.15 g/L' )  , now() , 20),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif + (0.3g/L)' )  , now() , 30),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++ (1.0 g/L)' )  , now() , 40),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +++ (3.0 g/L)' )  , now() , 50),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where name like '%sur bandelette' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++++ (>= 5.0 g/L)' )  , now() , 60);


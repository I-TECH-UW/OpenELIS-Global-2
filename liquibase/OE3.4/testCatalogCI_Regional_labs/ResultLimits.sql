INSERT INTO result_limits(  id, test_id, test_result_type_id, normal_dictionary_id, lastupdated)
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat-Pak(Plasma)' ) ,
            (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() ),
   ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat-Pak(Sérum)' ) ,
     (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() ),
   ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat-Pak(Sang total)' ) ,
     (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() ),
   ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) ,
     (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() ),
   ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Sérum)' ) ,
     (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() ),
   ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang total)' ) ,
     (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,( select max(id) from clinlims.dictionary where dict_entry ='Négatif' ) , now() );

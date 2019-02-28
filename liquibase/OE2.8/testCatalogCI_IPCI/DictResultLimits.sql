	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Murex(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );

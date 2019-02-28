INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Sang total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Vironstika(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Integral(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Western blot 1(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Western blot 2(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'p24Ag(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'DNA PCR(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Négatif' ) );

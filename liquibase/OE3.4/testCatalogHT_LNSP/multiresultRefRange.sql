INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Non Reactif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry like 'ADN VIH-1 Non%' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry like 'ADN VIH-1 Non%' ) );
--INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id)
--	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Influenza Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description like 'Germes Enterogastriques patho%' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang Total)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'M' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rotavirus Elisa(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'SyphilisTest Rapid(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 1(Selles 1)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 2(Selles 2)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Indetectable <300' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(DBS)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Indetectable <300' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Immuno Dot Leptospira IgM (Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Plasma)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang capillaire)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Eau de riviere)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Sensible' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Normal' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Normal' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );
INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , (select id from clinlims.type_of_test_result where test_result_type = 'D' ) ,  now() , (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) );

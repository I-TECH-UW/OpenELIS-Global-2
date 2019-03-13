INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4  Compte Abs(Sang Total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,500,1500,0,3000, now() );

			 
			 
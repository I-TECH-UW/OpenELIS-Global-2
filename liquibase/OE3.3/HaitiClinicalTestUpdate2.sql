INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glycemie Provoquee Fasting(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity', '' ,70,120,20,450, now() );		

UPDATE clinlims.test set uom_id = ( select id from clinlims.unit_of_measure where name='mg/dl') where description = 'Glycemie Provoquee Fasting(Serum)';

UPDATE clinlims.test_result set tst_rslt_type = 'N' where test_id = ( select id from clinlims.test where description = 'Glycemie Provoquee Fasting(Serum)' );  			 


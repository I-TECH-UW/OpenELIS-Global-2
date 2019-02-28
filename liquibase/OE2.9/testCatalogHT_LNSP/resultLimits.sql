INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 Compte en %(Sang Total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,30,60,5,65, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' ,3.50,11.0,1,99.9, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' ,4.0,11.0,1,99.9, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1 , '' ,10.0,25.0,1,99.9, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168 , '' , 6.0, 10.0,1,99.9, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' ,4.50,6.20,0.30,7.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 4.00, 5.40,0.30,7.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1 , '' ,5.00,6.00,0.30,7.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168 , '' , 3.50, 6.00,0.30,7.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' ,12.5,15.5,1.0,25.0, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' ,14.0,17.0,1.0,25.0, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168 , '' , 11.0,13.0,1.0,25.0, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1 , '' ,14.0, 19.0,1.0,25.0, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' ,35,46,10,60, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' ,36,52,10,60, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1 , '' ,44,62,10,60, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168 , '' ,36,42,10,60, now() ); 
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VGM(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80,95,45,120, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,28,32,15,50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,30,35,20,38, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Plaquettes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,150,400,10,999, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Neutrophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,50,80,1,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,20,40,1,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,10,1,15, now() );

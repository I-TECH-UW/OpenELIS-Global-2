INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Yeast Cells (>5/hpf)(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 0, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Red Blood Cells (>5/hpf)(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 3, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Specific Gravity(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 1.003, 1.03, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'pH(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 5, 7, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Urobilinogen Phenlpyruvic Acid(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.2, 1, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Pus Cells (>5/hpf)(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 2, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'WBC Count(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 4, 11, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Neutrophil, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 1.7, 7.7, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocyte, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.4, 4.4, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocyte, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 0.8, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Eosinophil, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 0.6, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Basophil, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 0.2, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Neutrophil(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 42, 85, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocyte(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 11, 49, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocyte(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 9, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Eosinophil(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 6, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Basophil(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 2, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'RBC Count - Male(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 4.4, 5.9, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'RBC Count - Female(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 3.8, 5.2, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobin - Male(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 13.3, 17.7, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobin - Female(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 11.7, 15.7, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrit - Male(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 39.8, 52.2, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrit - Female(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 34.9, 46.9, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'MCV(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 80, 100, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'MCH(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 25, 32, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'MCHC(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 28, 36, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'RDW(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 10, 16.5, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'PLT(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 100, 450, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'PCT(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.1, 1, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'MPV(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 5, 10, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'PDW(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 12, 18, '-Infinity', 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Bleeding Time(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 2, 9, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Thrombin Clotting Time(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 11, 18, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Prothrombin Time(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 11, 14, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Partial prothrombin time(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 25, 35, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Erythrocyte Sedimentation rate (ESR)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 20, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Reticulocyte counts %(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 1, 3, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Haemoglobin - HemoCue(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 12, 18, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 %(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 30, 55, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4, Absolute(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 433, 1508, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4:CD8 ratio(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.5, 2.7, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Direct bilirubin(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.1, 0.3, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Total bilirubin(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.1, 1.2, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'SGPT/ALT - Male(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 10, 40, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'SGPT/ALT - Female(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 7, 35, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'SGOT/AST(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 10, 41, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Serum Protein(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 6.4, 8.3, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Albumin(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 3.5, 4.8, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Alkaline Phosphatase - Male(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 53, 128, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Alkaline Phosphatase - Female(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 42, 98, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Gamma GT(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 5, 40, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Amylase(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 30, 120, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Creatinine(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.6, 1.3, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Urea(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 6, 20, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Sodium(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 136, 145, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Potassium(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 3.5, 5.1, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Chloride(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 98, 107, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Bicarbonate(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 18, 23, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Creatinine Clearance(Urine)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 4.8, 19, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Total cholestrol(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 199.99, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Trigycerides(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 149.99, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'HDL(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 1.1, 2.1, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'LDL(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 2.2, 3.2, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'PSA - Total(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 1.99, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'PSA - Free(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 24.99, 'Infinity', 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Proteins(CSF)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 15, 45, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose(CSF)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 60, 80, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose - Fasting (Finger-Stick Test)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 60, 100, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose - Random (Finger-Stick Test)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 109.99, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose-Fasting (Serum/Plasma)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 70, 110, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose- Random(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 70, 139, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose-2 HR PC(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 139.99, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Acid phosphatase(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 0.8, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Triiodothyronine (T3)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 2.3, 4.2, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Thyroid-stimulating Hormone (TSH)(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0.37, 4.42, 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'HIV Viral Load(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 200000, 0, 200001, now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatitis C Viral Load(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 'Infinity', 0, 'Infinity', now() );
INSERT INTO clinlims.result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'clinlims.result_limits_seq' ) , ( select id from clinlims.test where description = 'Hepatitis B Viral Load(Blood)' ), 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ), 0, 'Infinity' , NULL, 0, 'Infinity', 0, 'Infinity', now() );

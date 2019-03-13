INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M', 3.50, 11.00,0.50,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F', 4.00, 11.00,0.50,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '', 10.00, 25.00,0.50,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 6.00, 10.00,0.50,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Copmte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' , 4.50, 6.20,0.10,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Copmte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 4.00, 5.40,0.10,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Copmte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' , 5.00, 6.00,0.10,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Copmte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 3.50, 6.00,0.10,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 12.5, 15.5,1.00,26.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' , 14.00, 17.00,1.00,26.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '', 14.00, 19.00,1.00,26.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '', 11.00, 13.00,1.00,26.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 35, 46,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' , 6, 52,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' , 44, 62,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 36, 42,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VGM(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80,95,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,28.00,32.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,30,35,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Plaquettes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,150000.00,400000.00,1.00,100000000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Neutrophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,50,80,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,20,40,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.00,25.00,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2,10,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Eosinophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,4,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Basophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,1,0,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,10.00,0.00,15.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,5.00,15.00,0.00,60.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2.00,8.00,0.00,60.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'temps de saignement(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2.00,4.00,1.00,6.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.50,1.50,0.00,3.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.50,1.50,0.00,3.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,25.00,37.00,10.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fibrinogene(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2.00,4.00,0.00,8.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Facteur IX(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,60.00,150.00,40.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Heparinemie(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.20,0.50,0.00,1.50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80.00,120.00,30.00,180.00, now() );		 
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$ ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,9.00,18.00,0.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Uree(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,20.00,40.00,0.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'creatinine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.30,2.00,0.10,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glycemie(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70.00,120.00,0.10,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose(LCR/CSF)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.20,0.40,0.1,0.50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Proteines(LCR/CSF)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,19.80,23.40,10.00,33.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlore(LCR/CSF)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,19.80,23.40,10.00,33.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Alpha-foetoproteine(Liquide Amniotique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,10,10,10,10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'dosage des phospholipides(Liquide Amniotique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,3,3,3,3, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'creatinine(Liquide Amniotique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,5.00,12.00,2.00,20, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Proteines(Liquide Ascite)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,2.50,0,3.50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Albumine(Liquide Ascite)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.45,1.1,0.10,5.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'glucose(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,126.70,131.10,0, 'Infinity' , now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Uree(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.10,0.50,0.1,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide urique(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'M' ,35 ,70 ,20.00,90.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Acide urique(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'F' ,25,60,20.00,90.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'proteine total(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.20,0.40,0.00,2.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'glycemie(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70.00,120.00,0.10,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Albumine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,36.00,50.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'α1 globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.00,5.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'α2 globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,4.00,8.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'β globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,5.00,12.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ϒ globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,8.00,16.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Acide(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,4.30,0.00,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Plasma hepariné)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80.00,200.00,1.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlorures(LCR/CSF)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80.00,200.00,1.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Urines/24 heures)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,80.00,200.00,1.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' ,70,145,10.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 60, '' ,20,120,10.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' , 20,200,10.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 20,100,10.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,10.00,90.00,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lipase(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,7.00,60.00,1.00,1000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,95.00,105.00,40.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Sodium(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,135.00,147.00,100.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Potassium(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,3.50,5.00,1.00,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bicarbonates(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,22.00,30.00,15.00,40.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Calcium(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,90.00,100.00,30.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'magnésium(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.50,2.50,0.10,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'phosphore(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2.20,4.80,0.10,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lithium(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.60,1.20,0.10,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fer Serique(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,6.00,36.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bilirubine totale(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.30,1.10,0.00,2.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bilirubine directe(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.10,0.40,0.00,1.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.20,0.70,0.00,1.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'SGOT(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,8.00,40.00,1.00,80.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'SGPT(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,8.00,40.00,1.00,80.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,9.00,35.00,1.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cholesterol Total(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,100.00,200.00,50.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Triglyceride(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,10.00,150.00,1.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lipide(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,150.00,200.00,100.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,40.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'LDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,100.00,0.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VLDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,25.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'MBG(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,60.00,100.00,20.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine glyqué(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,4.40,60.00,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ck-mB(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,22.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ck total(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,25.00,174.00, 0, 'Infinity', now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' , 20, 70,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 5, 30,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' ,5,30,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 5, 25,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, ' ' , 20, 80,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 10, 35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M' , 10, 40,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 10, 35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' , 2, 20,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 5, 30,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'M',  5, 35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 168, 'Infinity' , 'F' , 5, 30,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 1, '' , 5,35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 1, 168, '' , 10, 35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'M', 10, 45,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'F', 10, 35,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'LDH(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,150.00,450.00,100.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Troponine 1(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,1.50,0.00,2.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70,120,30.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ph(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,7.35,7.45,3.00,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'PaCO2(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,35,45,20.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HCO3(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,22,26,15.00,30.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'O2 Saturation(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,96,100,50.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'PaO2(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,85,100,50.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'BE(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,-2,2,-3.00,3.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'pH(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,7.20,8.30,1.00,14.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fructose(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,315.00,500.00,1.00,2000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Volume(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,3.00,5.00,0.10,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,60.00,120.00,1.00,1000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,79.00,90.00,0.00,100.00, now() );
--INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
--	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)' ) , 
--			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,,,,, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70.00,90.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70.00,90.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,70.00,90.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Densite(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.003,1.030,0.50,2.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Ph(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,5.00,8.50,2.00,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 en mm3(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,500.00,1500.00,0.00,3000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 en %(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,30,60,15,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CMV Ig G(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,12.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CMV Ig M(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,0.90,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig G(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,15.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig M(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,5.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE I   Ig G(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,0.80,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE II Ig M(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,0.80,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Charge Virale(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,300,300,1.00,200000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'globules rouges(Liquide Pleural)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,10000.00,100000.00,1.00,10000000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cellules polynucleaires(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,24.00,0,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'leucocytes(Liquide Synovial)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,10.00,200.00,0.00,220, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numeration des globules blancs(LCR/CSF)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,4000.00,10000.00,0,'Infinity', now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Prolactine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'M' ,2,15,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Prolactine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'F' ,3,20,0.00,500.00, now() );
--INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
--	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Prolactine(Serum)' ) , 
--			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,Post-menopause: 2,Post-Menopause 15,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'FSH(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'FSH(Plasma hepariné)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'LH(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'LH(Plasma hepariné)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Progesterone(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,500,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'T3(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.07,3.37,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'T4(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,58.00,161.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TSH(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.20,5.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,30.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.00,0.90,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,20,0,'Infinity', now() );			 

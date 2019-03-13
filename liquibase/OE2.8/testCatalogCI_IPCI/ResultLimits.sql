INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,7,40,7,350, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,3,40,3,350, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.7,1.1,0.1,5, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Créatinine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,6,13,2.03,150, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,486,1,1000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,36,50,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cholestérol total(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1.5,2.6,0.01,5, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.35,0.7,0,150, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Triglycérides(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.35,1.7,0.1,7, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0.05,0.2,0.05,1.5, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 120, 'Infinity' , '' ,4.0,10.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 3 , '' ,10.0,25.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 3, 8 , '' ,8.0,15.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 8, 12 , '' ,6.0,15.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12,48, '' ,4.5,13.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 48, 120 , '' ,4.0,10.0,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'M' ,4.50,6.00,0,10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'F' ,4.80,5.50,0,10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 3 , '' ,5.00,6.20,0,10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 3, 12 , '' ,3.60,5.00,0,10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'F' ,12.00,16.00,0,30, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'M' ,13.00,18.00,0,30, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 3 , '' ,16.00,20.00,0,30, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 3, 12 , '' ,12.00,16.00,0,30, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'F' ,37,47,0,80, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 12, 'Infinity' , 'M' ,40,52,0,80, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 3 , '' ,44,62,0,80, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 3, 12 , '' ,36,42,0,80, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 3, 'Infinity' , '' ,85,95,0,266, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 3 , '' ,106,106,0,266, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,27,31,0,50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,32,36,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Plaquette(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,150,400,0,1500, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,45,70,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1500,7000,0,100000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,4,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,400,0,100000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,0.5,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,50,0,100000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,20,40,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1500,4000,0,100000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2,10,0,100, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,1000,0,100000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,400,1750,0,3000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,35,55,0,5000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,300,1,200000, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,300,1,200000, now() );
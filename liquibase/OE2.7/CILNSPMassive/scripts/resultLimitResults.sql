INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Homme:3.50  / femme: 4.00 / nouveau-ne: 10.00 /enfant : 6.00,Homme: 11.00 / femme: 11.00 / nouveau-ne: 25.00 /enfant : 10.00,0.50,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Homme: 4.50 / femme: 4.00 / nouveau-ne: 5.00 /enfant : 3.50,Homme: 6.20 / femme: 5.40 / nouveau-ne: 6.00 /enfant : 6.00,0.10,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,femme:12.5 / Homme:14.00 / nouveau-ne: 14.00 enfant: 11.00,femme:15.5 / Homme:17.00 / nouveau-ne: 19.00 / enfant:13.00,1.00,26.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,femme:35 /Homme: 36 / nouveau-ne: 44 / enfant : 36,Femme:46/Homme:52  / nouveau-ne: 62 / enfant :42,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'VGM(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,80,95,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'TCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,28.00,32.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CCMH(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,30,35,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Plaquettes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,150000.00,400000.00,1.00,100000000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Neutrophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,50,80,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,20,40,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,2,10,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Eosinophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,1,4,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Basophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0,1,2.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,10.00,0.00,15.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,2.00,8.00,0.00,60.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'temps de saignement(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,2.00,4.00,1.00,6.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Taux reticulocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.50,1.50,0.00,3.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,25.00,37.00,10.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Fibrinogene(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,2.00,4.00,0.00,8.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Facteur IX(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,60.00,150.00,40.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Heparinemie(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.20,0.50,0.00,1.50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,80.00,120.00,30.00,180.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Azote de l'Uree(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,9.00,18.00,0.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Uree(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,20.00,40.00,0.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'creatinine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.30,2.00,0.10,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glycemie(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,70.00,120.00,0.10,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glucose(LCR)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.20,0.40,> 0.1,0.50, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Proteines(LCR)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,19.80,23.40,10.00,33.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Alpha-foetoproteine(Liquide Amniotique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,< 10,<10,> 10,>10, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'dosage des phospholipides(Liquide Amniotique)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,<3,<3,>3,>3, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Albumine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,36.00,50.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'α1 globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,1.00,5.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'α2 globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,4.00,8.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'β globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,5.00,12.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'ϒ globuline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,8.00,16.00,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Acide(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,H> 50 ans: 2.2                 H 20-50 ans: 1.9            F> 40 ans: 1.5                                 F 20-40: 1.5                                      G 4-10 ans:3.9                                   F 4-10 ans:3.6                                  Enf. 1-3 ans: 4.5,5.3                                     5.0                                              4.8                                         4.4                                     7.3                               7.3                                  9.5,0.00,10.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlore(Plasma heparinate)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,100                                           80                                      110,110                                     200                                     130,50                               30                     50,150                  250               200, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlore(Urines de 24 heures)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,100                                           80                                      110,110                                     200                                     130,50                               30                     50,150                  250               200, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlore(LCR)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,100                                           80                                      110,110                                     200                                     130,50                               30                     50,150                  250               200, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Nouveau-ne : 70         Nourisson-enfant: 20                               Homme adulte:20                           Femme Adulte: 20,145                                     120                                     200                                     100,10.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,10.00,90.00,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Lipase(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,7.00,60.00,1.00,1000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Chlore(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,95.00,105.00,40.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bilirubine totale(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.30,1.10,0.00,2.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Bilirubine directe(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.10,0.40,0.00,1.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'SGOT(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,8.00,40.00,1.00,80.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'SGPT(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,8.00,40.00,1.00,80.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,9.00,35.00,1.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Cholesterol Total(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,100.00,200.00,50.00,250.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Triglyceride(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,10.00,150.00,1.00,200.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'HDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,40.00,0.00,50.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'LDL(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,100.00,0.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine gliqué(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,4.40,60.00,1.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Glycemie provoquee(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,70,120,30.00,150.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherches metazoaires(Selles)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,500.00,1500.00,0.00,3000.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,n/a,n/a,0.00,100.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recheche de microfilaire(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,12.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,0.90,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 en mm3(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,5.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'CD4 en %(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,0.80,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Avant Puberte: <1.5       Adultes:1                            Andropause: >20,Adultes: 5,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(sputum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Avant Puberte: <1.5       Adultes:1                            Andropause: >20,Adultes: 5,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Homme: 18                           Femme avant la puberte: 7                                             Phase folliculaire:35                                      pic preovulatoire:150                                      Phase luteale:110                           Post menopause:18,75                                              35                                              110                                            370                                            220                                            70,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Culture de M. tuberculosis(sputum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,Homme: 18                           Femme avant la puberte: 7                                             Phase folliculaire:35                                      pic preovulatoire:150                                      Phase luteale:110                           Post menopause:18,75                                              35                                              110                                            370                                            220                                            70,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Prolactine(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,107,337,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oestrogene(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,5jours: 115                     5mois-5ans: 102          5ans-15ans: 90             15ans-60ans: 64           >60ans: 58,192                                     180                                     160                                     160                                     128,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Oestrogene(24 heures)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,5jours: 115                     5mois-5ans: 102          5ans-15ans: 90             15ans-60ans: 64           >60ans: 58,192                                     180                                     160                                     160                                     128,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Progesterone(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.20,5.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Serum)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,30.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Urine concentre du matin)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,30.00,0.00,500.00, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Test de Grossesse(Urines)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , 'gender' ,0.00,0.90,0.00,500.00, now() );

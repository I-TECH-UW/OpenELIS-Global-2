DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Transaminases GPT (37°C)(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Transaminases GPT (37°C)(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Transaminases G0T (37°C)(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Transaminases G0T (37°C)(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Glucose(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glucose(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Créatinine(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Créatinine(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amylase(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amylase(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Albumine recherche miction(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Albumine recherche miction(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cholestérol total(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cholestérol total(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cholestérol HDL(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cholestérol HDL(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Triglycérides(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Triglycérides(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Prolans (BHCG) urines de 24 h(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Prolans (BHCG) urines de 24 h(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test urinaire de grossesse(Urine)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test urinaire de grossesse(Urine)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Protéinurie sur bandelette(Urine)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Protéinurie sur bandelette(Urine)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Numération des globules blancs(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Numération des globules blancs(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Numération des globules rouges(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Numération des globules rouges(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hémoglobine(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hémoglobine(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hémotocrite(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hémotocrite(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Volume Globulaire Moyen(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Volume Globulaire Moyen(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Plaquette(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Plaquette(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires basophiles (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires basophiles (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polynucléaires basophiles (Abs)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polynucléaires basophiles (Abs)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lymphocytes (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lymphocytes (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lymphocytes (Abs)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lymphocytes (Abs)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Monocytes (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Monocytes (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Monocytes (Abs)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Monocytes (Abs)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test rapide HIV 1 + HIV 2(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test rapide HIV 1 + HIV 2(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HBs AG (antigén australia)(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HBs AG (antigén australia)(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mesure de la charge virale(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mesure de la charge virale(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Western blot VIH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Western blot VIH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Western blot VIH(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Western blot VIH(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bioline(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bioline(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bioline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bioline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bioline(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bioline(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Genie III(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Genie III(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Genie III(Sang total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Genie III(Sang total)' )  ,    (select id from type_of_sample where description = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Murex(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Murex(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Murex(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Murex(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Vironostika(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Vironostika(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Vironostika(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Vironostika(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'P24 Ag(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'P24 Ag(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'P24 Ag(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'P24 Ag(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );

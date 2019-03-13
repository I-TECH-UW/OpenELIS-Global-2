DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Compte des Globules Blancs(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Compte des Globules Blancs(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Copmte des Globules Rouges(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Copmte des Globules Rouges(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hemoglobine(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hemoglobine(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hematocrite(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hematocrite(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VGM(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VGM(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'TCMH(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'TCMH(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CCMH(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CCMH(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Plaquettes(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Plaquettes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Neutrophiles(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Neutrophiles(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lymphocytes(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lymphocytes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mixtes(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mixtes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Monocytes(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Monocytes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Eosinophiles(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Eosinophiles(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Basophiles(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Basophiles(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Vitesse de Sedimentation(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Vitesse de Sedimentation(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Temps de Coagulation en tube(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Temps de Coagulation en tube(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Temps de Coagulation(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Temps de Coagulation(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'temps de saignement(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'temps de saignement(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = $$Electrophorese de l'hemoglobine(Sang)$$ )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = $$Electrophorese de l'hemoglobine(Sang)$$ )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sickling Test(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sickling Test(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Taux reticulocytes - Auto(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Taux reticulocytes - Auto(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Taux reticulocytes - Manual(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Taux reticulocytes - Manual(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Temps de cephaline Activé(TCA)(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Temps de cephaline Activé(TCA)(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fibrinogene(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fibrinogene(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Facteur VIII(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Facteur VIII(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Facteur IX(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Facteur IX(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Heparinemie(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Heparinemie(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Anti-Thrombine III (Dosage)(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Anti-Thrombine III (Dosage)(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Anti-Thrombine III (Activite)(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Anti-Thrombine III (Activite)(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Groupe Sanguin - ABO(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Groupe Sanguin - ABO(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Groupe Sanguin - Rhesus(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Groupe Sanguin - Rhesus(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coombs Test Direct(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coombs Test Direct(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coombs Test Direct(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coombs Test Direct(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coombs Test Indirect(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coombs Test Indirect(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coombs Test Indirect(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coombs Test Indirect(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = $$Azote de l'Uree(Serum)$$ )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = $$Azote de l'Uree(Serum)$$ )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Uree(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Uree(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'creatinine(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'creatinine(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Glycemie(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glycemie(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Glucose(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glucose(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Proteines(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Proteines(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chlore(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chlore(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Alpha-foetoproteine(Liquide Amniotique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Amniotique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Alpha-foetoproteine(Liquide Amniotique)' )  ,    (select id from type_of_sample where description = 'Liquide Amniotique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'dosage des phospholipides(Liquide Amniotique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Amniotique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'dosage des phospholipides(Liquide Amniotique)' )  ,    (select id from type_of_sample where description = 'Liquide Amniotique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'creatinine(Liquide Amniotique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Amniotique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'creatinine(Liquide Amniotique)' )  ,    (select id from type_of_sample where description = 'Liquide Amniotique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Proteines(Liquide Ascite)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Ascite') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Proteines(Liquide Ascite)' )  ,    (select id from type_of_sample where description = 'Liquide Ascite')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Albumine(Liquide Ascite)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Ascite') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Albumine(Liquide Ascite)' )  ,    (select id from type_of_sample where description = 'Liquide Ascite')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'glucose(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'glucose(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Uree(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Uree(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide urique(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide urique(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'proteine total(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'proteine total(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'glycemie(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'glycemie(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Albumine(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Albumine(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'α1 globuline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'α1 globuline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'α2 globuline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'α2 globuline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'β globuline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'β globuline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ϒ globuline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ϒ globuline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Phosphatase Acide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Phosphatase Acide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chlorures(Plasma hepariné)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma hepariné') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chlorures(Plasma hepariné)' )  ,    (select id from type_of_sample where description = 'Plasma hepariné')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chlorures(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chlorures(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chlorures(Urines/24 heures)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines/24 heures') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chlorures(Urines/24 heures)' )  ,    (select id from type_of_sample where description = 'Urines/24 heures')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CPK(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CPK(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amylase(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amylase(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lipase(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lipase(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chlorures(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chlorures(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sodium(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sodium(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Potassium(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Potassium(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bicarbonates(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bicarbonates(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Calcium(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Calcium(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'magnésium(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'magnésium(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'phosphore(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'phosphore(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lithium(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lithium(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fer Serique(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fer Serique(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bilirubine totale(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bilirubine totale(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bilirubine directe(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bilirubine directe(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bilirubine indirecte(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bilirubine indirecte(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'SGOT(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'SGOT(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'SGPT(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'SGPT(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Phosphatase Alcaline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Phosphatase Alcaline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cholesterol Total(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cholesterol Total(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Triglyceride(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Triglyceride(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lipide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lipide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HDL(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HDL(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LDL(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LDL(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VLDL(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VLDL(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'MBG(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'MBG(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hémoglobine glyqué(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hémoglobine glyqué(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ck-mB(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ck-mB(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ck total(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ck total(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ASAT 30° C(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ASAT 30° C(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ASAT 37° C(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ASAT 37° C(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ALAT 30° C(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ALAT 30° C(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ALAT 37° C(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ALAT 37° C(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LDH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LDH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Troponine 1(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Troponine 1(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Glycémie provoquée(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glycémie provoquée(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ph(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ph(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'PaCO2(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'PaCO2(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HCO3(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HCO3(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'O2 Saturation(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'O2 Saturation(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'PaO2(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'PaO2(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'BE(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'BE(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacteries(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacteries(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures Simples(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures Simples(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures Bourgeonantes(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures Bourgeonantes(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Trichomonas vaginalis(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Trichomonas vaginalis(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Globules Blancs(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Globules Blancs(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Globules Rouges(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Globules Rouges(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Filaments Myceliens(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Filaments Myceliens(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cellules Epitheliales(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cellules Epitheliales(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacteries(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacteries(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures Simples(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures Simples(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures Bourgeonantes(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures Bourgeonantes(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Trichomonas vaginalis(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Trichomonas vaginalis(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Globules Blancs(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Globules Blancs(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Globules Rouges(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Globules Rouges(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Filaments Myceliens(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Filaments Myceliens(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cellules Epitheliales(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cellules Epitheliales(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'KOH(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'KOH(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test de Rivalta(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test de Rivalta(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacilles Gram+(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacilles Gram+(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacilles Gram-(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacilles Gram-(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocci Gram+(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocci Gram+(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocci Gram-(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocci Gram-(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocobacilles Gram -(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocobacilles Gram -(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacilles Gram+(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacilles Gram+(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacilles Gram-(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacilles Gram-(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocci Gram+(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocci Gram+(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocci Gram-(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocci Gram-(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cocobacilles Gram -(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cocobacilles Gram -(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion vaginale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' )  ,    (select id from type_of_sample where description = 'Secretion vaginale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' )  and sample_type_id =  (select id from type_of_sample where description = 'Secretion Urethrale') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' )  ,    (select id from type_of_sample where description = 'Secretion Urethrale')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cholera Test rapide(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cholera Test rapide(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coproculture(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coproculture(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Couleur(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Couleur(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Liquefaction(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Liquefaction(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'pH(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'pH(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fructose(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fructose(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Volume(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Volume(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Compte de spermes(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Compte de spermes(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Formes normales(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Formes normales(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Formes anormales(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Formes anormales(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Motilite STAT(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Motilite STAT(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Motilite 1 heure(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Motilite 1 heure(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Motilite 3 heures(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Motilite 3 heures(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gram(Liquide Spermatique)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Spermatique') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gram(Liquide Spermatique)' )  ,    (select id from type_of_sample where description = 'Liquide Spermatique')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Couleur(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Couleur(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aspect(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aspect(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Densite(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Densite(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ph(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ph(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Proteines(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Proteines(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Glucose(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Glucose(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cetones(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cetones(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bilirubine(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bilirubine(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sang(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sang(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide ascorbique(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide ascorbique(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Urobilinogene(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Urobilinogene(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrites(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrites(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hematies(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hematies(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Leucocytes(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Leucocytes(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'cellules epitheliales(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'cellules epitheliales(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacteries(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacteries(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'filaments myceliens(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'filaments myceliens(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'spores(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'spores(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'trichomonas(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'trichomonas(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cylindres(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cylindres(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cristaux(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cristaux(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Couleur(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Couleur(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aspect(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aspect(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sang Occulte(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sang Occulte(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacteries(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacteries(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'cellules vegetales(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'cellules vegetales(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Protozoaires(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Protozoaires(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Metazoaires(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Metazoaires(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recheche de microfilaire(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recheche de microfilaire(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de plasmodiun - Especes(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de plasmodiun - Especes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de plasmodiun - Schizonte(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de plasmodiun - Schizonte(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CD4 en mm3(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CD4 en mm3(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CD4 en %(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CD4 en %(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CMV Ig G(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CMV Ig G(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CMV Ig M(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CMV Ig M(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'RUBELLA Ig G(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'RUBELLA Ig G(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'RUBELLA Ig M(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'RUBELLA Ig M(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HERPESTYPE I   Ig G(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HERPESTYPE I   Ig G(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HERPESTYPE II Ig M(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HERPESTYPE II Ig M(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV test rapide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV test rapide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV test rapide(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV test rapide(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV test rapide(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV test rapide(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Elisa(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Elisa(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Elisa(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Elisa(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Elisa(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Elisa(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HBsAg - Hépatite B(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HBsAg - Hépatite B(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HBsAg - Hépatite B(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HBsAg - Hépatite B(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HBsAg - Hépatite B(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HBsAg - Hépatite B(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HCV - Hépatite C(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HCV - Hépatite C(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HCV - Hépatite C(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HCV - Hépatite C(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HCV - Hépatite C(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HCV - Hépatite C(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Toxoplasmose(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Toxoplasmose(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Toxoplasmose(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Toxoplasmose(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Toxoplasmose(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Toxoplasmose(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rubeole(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rubeole(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rubeole(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rubeole(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rubeole(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rubeole(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Western Blot(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Western Blot(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Western Blot(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Western Blot(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HIV Western Blot(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HIV Western Blot(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBsAg IGG(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBsAg IGG(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBsAg IGG(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBsAg IGG(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBsAg IGG(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBsAg IGG(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite C - HCV IGG(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite C - HCV IGG(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite C - HCV IGG(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite C - HCV IGG(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite C - HCV IGG(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite C - HCV IGG(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HCV IGM(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HCV IGM(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HCV IGM(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HCV IGM(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hépatite B - HCV IGM(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hépatite B - HCV IGM(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'P24(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'P24(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Charge Virale(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Charge Virale(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ADN VIH-1(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ADN VIH-1(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'PCR Resistance TB(Expectoration)' )  and sample_type_id =  (select id from type_of_sample where description = 'Expectoration') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'PCR Resistance TB(Expectoration)' )  ,    (select id from type_of_sample where description = 'Expectoration')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Antibiogramme(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Antibiogramme(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'recherche des bacteries(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'recherche des bacteries(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'pneumocoques(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'pneumocoques(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'streptocoques(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'streptocoques(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'staphylocoques(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'staphylocoques(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'culture BK(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'culture BK(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Antibiogramme(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Antibiogramme(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'culture(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'culture(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = $$cristaux d'urates(Liquide Synovial)$$ )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = $$cristaux d'urates(Liquide Synovial)$$ )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'cristaux de cholesterol(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'cristaux de cholesterol(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Examen direct(Pus)' )  and sample_type_id =  (select id from type_of_sample where description = 'Pus') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Examen direct(Pus)' )  ,    (select id from type_of_sample where description = 'Pus')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture(Pus)' )  and sample_type_id =  (select id from type_of_sample where description = 'Pus') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture(Pus)' )  ,    (select id from type_of_sample where description = 'Pus')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Antibiogramme(Pus)' )  and sample_type_id =  (select id from type_of_sample where description = 'Pus') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Antibiogramme(Pus)' )  ,    (select id from type_of_sample where description = 'Pus')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Ascite') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' )  ,    (select id from type_of_sample where description = 'Liquide Ascite')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Liquide Ascite GRAM(Liquide Ascite)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Ascite') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Liquide Ascite GRAM(Liquide Ascite)' )  ,    (select id from type_of_sample where description = 'Liquide Ascite')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Liquide Ascite ZIELH NIELSEN(Liquide Ascite)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Ascite') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Liquide Ascite ZIELH NIELSEN(Liquide Ascite)' )  ,    (select id from type_of_sample where description = 'Liquide Ascite')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche des Lymphocytes(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche des Lymphocytes(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Eosinophile(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Eosinophile(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'cellules atypiques/polynucleaires(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'cellules atypiques/polynucleaires(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'globules rouges(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'globules rouges(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'GRAM(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'GRAM(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ZIELH NIELSEN(Liquide Pleural)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Pleural') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ZIELH NIELSEN(Liquide Pleural)' )  ,    (select id from type_of_sample where description = 'Liquide Pleural')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cellules polynucleaires(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cellules polynucleaires(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'leucocytes(Liquide Synovial)' )  and sample_type_id =  (select id from type_of_sample where description = 'Liquide Synovial') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'leucocytes(Liquide Synovial)' )  ,    (select id from type_of_sample where description = 'Liquide Synovial')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Numeration des globules blancs(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Numeration des globules blancs(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LCR GRAM(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LCR GRAM(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR/CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' )  ,    (select id from type_of_sample where description = 'LCR/CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BAAR(Expectoration)' )  and sample_type_id =  (select id from type_of_sample where description = 'Expectoration') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BAAR(Expectoration)' )  ,    (select id from type_of_sample where description = 'Expectoration')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BK Auramine(Expectoration)' )  and sample_type_id =  (select id from type_of_sample where description = 'Expectoration') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BK Auramine(Expectoration)' )  ,    (select id from type_of_sample where description = 'Expectoration')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture de M. tuberculosis(Expectoration)' )  and sample_type_id =  (select id from type_of_sample where description = 'Expectoration') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture de M. tuberculosis(Expectoration)' )  ,    (select id from type_of_sample where description = 'Expectoration')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Prolactine(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Prolactine(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'FSH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'FSH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'FSH(Plasma hepariné)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma hepariné') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'FSH(Plasma hepariné)' )  ,    (select id from type_of_sample where description = 'Plasma hepariné')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LH(Plasma hepariné)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma hepariné') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LH(Plasma hepariné)' )  ,    (select id from type_of_sample where description = 'Plasma hepariné')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oestrogene(Urines/24 heures)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines/24 heures') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oestrogene(Urines/24 heures)' )  ,    (select id from type_of_sample where description = 'Urines/24 heures')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Progesterone(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Progesterone(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'T3(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'T3(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'B-HCG(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'B-HCG(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'B-HCG(Urine concentré du matin)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urine concentré du matin') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'B-HCG(Urine concentré du matin)' )  ,    (select id from type_of_sample where description = 'Urine concentré du matin')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test de Grossesse(Urines)' )  and sample_type_id =  (select id from type_of_sample where description = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test de Grossesse(Urines)' )  ,    (select id from type_of_sample where description = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'T4(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'T4(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'TSH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'TSH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Malaria Test Rapide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Malaria Test Rapide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Malaria Test Rapide(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Malaria Test Rapide(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Malaria Test Rapide(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Malaria Test Rapide(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Test Rapide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Test Rapide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Test Rapide(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Test Rapide(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Test Rapide(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Test Rapide(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HTLV I et II(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HTLV I et II(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HTLV I et II(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HTLV I et II(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HTLV I et II(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HTLV I et II(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis RPR(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis RPR(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis RPR(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis RPR(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis RPR(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis RPR(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis TPHA(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis TPHA(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis TPHA(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis TPHA(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis TPHA(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis TPHA(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Helicobacter Pilori(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Helicobacter Pilori(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Helicobacter Pilori(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Helicobacter Pilori(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Helicobacter Pilori(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Helicobacter Pilori(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CRP(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CRP(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CRP(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CRP(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CRP(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CRP(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ASO(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ASO(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ASO(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ASO(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'ASO(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'ASO(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test de Widal Ag O(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test de Widal Ag O(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test de Widal Ag H(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test de Widal Ag H(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Typhoide Test Rapide(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Typhoide Test Rapide(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Determine(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Determine(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Determine(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colloidal Gold / Shangai Kehua(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colloidal Gold / Shangai Kehua(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colloidal Gold / Shangai Kehua(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colloidal Gold / Shangai Kehua(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colloidal Gold / Shangai Kehua(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colloidal Gold / Shangai Kehua(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Bioline(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Bioline(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Bioline(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Bioline(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis Bioline(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis Bioline(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );

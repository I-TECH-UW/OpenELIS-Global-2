DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test Rapide VIH(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide VIH(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test Rapide VIH(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide VIH(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test Rapide VIH(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide VIH(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Elisa(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Elisa(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test Rapide VIH(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide VIH(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Elisa(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Elisa(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Elisa(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Elisa(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Elisa(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Elisa(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Western Blot(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Western Blot(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Western Blot(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Western Blot(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Western Blot(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Western Blot(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH Western Blot(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH Western Blot(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rougeole(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rougeole(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rougeole(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rougeole(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rubeole(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rubeole(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rubeole(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rubeole(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue NS1Ag(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue NS1Ag(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Dengue NS1Ag(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Dengue NS1Ag(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite A IgM(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite A IgM(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite A IgM(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite A IgM(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite B Ag(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite B Ag(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite B Ag(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite B Ag(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite C IgM(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite C IgM(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite C IgM(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite C IgM(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite E IgM(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite E IgM(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hepatite E IgM(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hepatite E IgM(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rotavirus Elisa(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rotavirus Elisa(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'SyphilisTest Rapid(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'SyphilisTest Rapid(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polio Selles 1(Selles 1)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles 1') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polio Selles 1(Selles 1)' )  ,    (select id from type_of_sample where description = 'Selles 1')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Polio Selles 2(Selles 2)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles 2') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Polio Selles 2(Selles 2)' )  ,    (select id from type_of_sample where description = 'Selles 2')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis RPR(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis RPR(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Syphilis TPHA(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Syphilis TPHA(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH-1 PCR Qualitatif(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR Qualitatif(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH-1 PCR Qualitatif(Sang Total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang Total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 PCR Qualitatif(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH-1 Charge Virale(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 Charge Virale(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'VIH-1 Charge Virale(DBS)' )  and sample_type_id =  (select id from type_of_sample where description = 'DBS') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'VIH-1 Charge Virale(DBS)' )  ,    (select id from type_of_sample where description = 'DBS')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Germes Enterogastriques pathogènes(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Germes Enterogastriques pathogènes(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' )  and sample_type_id =  (select id from type_of_sample where description = 'Culture') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' )  ,    (select id from type_of_sample where description = 'Culture')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CD4  Compte Abs(Sang Total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang Total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CD4  Compte Abs(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'CD4 Compte en %(Sang Total)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang Total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'CD4 Compte en %(Sang Total)' )  ,    (select id from type_of_sample where description = 'Sang Total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Compte des Globules Blancs(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Compte des Globules Blancs(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Compte des Globules Rouges(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Compte des Globules Rouges(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
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
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LCR Coloration de Gram  (LCR)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LCR Coloration de Gram  (LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Selles Coloration de Gram(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Selles Coloration de Gram(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coloration de Gram Ecouvillon Pharynge(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coloration de Gram Ecouvillon Pharynge(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coloration de Gram Ecouvillon Naso-Pharynge(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coloration de Gram Ecouvillon Naso-Pharynge(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coloration de Gram Sang(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coloration de Gram Sang(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Immuno Dot Leptospira IgM (Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Immuno Dot Leptospira IgM (Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Leptospirose IgM(Plasma)' )  and sample_type_id =  (select id from type_of_sample where description = 'Plasma') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Leptospirose IgM(Plasma)' )  ,    (select id from type_of_sample where description = 'Plasma')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Leptospirose IgM(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Leptospirose IgM(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Leptospirose IgM(Sang capillaire)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang capillaire') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Leptospirose IgM(Sang capillaire)' )  ,    (select id from type_of_sample where description = 'Sang capillaire')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hemoculture IgM(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hemoculture IgM(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Serum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' )  ,    (select id from type_of_sample where description = 'Serum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LCR : Culture(LCR)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LCR : Culture(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'LCR Test Rapide(LCR)' )  and sample_type_id =  (select id from type_of_sample where description = 'LCR') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'LCR Test Rapide(LCR)' )  ,    (select id from type_of_sample where description = 'LCR')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de Salmonelle(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de Salmonelle(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de Shigelle(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de Shigelle(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de V.cholerae(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de V.cholerae(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de C. diphteriae(Ecouvillon Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de C. diphteriae(Ecouvillon Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de B. pertussis(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de B. pertussis(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide nalidixique 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide nalidixique 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Acide fusidique 10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Acide fusidique 10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amikacine 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amikacine 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline 10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline 10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Azithromicine 15 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Azithromicine 15 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aztreinam 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aztreinam 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefalotine 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefalotine 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefotaxime 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefotaxime 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftazidime 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftazidime 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftriaxone 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftriaxone 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Cefixime10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Cefixime10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ceftizoxime 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ceftizoxime 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Chloramfenicol 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Chloramfenicol 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ciprofloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ciprofloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colistine 50 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colistine 50 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Enoxacine 50 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Enoxacine 50 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Erythromycine 15 UI(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Erythromycine 15 UI(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fosfomycine 50 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fosfomycine 50 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Gentamicine 15 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Gentamicine 15 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lincomycine 15 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lincomycine 15 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levofloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levofloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Lomefloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Lomefloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Mecillinam 10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Mecillinam 10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Meropeneme 10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Meropeneme 10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxalactam(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxalactam(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxifloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxifloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxifloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxifloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Moxifloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Moxifloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nitrofuranes 300 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nitrofuranes 300 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Norfloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Norfloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 1 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 1 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Oxacilline 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Oxacilline 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ofloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ofloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pefloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pefloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Penicilline G 6 μg (10 UI)(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Piperacilline 75 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Piperacilline 75 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Pristinamycine 15 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Pristinamycine 15 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Rifampicine 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Rifampicine 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sparfloxacine 5 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sparfloxacine 5 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Teiclopanine 30 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Teiclopanine 30 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tetracycline 30 UI(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tetracycline 30 UI(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ticarcilline 75 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ticarcilline 75 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Pharinge') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Pharinge')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' )  and sample_type_id =  (select id from type_of_sample where description = 'Ecouvillon Nasal') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' )  ,    (select id from type_of_sample where description = 'Ecouvillon Nasal')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Eau de riviere)' )  and sample_type_id =  (select id from type_of_sample where description = 'Eau de riviere') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Eau de riviere)' )  ,    (select id from type_of_sample where description = 'Eau de riviere')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Tobramycine 10 μg(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Tobramycine 10 μg(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Echantillon(Free text)' )  and sample_type_id =  (select id from type_of_sample where description = 'Free text') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Echantillon(Free text)' )  ,    (select id from type_of_sample where description = 'Free text')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Colaration de Gram(Free text)' )  and sample_type_id =  (select id from type_of_sample where description = 'Free text') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Colaration de Gram(Free text)' )  ,    (select id from type_of_sample where description = 'Free text')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture(Free text)' )  and sample_type_id =  (select id from type_of_sample where description = 'Free text') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture(Free text)' )  ,    (select id from type_of_sample where description = 'Free text')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Couleur(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Couleur(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Consistance(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Consistance(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sang Occulte(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sang Occulte(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Bacteries(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Bacteries(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures simples(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures simples(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levures bourgeonantes(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levures bourgeonantes(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Examen Microscopique direct(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Examen Microscopique direct(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Examen Microscopique après concentration(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Examen Microscopique après concentration(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = $$Recherche d'Oxyures (Scotch Tape)(Selles)$$ )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = $$Recherche d'Oxyures (Scotch Tape)(Selles)$$ )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ziehl Neelsen modifiee(Selles)' )  and sample_type_id =  (select id from type_of_sample where description = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ziehl Neelsen modifiee(Selles)' )  ,    (select id from type_of_sample where description = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Malaria(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Malaria(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Malaria Test Rapide(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Malaria Test Rapide(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de Microfilaires(Sang)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sang') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de Microfilaires(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture de mycobacteries Solide(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture de mycobacteries Solide(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Culture de mycobacteries liquide(Sputum)' )  and sample_type_id =  (select id from type_of_sample where description = 'Sputum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Culture de mycobacteries liquide(Sputum)' )  ,    (select id from type_of_sample where description = 'Sputum')  );

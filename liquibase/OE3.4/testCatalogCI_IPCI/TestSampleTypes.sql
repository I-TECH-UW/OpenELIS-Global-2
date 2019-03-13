DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Goutte épaisse(Sang total)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Goutte épaisse(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasitémie(Sang total)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasitémie(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Frottis Mince(Sang total)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Frottis Mince(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Autres espèces(Sang total)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sang total') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Autres espèces(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Scotch test anal(Scotch Test Anal)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Scotch Tes') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Scotch test anal(Scotch Test Anal)' )  ,    (select id from type_of_sample where local_abbrev = 'Scotch Tes')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aspect des selles(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aspect des selles(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasites(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasites(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Eléments fongiques(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Eléments fongiques(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Concentration des selles(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Concentration des selles(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Baermann(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Baermann(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Ziehl neelsen(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Ziehl neelsen(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Coproculture(Selles)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Selles') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Coproculture(Selles)' )  ,    (select id from type_of_sample where local_abbrev = 'Selles')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'IgM(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'IgM(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Index IgM(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Index IgM(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'IgG(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'IgG(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Titre IgG(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Titre IgG(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'HAI amibiase(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'HAI amibiase(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Résultat HAI bilharziose(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Résultat HAI bilharziose(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Sérum') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)' )  ,    (select id from type_of_sample where local_abbrev = 'Sérum')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Aspect des urines(Urines)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Aspect des urines(Urines)' )  ,    (select id from type_of_sample where local_abbrev = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasites (examen direct)(Urines)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasites (examen direct)(Urines)' )  ,    (select id from type_of_sample where local_abbrev = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Eléments fongiques (examen direct)(Urines)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Eléments fongiques (examen direct)(Urines)' )  ,    (select id from type_of_sample where local_abbrev = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasites (après centrifugation)(Urines)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Urines') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasites (après centrifugation)(Urines)' )  ,    (select id from type_of_sample where local_abbrev = 'Urines')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de microfilaires' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de microfilaires' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Recherche de leishmanies' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Recherche de leishmanies' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Scotch test cutane(Biopsie)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Biopsie') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Scotch test cutane(Biopsie)' )  ,    (select id from type_of_sample where local_abbrev = 'Biopsie')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Filaments - mycoses profondes' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Filaments - mycoses profondes' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Filaments - mycose superficielle' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Filaments - mycose superficielle' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Leucocytes' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Leucocytes' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test encre chine(LCR)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'LCR') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test encre chine(LCR)' )  ,    (select id from type_of_sample where local_abbrev = 'LCR')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Hématies' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Hématies' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasites - mycoses superficielles' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasites - mycoses superficielles' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Parasites - mycoses profondes' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Parasites - mycoses profondes' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sur milieu SAC  - mycoses profondes' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sur milieu SAC  - mycoses profondes' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sur milieu SAC  - mycose superficielle' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sur milieu SAC  - mycose superficielle' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Test ag soluble(LCR)' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'LCR') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Test ag soluble(LCR)' )  ,    (select id from type_of_sample where local_abbrev = 'LCR')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Germe isolé antifongigramme' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Germe isolé antifongigramme' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Flucytosine 1 ug' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Flucytosine 1 ug' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Flucytosine 10 ug' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Flucytosine 10 ug' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Amphotéricine B' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Amphotéricine B' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Fluconazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Fluconazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Miconazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Miconazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Econazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Econazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Kétéconazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Kétéconazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Nystatine' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Nystatine' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Voriconazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Voriconazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Itraconazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Itraconazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Clotrimazole' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Clotrimazole' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Germe isolé mycose profonde' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Germe isolé mycose profonde' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Grains' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Grains' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Spores' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Spores' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Attaque pilaire' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Attaque pilaire' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sur milieu SC' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sur milieu SC' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Sur milieu taplin' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Sur milieu taplin' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levure mycose superficielle' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levure mycose superficielle' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Levure mycose profonde' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Levure mycose profonde' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from test where description = 'Germe isolé mycose superficielle' )  and sample_type_id =  (select id from type_of_sample where local_abbrev = 'Variable') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Germe isolé mycose superficielle' )  ,    (select id from type_of_sample where local_abbrev = 'Variable')  );

INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Goutte épaisse(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Goutte épaisse(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11111', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasitémie(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasitémie(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11111', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Mince(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Mince(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11111', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Autres espèces(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Autres espèces(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11111', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Scotch test anal(Scotch Test Anal)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Scotch test anal(Scotch Test Anal)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11207', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect des selles(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect des selles(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eléments fongiques(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eléments fongiques(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Concentration des selles(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Concentration des selles(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Baermann(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Baermann(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Ziehl neelsen(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Ziehl neelsen(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coproculture(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coproculture(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '13013', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'IgM(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'IgM(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11056', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Index IgM(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Index IgM(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11056', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'IgG(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'IgG(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11056', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre IgG(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre IgG(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11056', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HAI amibiase(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HAI amibiase(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11054', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11054', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11054', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Résultat HAI bilharziose(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 80', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Résultat HAI bilharziose(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11055', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 80', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11055', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 80', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11055', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect des urines(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect des urines(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11522', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites (examen direct)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites (examen direct)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11522', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eléments fongiques (examen direct)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eléments fongiques (examen direct)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11522', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites (après centrifugation)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites (après centrifugation)(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11522', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de microfilaires' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de microfilaires' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11105', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de leishmanies' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de leishmanies' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11107', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Scotch test cutane(Biopsie)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Scotch test cutane(Biopsie)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11207', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments - mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments - mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Leucocytes' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Leucocytes' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test encre chine(LCR)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test encre chine(LCR)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11401', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hématies' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hématies' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites - mycoses superficielles' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Parasites - mycoses superficielles' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu Sc' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu Sc' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SAC  - mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SAC  - mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SAC  - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SAC  - mycoses profondes' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test ag soluble(LCR)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test ag soluble(LCR)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11401', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé antifongigramme' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé antifongigramme' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Flucytosine 1 ug' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Flucytosine 1 ug' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Flucytosine 10 ug' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Flucytosine 10 ug' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amphotéricine B' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amphotéricine B' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fluconazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fluconazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Miconazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Miconazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Econazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Econazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Kétéconazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Kétéconazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Nystatine' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Nystatine' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Voriconazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Voriconazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Itraconazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Itraconazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Clotrimazole' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Clotrimazole' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11304', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé mycose profonde' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé mycose profonde' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Grains' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Grains' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Spores' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Spores' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Attaque pilaire' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Attaque pilaire' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SC' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu SC' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu taplin' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sur milieu taplin' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levure mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levure mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levure mycose profonde' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levure mycose profonde' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Germe isolé mycose superficielle' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11303', now() );

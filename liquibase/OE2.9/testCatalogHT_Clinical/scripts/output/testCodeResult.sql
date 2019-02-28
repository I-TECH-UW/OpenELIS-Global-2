INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hemoglobine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hemoglobine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hematocrite(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hematocrite(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VGM(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VGM(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TCMH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TCMH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CCMH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CCMH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Plaquettes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Plaquettes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Neutrophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Neutrophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mixtes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mixtes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eosinophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Eosinophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Basophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Basophiles(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Coagulation(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Coagulation(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de saignement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de saignement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Electrophorese de l'hemoglobine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Electrophorese de l'hemoglobine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sickling Test(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sickling Test(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Prothrombine(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Temps de Prothrombine(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'INR(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'INR(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur VIII(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur VIII(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur IX(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur IX(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Heparinemie(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Heparinemie(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Anti-Thrombine III (Dosage)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Anti-Thrombine III (Dosage)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Groupe Sanguin - Rhesus(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Groupe Sanguin - Rhesus(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de comptabilite(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de comptabilite(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de comptabilite(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de comptabilite(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Uree(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Uree(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'creatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'creatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Proteines(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Proteines(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlore(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlore(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'glycemie(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'glycemie(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Albumine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Albumine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'α1 globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'α1 globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'α2 globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'α2 globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'β globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'β globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ϒ globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ϒ globuline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Phosphatase Acide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Phosphatase Acide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Urines/24 heures)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Urines/24 heures)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CPK(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CPK(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amylase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amylase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lipase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lipase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorures(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sodium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sodium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Potassium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Potassium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bicarbonates(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bicarbonates(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Calcium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Calcium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'magnésium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'magnésium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'phosphore(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'phosphore(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lithium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lithium(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fer Serique(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fer Serique(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine totale(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine totale(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine directe(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine directe(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/ AST(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/ AST(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ ALT(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ ALT(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholesterol Total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholesterol Total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglyceride(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglyceride(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lipide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lipide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VLDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VLDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'MBG(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'MBG(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémoglobine glycolisee(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémoglobine glycolisee(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triponine I(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triponine I(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Ph(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Ph(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PaCO2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PaCO2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HCO3(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HCO3(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'O2 Saturation(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'O2 Saturation(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PaO2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PaO2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'BE(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'BE(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie Provoquee Fasting()' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie Provoquee Fasting()' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 1/2 hre(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 1/2 hre(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 1hre(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 1hre(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 2hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 2hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 3hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 3hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 4hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée 4hres(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycémie provoquée(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie Postprandiale(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glycemie Postprandiale(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Azote Urée(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Azote Urée(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urée (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urée (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Créatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Créatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT (ALT)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT (ALT)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT (AST)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT (AST)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HDL-cholestérol(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HDL-cholestérol(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDL-cholesterol (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LDL-cholesterol (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VLDL – cholesterol (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VLDL – cholesterol (calculée)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglycéride(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglycéride(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Acide urique(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Acide urique(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorure(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlorure(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Calcium (Ca++)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Calcium (Ca++)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Protéines totales(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Protéines totales(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Creatinine(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGPT/ALT(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'SGOT/AST(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP Quantitatif(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP Quantitatif(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP Quantitatif(plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP Quantitatif(plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C3 du Complement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C3 du Complement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C4 du complement(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C4 du complement(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C3 du Complement(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C3 du Complement(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C4 du complement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'C4 du complement(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur Rhumatoide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Facteur Rhumatoide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Trichomonas hominis(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Trichomonas hominis(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'KOH(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'KOH(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Catalase(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Catalase(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Oxydase(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Oxydase(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coagulase libre(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coagulase libre(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'DNAse(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'DNAse(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Hydrolyse de l'esculine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Hydrolyse de l'esculine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urée-tryptophane(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urée-tryptophane(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mobilité(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mobilité(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test à la potasse(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test à la potasse(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test à la porphyrine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test à la porphyrine(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ONPG(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ONPG(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Réaction de Voges-Proskauer(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Réaction de Voges-Proskauer(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Camp-test(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Camp-test(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Techniques d'agglutination(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Techniques d'agglutination(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Gram(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Gram(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Ziehl-Neelsen(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Ziehl-Neelsen(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Coloration à l'auramine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Coloration à l'auramine(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Coloration à l'acridine orange(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = $$Coloration à l'acridine orange(Sang)$$ ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Kinyoun(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Kinyoun(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholera Test rapide(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholera Test rapide(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coproculture(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coproculture(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Liquefaction(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Liquefaction(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'pH(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'pH(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fructose(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Fructose(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Volume(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Volume(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Gram(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Coloration de Gram(Liquide Spermatique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Culture Bacterienne(Liquide Biologique)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Culture Bacterienne(Liquide Biologique)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hemoculture(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hemoculture(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Densite(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Densite(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'pH(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'pH(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Proteines(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Proteines(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glucose(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glucose(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cetones(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cetones(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bilirubine(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sang(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sang(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Leucocytes(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Leucocytes(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Acide ascorbique(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Acide ascorbique(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urobilinogene(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Urobilinogene(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Nitrites(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Nitrites(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hematies(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hematies(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'cellules epitheliales(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'cellules epitheliales(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bacteries(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Levures(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'filaments myceliens(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'filaments myceliens(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'spores(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'spores(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'trichomonas(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'trichomonas(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cylindres(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cylindres(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cristaux(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cristaux(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Couleur(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Aspect(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sang Occulte(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Sang Occulte(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bleu de Methylene(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bleu de Methylene(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Examen Microscopique direct(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Examen Microscopique direct(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Examen Microscopique apres concentration(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Examen Microscopique apres concentration(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recheche de microfilaire(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recheche de microfilaire(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CD4 en mm3(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CD4 en mm3(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CD4 en %(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CD4 en %(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH test rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'VIH Elisa(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite B Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hépatite C IgM(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue NS1 Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue NS1 Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue NS1 Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue NS1 Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PPD Qualitaitif(In Vivo)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PPD Qualitaitif(In Vivo)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PPD Quantitatif(In Vivo)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PPD Quantitatif(In Vivo)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Prolactine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Prolactine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'FSH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'FSH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'FSH(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'FSH(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LH(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LH(Plasma heparin�)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Progesterone(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Progesterone(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'T3(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'T3(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'B-HCG(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'B-HCG(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'B-HCG(Urine concentr� du matin)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'B-HCG(Urine concentr� du matin)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Grossesse(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Grossesse(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'T4(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'T4(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TSH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TSH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LCR GRAM(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LCR GRAM(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Malaria Test Rapide(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Test Rapide(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HTLV I et II(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis RPR(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis TPHA(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Helicobacter Pilori(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Herpes Simplex(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Herpes Simplex(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Herpes Simplex(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Herpes Simplex(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlamydia Ab(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlamydia Ab(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlamydia Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Chlamydia Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CRP(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'ASO(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mono Test(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mono Test(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LE Cell(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'LE Cell(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue Ig G(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue Ig G(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue Ig A(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dengue Ig A(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CMV Ig G(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CMV Ig G(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CMV Ig A(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'CMV Ig A(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cryptococcus Antigene dipstick(serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cryptococcus Antigene dipstick(serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Clostridium Difficile Toxin A & B(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Clostridium Difficile Toxin A & B(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Determine VIH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Syphilis Bioline(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PSA(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PSA(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PSA(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'PSA(Sang)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );

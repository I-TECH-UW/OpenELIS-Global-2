INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '12214', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '12213', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glucose(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 10', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Glucose(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11918', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Créatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 20', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Créatinine(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11914', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amylase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 50', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Amylase(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '12201', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 10', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11519', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 15', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol total(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11911', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 15', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11912', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglycérides(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Triglycérides(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11929', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test urinaire de grossesse(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test urinaire de grossesse(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 7', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémotocrite(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Hémotocrite(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Plaquette(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Plaquette(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11116', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 30', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '12802', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B100', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 60', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '11202', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Western blot VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Western blot VIH(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Western blot VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 150', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Western blot VIH(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Bioline(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Genie III(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Murex(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Murex(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Murex(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Murex(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vironostika(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vironostika(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vironostika(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'Vironostika(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'P24 Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'P24 Ag(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'P24 Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES ( (select id from clinlims.test where description = 'P24 Ag(Serum)' ), (select id from clinlims.test_code_type where schema_name = 'analyzeCode'), '', now() );

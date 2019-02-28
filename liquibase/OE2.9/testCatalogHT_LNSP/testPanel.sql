INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'Test Rapide VIH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'Test Rapide VIH(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'Test Rapide VIH(DBS)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Elisa(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'Test Rapide VIH(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Elisa(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Elisa(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Elisa(DBS)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Western Blot(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Western Blot(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie-Virologie') , now(), null,  (select id from test where description = 'VIH Western Blot(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Recherche de virus respiratoire par immunofuorescence directe') , now(), null,  (select id from test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CD4') , now(), null,  (select id from test where description = 'CD4  Compte Abs(Sang Total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CD4') , now(), null,  (select id from test where description = 'CD4 Compte en %(Sang Total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Compte des Globules Blancs(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Compte des Globules Rouges(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Hemoglobine(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Hematocrite(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'VGM(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'TCMH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'CCMH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Plaquettes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Neutrophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Lymphocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Auto') , now(), null,  (select id from test where description = 'Mixtes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide nalidixique 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Acide fusidique 10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amikacine 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline 10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Azithromicine 15 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Aztreinam 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefalotine 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefotaxime 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftazidime 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftriaxone 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Cefixime10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ceftizoxime 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Chloramfenicol 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ciprofloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Colistine 50 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Enoxacine 50 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Erythromycine 15 UI(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Fosfomycine 50 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Gentamicine 15 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lincomycine 15 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Levofloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Lomefloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Mecillinam 10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Meropeneme 10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxalactam(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxifloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxifloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Moxifloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Nitrofuranes 300 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Norfloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 1 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Oxacilline 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ofloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pefloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Penicilline G 6 μg (10 UI)(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Piperacilline 75 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Pristinamycine 15 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Rifampicine 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sparfloxacine 5 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Teiclopanine 30 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tetracycline 30 UI(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Ticarcilline 75 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Eau de riviere)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antibiogramme') , now(), null,  (select id from test where description = 'Tobramycine 10 μg(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Miscellaneous  Bacteriologie') , now(), null,  (select id from test where description = 'Echantillon(Free text)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Miscellaneous  Bacteriologie') , now(), null,  (select id from test where description = 'Colaration de Gram(Free text)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Miscellaneous  Bacteriologie') , now(), null,  (select id from test where description = 'Culture(Free text)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Couleur(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Consistance(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Sang Occulte(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Bacteries(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Levures simples(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Levures bourgeonantes(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Examen Microscopique direct(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Examen Microscopique après concentration(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria') , now(), null,  (select id from test where description = 'Malaria(Sang)' and is_active = 'Y' ) ); 

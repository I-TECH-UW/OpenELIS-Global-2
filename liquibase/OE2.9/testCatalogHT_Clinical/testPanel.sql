INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Compte des Globules Blancs(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Compte des Globules Rouges(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Hemoglobine(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Hematocrite(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'VGM(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'TCMH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'CCMH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Plaquettes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Neutrophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Lymphocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Compte des Globules Blancs(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Compte des Globules Rouges(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Hemoglobine(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Hematocrite(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Plaquettes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Auto') , now(), null,  (select id from test where description = 'Mixtes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Neutrophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Lymphocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Monocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Eosinophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme - Manual') , now(), null,  (select id from test where description = 'Basophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'TS/TC') , now(), null,  (select id from test where description = 'Temps de Coagulation en tube(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'TS/TC') , now(), null,  (select id from test where description = 'Temps de Coagulation(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'TS/TC') , now(), null,  (select id from test where description = 'Temps de saignement(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Electrophorese Hb') , now(), null,  (select id from test where description = $$Electrophorese de l'hemoglobine(Sang)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Sickling test') , now(), null,  (select id from test where description = 'Sickling Test(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Coagulation') , now(), null,  (select id from test where description = 'Temps de cephaline Activé(TCA)(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Coagulation') , now(), null,  (select id from test where description = 'Temps de Prothrombine(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Coagulation') , now(), null,  (select id from test where description = 'INR(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Groupe Sanguin') , now(), null,  (select id from test where description = 'Groupe Sanguin - ABO(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Groupe Sanguin') , now(), null,  (select id from test where description = 'Groupe Sanguin - Rhesus(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie Sanguine') , now(), null,  (select id from test where description = $$Azote de l'Uree(Serum)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie Sanguine') , now(), null,  (select id from test where description = 'Uree(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie Sanguine') , now(), null,  (select id from test where description = 'creatinine(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie Sanguine') , now(), null,  (select id from test where description = 'Glycemie(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie LCR') , now(), null,  (select id from test where description = 'Glycemie(LCR/CSF)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie LCR') , now(), null,  (select id from test where description = 'Proteines(LCR/CSF)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Chimie LCR') , now(), null,  (select id from test where description = 'Chlore(LCR/CSF)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Chlorures(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Sodium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Potassium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Bicarbonates(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Calcium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'magnésium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'phosphore(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Ionogramme') , now(), null,  (select id from test where description = 'Lithium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Bilan Hepatique') , now(), null,  (select id from test where description = 'Bilirubine totale(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Bilan Hepatique') , now(), null,  (select id from test where description = 'Bilirubine directe(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Bilan Hepatique') , now(), null,  (select id from test where description = 'Bilirubine indirecte(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Bilan Hepatique') , now(), null,  (select id from test where description = 'SGOT/ AST(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Bilan Hepatique') , now(), null,  (select id from test where description = 'SGPT/ ALT(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'Cholesterol Total(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'Triglyceride(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'Lipide(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'HDL(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'LDL(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Profil Lipidique') , now(), null,  (select id from test where description = 'VLDL(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'Ph(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'PaCO2(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'HCO3(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'O2 Saturation(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'PaO2(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Arteriel') , now(), null,  (select id from test where description = 'BE(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'Ph(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'PaCO2(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'HCO3(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'O2 Saturation(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'PaO2(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Gaz du Sang Veineux') , now(), null,  (select id from test where description = 'BE(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Glycémie(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycemie Provoquee Fasting(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée 1/2 hre(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée 1hre(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée 2hres(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée 3hres(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée 4hres(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Provoquee') , now(), null,  (select id from test where description = 'Glycémie provoquée(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Postprandiale') , now(), null,  (select id from test where description = 'Glycemie Postprandiale(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Glycemie Postprandiale') , now(), null,  (select id from test where description = 'Glycemie Postprandiale(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Azote Urée(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Urée (calculée)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Créatinine(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Bilirubine totale(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'SGPT (ALT)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'SGOT (AST)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Cholestérol total(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'HDL-cholestérol(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'LDL-cholesterol (calculée)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'VLDL – cholesterol (calculée)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Triglycéride(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Acide urique(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Sodium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Potassium(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Chlorure(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Calcium (Ca++)(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Protéines totales(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Vitros') , now(), null,  (select id from test where description = 'Albumine(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'Creatinine(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'Creatinine(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'Creatinine(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGPT/ALT(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGPT/ALT(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGPT/ALT(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGOT/AST(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGOT/AST(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Biochimie Reflotron') , now(), null,  (select id from test where description = 'SGOT/AST(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Bacteries(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Levures Simples(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Levures Bourgeonantes(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Trichomonas vaginalis(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Globules Blancs(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Globules Rouges(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Filaments Myceliens(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Cellules Epitheliales(Secretion vaginale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Bacteries(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Levures Simples(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Levures Bourgeonantes(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Trichomonas hominis(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Globules Blancs(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Globules Rouges(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Filaments Myceliens(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Goutte Pendante') , now(), null,  (select id from test where description = 'Cellules Epitheliales(Secretion Urethrale)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Catalase(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Oxydase(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Coagulase libre(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'DNAse(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = $$Hydrolyse de l'esculine(Sang)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Urée-tryptophane(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Mobilité(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Test à la potasse(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Test à la porphyrine(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'ONPG(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Réaction de Voges-Proskauer(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Camp-test(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = $$Techniques d'agglutination(Sang)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Coloration de Gram(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Coloration de Ziehl-Neelsen(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = $$Coloration à l'auramine(Sang)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = $$Coloration à l'acridine orange(Sang)$$ and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Coloration de Kinyoun(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Cholera') , now(), null,  (select id from test where description = 'Cholera Test rapide(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Cholera') , now(), null,  (select id from test where description = 'Coproculture(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Couleur(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Liquefaction(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'pH(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Fructose(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Volume(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Compte de spermes(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Formes normales(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Formes anormales(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Motilite STAT(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Motilite 1 heure(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Motilite 3 heures(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Spermogramme') , now(), null,  (select id from test where description = 'Coloration de Gram(Liquide Spermatique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Culture Bacterienne(Liquide Biologique)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Bacterienne') , now(), null,  (select id from test where description = 'Hemoculture(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Couleur(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Aspect(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Densite(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'pH(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Proteines(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Glucose(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Cetones(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Bilirubine(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Sang(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Leucocytes(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Acide ascorbique(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Urobilinogene(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Macro') , now(), null,  (select id from test where description = 'Nitrites(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Hematies(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Leucocytes(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'cellules epitheliales(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Bacteries(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Levures(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'filaments myceliens(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'spores(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'trichomonas(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Cylindres(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Urine Micro') , now(), null,  (select id from test where description = 'Cristaux(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Couleur(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Aspect(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Selles Routine') , now(), null,  (select id from test where description = 'Sang Occulte(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Zielh Modifie') , now(), null,  (select id from test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria') , now(), null,  (select id from test where description = 'Malaria(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria') , now(), null,  (select id from test where description = 'Malaria Test Rapide(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CD4') , now(), null,  (select id from test where description = 'CD4 en mm3(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CD4') , now(), null,  (select id from test where description = 'CD4 en %(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Zielh Nielsen') , now(), null,  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Zielh Nielsen') , now(), null,  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Zielh') , now(), null,  (select id from test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Fluorescent/Auramine') , now(), null,  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Fluorescent/Auramine') , now(), null,  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Fluorescent/Auramine') , now(), null,  (select id from test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Culture Mycobacteriologique') , now(), null,  (select id from test where description = 'Culture de M. tuberculosis(Expectoration)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'PPD') , now(), null,  (select id from test where description = 'PPD Qualitaitif(In Vivo)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'PPD') , now(), null,  (select id from test where description = 'PPD Quantitatif(In Vivo)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria Rapid') , now(), null,  (select id from test where description = 'Malaria Test Rapide(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria Rapid') , now(), null,  (select id from test where description = 'Malaria Test Rapide(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Malaria Rapid') , now(), null,  (select id from test where description = 'Malaria Test Rapide(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Determine VIH(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Determine VIH(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Determine VIH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Syphilis Bioline(Serum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Syphilis Bioline(Plasma)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'CDV') , now(), null,  (select id from test where description = 'Syphilis Bioline(Sang)' and is_active = 'Y' ) ); 

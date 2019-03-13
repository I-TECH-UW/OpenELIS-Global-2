INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hemoglobine(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hematocrite(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VGM(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'TCMH(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CCMH(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Plaquettes(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Neutrophiles(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lymphocytes(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Mixtes(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Monocytes(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Eosinophiles(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Basophiles(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Temps de Coagulation(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Temps de saignement(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Electrophorese de l'hemoglobine(Sang)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sickling Test(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Temps de Prothrombine(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'INR(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Facteur VIII(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Facteur IX(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Heparinemie(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Anti-Thrombine III (Dosage)(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Groupe Sanguin - Rhesus(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de comptabilite(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de comptabilite(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coombs Test Direct(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coombs Test Indirect(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Uree(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'creatinine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycemie(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycemie(LCR/CSF)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Proteines(LCR/CSF)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlore(LCR/CSF)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'glycemie(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Albumine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'α1 globuline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'α2 globuline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'β globuline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'ϒ globuline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Phosphatase Acide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlorures(Plasma heparin�)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlorures(Urines/24 heures)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CPK(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amylase(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lipase(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlorures(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sodium(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Potassium(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bicarbonates(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Calcium(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'magnésium(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'phosphore(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lithium(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fer Serique(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bilirubine totale(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bilirubine directe(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bilirubine indirecte(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/ AST(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ ALT(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cholesterol Total(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triglyceride(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lipide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HDL(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LDL(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VLDL(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'MBG(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hémoglobine glycolisee(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LDH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triponine I(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ph(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PaCO2(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HCO3(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'O2 Saturation(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PaO2(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'BE(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycemie Provoquee Fasting()'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée 1/2 hre(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée 1hre(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée 2hres(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée 3hres(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée 4hres(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycémie provoquée(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glycemie Postprandiale(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Azote Urée(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Urée (calculée)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Créatinine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT (ALT)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT (AST)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cholestérol total(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HDL-cholestérol(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LDL-cholesterol (calculée)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VLDL – cholesterol (calculée)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triglycéride(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Acide urique(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlorure(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Calcium (Ca++)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Protéines totales(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Creatinine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Creatinine(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Creatinine(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ALT(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ALT(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ALT(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/AST(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/AST(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/AST(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CRP Quantitatif(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CRP Quantitatif(plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'C3 du Complement(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'C4 du complement(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'C3 du Complement(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'C4 du complement(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Facteur Rhumatoide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trichomonas hominis(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'KOH(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Catalase(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Oxydase(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coagulase libre(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'DNAse(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Hydrolyse de l'esculine(Sang)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Urée-tryptophane(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Mobilité(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test à la potasse(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test à la porphyrine(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'ONPG(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Réaction de Voges-Proskauer(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Camp-test(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Techniques d'agglutination(Sang)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coloration de Gram(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coloration de Ziehl-Neelsen(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Coloration à l'auramine(Sang)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = $$Coloration à l'acridine orange(Sang)$$),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coloration de Kinyoun(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cholera Test rapide(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coproculture(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Liquefaction(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'pH(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fructose(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Volume(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coloration de Gram(Liquide Spermatique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Culture Bacterienne(Liquide Biologique)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hemoculture(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Couleur(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Densite(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'pH(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Proteines(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cetones(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bilirubine(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sang(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Leucocytes(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Acide ascorbique(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Urobilinogene(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Nitrites(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hematies(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'cellules epitheliales(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bacteries(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levures(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'filaments myceliens(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'spores(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'trichomonas(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cylindres(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cristaux(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Couleur(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sang Occulte(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bleu de Methylene(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Examen Microscopique direct(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Examen Microscopique apres concentration(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recheche de microfilaire(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malaria(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CD4 en mm3(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CD4 en %(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH test rapide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH test rapide(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH test rapide(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH Elisa(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH Elisa(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'VIH Elisa(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite B Ag(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite B Ag(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite B Ag(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite C IgM(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite C IgM(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hépatite C IgM(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dengue NS1 Ag(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dengue NS1 Ag(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dengue(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PPD Qualitaitif(In Vivo)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PPD Quantitatif(In VIvo)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Prolactine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'FSH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'FSH(Plasma heparin�)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LH(Plasma heparin�)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Progesterone(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'T3(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'B-HCG(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'B-HCG(Urine concentr� du matin)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de Grossesse(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'T4(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'TSH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LCR GRAM(LCR/CSF)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malaria Test Rapide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Test Rapide(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Test Rapide(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malaria Test Rapide(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Test Rapide(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HTLV I et II(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HTLV I et II(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HTLV I et II(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis RPR(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis RPR(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis TPHA(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis TPHA(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Helicobacter Pilori(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Helicobacter Pilori(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Helicobacter Pilori(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Herpes Simplex(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Herpes Simplex(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlamydia Ab(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chlamydia Ag(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CRP(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CRP(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CRP(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'ASO(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'ASO(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'ASO(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Mono Test(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LE Cell(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dengue Ig G(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dengue Ig A(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CMV Ig G(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CMV Ig A(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cryptococcus Antigene dipstick(serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Clostridium Difficile Toxin A & B(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Determine VIH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Determine VIH(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Determine VIH(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Bioline(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Bioline(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Syphilis Bioline(Sang)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PSA(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = ''),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PSA(Sang)'),'DISPLAY');

INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Plasma)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Créatinine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Créatinine(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases GPT(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases GPT(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases G0T(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Transaminases G0T(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amylase(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Albumine recherche miction(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cholestérol total(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Cholestérol HDL(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triglycérides(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hémotocrite(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hémotocrite(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Plaquette(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Plaquette(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)'),'DISPLAY');

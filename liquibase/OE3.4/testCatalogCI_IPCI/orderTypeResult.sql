INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Goutte épaisse(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Goutte épaisse(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasitémie(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasitémie(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Frottis Mince(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Frottis Mince(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Autres espèces(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Autres espèces(Sang total)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Scotch test anal(Scotch Test Anal)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Scotch test anal(Scotch Test Anal)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect des selles(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect des selles(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Eléments fongiques(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Eléments fongiques(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Concentration des selles(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Concentration des selles(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Baermann(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Baermann(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ziehl neelsen(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ziehl neelsen(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coproculture(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Coproculture(Selles)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'IgM(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'IgM(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Index IgM(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Index IgM(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'IgG(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'IgG(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre IgG(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre IgG(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HAI amibiase(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HAI amibiase(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Résultat HAI bilharziose(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Résultat HAI bilharziose(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect des urines(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Aspect des urines(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites (examen direct)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites (examen direct)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Eléments fongiques (examen direct)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Eléments fongiques (examen direct)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites (après centrifugation)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites (après centrifugation)(Urines)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de microfilaires'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de microfilaires'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de leishmanies'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Recherche de leishmanies'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Scotch test cutane(Biopsie)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Scotch test cutane(Biopsie)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments - mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Filaments - mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Leucocytes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Leucocytes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test encre chine(LCR)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test encre chine(LCR)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hématies'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Hématies'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites - mycoses superficielles'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Parasites - mycoses superficielles'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu Sc'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu Sc'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SAC  - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SAC  - mycoses profondes'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SAC  - mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SAC  - mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test ag soluble(LCR)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Test ag soluble(LCR)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé antifongigramme'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé antifongigramme'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Flucytosine 1 ug'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Flucytosine 1 ug'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Flucytosine 10 ug'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Flucytosine 10 ug'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amphotéricine B'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amphotéricine B'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fluconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fluconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Miconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Miconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Econazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Econazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Kétéconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Kétéconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Nystatine'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Nystatine'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Voriconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Voriconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Itraconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Itraconazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Clotrimazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Clotrimazole'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé mycose profonde'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé mycose profonde'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Grains'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Grains'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Spores'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Spores'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Attaque pilaire'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Attaque pilaire'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SC'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu SC'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu taplin'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sur milieu taplin'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levure mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levure mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levure mycose profonde'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Levure mycose profonde'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé mycose superficielle'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Germe isolé mycose superficielle'),'DISPLAY');

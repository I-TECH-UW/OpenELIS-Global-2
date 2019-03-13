INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Serologie-Virologie' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'Serologie-Virologie' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'DBS' ) , (select id from clinlims.panel where name = 'Serologie-Virologie' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Serologie-Virologie' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Nasal' ) , (select id from clinlims.panel where name = 'Recherche de virus respiratoire par immunofuorescence directe' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Pharynge' ) , (select id from clinlims.panel where name = 'Recherche de virus respiratoire par immunofuorescence directe' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Naso-Pharynge' ) , (select id from clinlims.panel where name = 'Recherche de virus respiratoire par immunofuorescence directe' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang Total' ) , (select id from clinlims.panel where name = 'CD4' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Hemogramme-Auto' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Naso-Pharynge' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Pharinge' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Nasal' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Eau de riviere' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Antibiogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Selles Routine' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Malaria' ) );

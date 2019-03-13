INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Sang total' ) , (select id from clinlims.panel where name = 'GE / FS' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Selles' ) , (select id from clinlims.panel where name = 'KOP' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Sérum' ) , (select id from clinlims.panel where name = 'Serologie Toxoplasmique' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Sérum' ) , (select id from clinlims.panel where name = 'Serologie Amibienne' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Sérum' ) , (select id from clinlims.panel where name = 'Serologie Bilharzienne' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Urines' ) , (select id from clinlims.panel where name = $$Examen d'urines$$ ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Variable' ) , (select id from clinlims.panel where name = 'Mycoses Profondes' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'LCR' ) , (select id from clinlims.panel where name = 'Mycoses Profondes' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Variable' ) , (select id from clinlims.panel where name = 'Antifongigramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where local_abbrev = 'Variable' ) , (select id from clinlims.panel where name = 'Mycose Superficielle' ) );

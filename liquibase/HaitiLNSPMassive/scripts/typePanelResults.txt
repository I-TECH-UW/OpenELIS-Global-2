INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang Total' ) , (select id from clinlims.panel where name = 'CD4' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Antibiogramme Cholera' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Antibiogramme Shiguelle' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Antibiogramme Salmonelle' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Selles macro' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Selles micro' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Malaria' ) );

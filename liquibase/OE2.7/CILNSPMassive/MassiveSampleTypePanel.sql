INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang total' ) , (select id from clinlims.panel where name = 'CD4' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'LCR' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang total' ) , (select id from clinlims.panel where name = 'NFS' ) );

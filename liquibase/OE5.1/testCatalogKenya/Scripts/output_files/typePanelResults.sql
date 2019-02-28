INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Urine' ) , (select id from clinlims.panel where name = 'Urine Microscopy' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Urine' ) , (select id from clinlims.panel where name = 'Urine Test Strip' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Full blood count' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Coagulation Test' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Lymphocyte Subsets' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Liver function tests' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Renal function tests' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Lipid profile' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'CSF' ) , (select id from clinlims.panel where name = 'CSF chemistry' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Blood' ) , (select id from clinlims.panel where name = 'Thyroid Function Tests' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Lymph nodes' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Respiratory tract lavage' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Pleural fluid' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ascitic fluid' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'CSF' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Liver tissue' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Kidney tissue' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Vaginal swab' ) , (select id from clinlims.panel where name = 'Cytology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Cervix' ) , (select id from clinlims.panel where name = 'Histology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Prostrate' ) , (select id from clinlims.panel where name = 'Histology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Breast' ) , (select id from clinlims.panel where name = 'Histology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ovarian cyst' ) , (select id from clinlims.panel where name = 'Histology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Fibroids' ) , (select id from clinlims.panel where name = 'Histology' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Lymph nodes' ) , (select id from clinlims.panel where name = 'Histology' ) );

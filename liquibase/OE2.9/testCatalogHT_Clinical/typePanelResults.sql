INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Hemogramme - Auto' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Hemogramme - Manual' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'TS/TC' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Electrophorese Hb' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Sickling test' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'Coagulation' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Groupe Sanguin' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Chimie Sanguine' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'LCR/CSF' ) , (select id from clinlims.panel where name = 'Chimie LCR' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Ionogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Bilan Hepatique' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Profil Lipidique' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Gaz du Sang Arteriel' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Gaz du Sang Veineux' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Biochimie Vitros' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Glycemie Provoquee' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Glycemie Postprandiale' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Biochimie Reflotron' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Biochimie Reflotron' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'Biochimie Reflotron' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Secretion vaginale' ) , (select id from clinlims.panel where name = 'Goutte Pendante' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Secretion Urethrale' ) , (select id from clinlims.panel where name = 'Goutte Pendante' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Culture Bacterienne' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Cholera' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Liquide Spermatique' ) , (select id from clinlims.panel where name = 'Spermogramme' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Liquide Biologique' ) , (select id from clinlims.panel where name = 'Culture Bacterienne' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Urines' ) , (select id from clinlims.panel where name = 'Urine Macro' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Urines' ) , (select id from clinlims.panel where name = 'Urine Micro' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Selles Routine' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Selles' ) , (select id from clinlims.panel where name = 'Zielh Modifie' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Malaria' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'CD4' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sputum' ) , (select id from clinlims.panel where name = 'Zielh Nielsen' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sputum' ) , (select id from clinlims.panel where name = 'Zielh' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sputum' ) , (select id from clinlims.panel where name = 'Fluorescent/Auramine' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Expectoration' ) , (select id from clinlims.panel where name = 'Culture Mycobacteriologique' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'In Vivo' ) , (select id from clinlims.panel where name = 'PPD' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'Malaria Rapid' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Malaria Rapid' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'Malaria Rapid' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Serum' ) , (select id from clinlims.panel where name = 'CDV' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'CDV' ) );
INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'CDV' ) );

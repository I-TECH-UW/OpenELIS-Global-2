INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Microscopy' , 'Microscopy', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Microscopy' ,100, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Serology-Immunology' , 'Serology-Immunology', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Serology-Immunology' ,400, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Hematology' , 'Hematology', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Hematology' ,610, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Biochemistry' , 'Biochemistry', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Biochemistry' ,990, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Bacteriology' , 'Bacteriology', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Bacteriology' ,1330, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Molecular Biology' , 'Molecular Biology', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Molecular' ,1690, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Blood Bank' , 'Blood Bank', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Blood' ,1760, 'Y');
INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES
	(nextval('clinlims.test_section_seq'), 'Histology / Cytology' , 'Histology / Cytology', (select id from clinlims.organization where name = 'Kisumu District Hospital'), 'N', now() ,'test.section.Histology' ,1820, 'Y');

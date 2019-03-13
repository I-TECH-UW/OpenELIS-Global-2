INSERT INTO type_of_sample( id, description, domain, lastupdated, local_abbrev, display_key, is_active )
	VALUES ( nextval( 'type_of_sample_seq' ) , 'Liquide Biologique','H', now() , 'Liquide Bi', 'sample.type.Liquide', 'Y');
INSERT INTO type_of_sample( id, description, domain, lastupdated, local_abbrev, display_key, is_active )
	VALUES ( nextval( 'type_of_sample_seq' ) , 'Sputum','H', now() , 'Sputum', 'sample.type.Sputum', 'Y');

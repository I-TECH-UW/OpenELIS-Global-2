INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id, display_key ) 
	VALUES ( nextval( 'clinlims.dictionary_seq' ) , 'Y' , 'Positive' , now(), ( select id from clinlims.dictionary_category where description = 'Kenya Lab'  ), 'dictionary.result.Positive' );
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id, display_key ) 
	VALUES ( nextval( 'clinlims.dictionary_seq' ) , 'Y' , 'Negative' , now(), ( select id from clinlims.dictionary_category where description = 'Kenya Lab'  ), 'dictionary.result.Negative' );
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id, display_key ) 
	VALUES ( nextval( 'clinlims.dictionary_seq' ) , 'Y' , 'Unspecified' , now(), ( select id from clinlims.dictionary_category where description = 'Kenya Lab'  ), 'dictionary.result.Unspecified' );

INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif +' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif ++' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif +++' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Négatif' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Indéterminé' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH1' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'PositifVIH2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH1 et VIH2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif HIV1' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));

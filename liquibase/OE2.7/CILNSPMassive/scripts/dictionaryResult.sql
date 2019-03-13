INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH 1' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH 2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif VIH1 et 2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Indetermine' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));

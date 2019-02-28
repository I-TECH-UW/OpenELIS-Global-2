INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Plasmodium Falciparum' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Plasmodium Malariae' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Plasmodium Ovalae' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Plasmodium Vivax' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Glaireuse' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Glairo-Sanguinolante' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Levures' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence De Parasites' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence De Levures' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Douteux' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Claire' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Limpide' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Hémorragique' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Rares Oeufs De Schistosoma Haematobium' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Nombreux Oeufs De Schistosoma Haematobium' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Formes Végétatives De Trichomonas Vaginalis' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Filaments' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence Filaments Mycéliens' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence De Levures' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence De Levures Du Genre Cryptocoque' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence De Colonies' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence De Colonies' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Candida Albicans' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Candida Tropicalis' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Candida Glabrata' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Candida Krusei' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Sensible' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Intermédiaire' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Résistant' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Si Sensible Préciser La Concentration Minimale Inhibitrice (Microgramme/Ml)' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence De Champignons' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Nom Du Champignon' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence De Spores' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Présence De Zygospores' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence De Grain' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , $$Présence De Grain D'Origine Fongique$$ , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , $$Presence De Grain D'Origine Actinomycosique$$ , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Ectothrix' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Endothrix' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Endo-Ectothrix' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absences De Colonies' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));

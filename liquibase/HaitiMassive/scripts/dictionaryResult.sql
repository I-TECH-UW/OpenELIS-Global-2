INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'B' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'RH Pos' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'RH Neg' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Compatible' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Incompatible' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif 1+' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif 2+' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Positif 3+' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Non Reactif' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Blanc jaunatre' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'jaune paille' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'jaune citrin' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'ambre' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'orange' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'jaune fonce' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'rouge' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'autre' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Jaune citrin' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Ambre' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Orange' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Jaune Fonce' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Icterique' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'normal' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'trace' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'En amas' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '0 à 1/ champs' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '2 à 5 / Champs' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '5 à 10/ Champs' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '0 à 1/ champs' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Hyalins' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Graisseux' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Epitheliales' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Leucocytaires' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , ' Oxalate de Calcium' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Acide Urique' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Cystine' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Phosphate de Calcium' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Triple phosphate' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Urate d'Ammonium ' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Noiratre' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Sang/Whole Blooduinolant' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Maron' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Solide' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'semi liquide' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'pateuse' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'POSITIF' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'NEGATIF' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'P. Falciparum' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'P. Vivax' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'P. Malariae' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'P.Ovale' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'INDETERMINE' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Detecte' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Non Detecte' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Indetermine' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Rares' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'POS' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'NEG' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Pres BK' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Abs BK' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'etc' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Pres ence des lymphocytes' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence des lymphocytes' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Pres eosinophiles' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Pres ence des cellules atypiques' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Absence des cellules atypiques' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , '' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/2' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/4' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/8' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/16' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/32' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/64' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'POS  > 200 unites/ml' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'NEG  < 200 unites/ml' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'POS  > 200 U ASLO/ml' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'NEG  < 200 U ASLO/ml' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/20' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/40' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/80' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/160' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/320' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));
INSERT INTO clinlims.dictionary ( id, is_active, dict_entry, lastupdated, dictionary_category_id ) 
	VALUES ( nextval( 'dictionary_seq' ) , 'Y' , 'Reactif 1/640' , now(), ( select id from clinlims.dictionary_category where description = 'Haiti Lab' ));

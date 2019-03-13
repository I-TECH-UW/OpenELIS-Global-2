
--Syphilis TPHA(Sérum)
update clinlims.test_result set tst_rslt_type = 'M', value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Non-Reactif' ) as varchar) 
where test_id = (select id from clinlims.test where description = 'Syphilis TPHA(Sérum)') and value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ) as varchar);

update clinlims.test_result set tst_rslt_type = 'M', value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Reactif' ) as varchar)
where test_id = (select id from clinlims.test where description = 'Syphilis TPHA(Sérum)') and value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Positif' ) as varchar);

--insert the rest of the values
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
         'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2u' )  , now() , 1511),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4u' )  , now() , 1512),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='16u' )  , now() , 1513),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='32u' )  , now() , 1514),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='64u' )  , now() , 1515),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 1516),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 1517),
  ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)' ) ,
    'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 572u' )  , now() , 1518);

update clinlims.result_limits set normal_dictionary_id = (select max(id) from clinlims.dictionary where dict_entry = 'Non-Reactif' )
where test_id =  ( select id from clinlims.test where description = 'Syphilis TPHA(Sérum)');


--

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sérum)' ) ,
         'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2420);

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Plasma)' ) ,
         'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2420);

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang Total)' ) ,
         'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2420);
         
--

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name = 'Copies/ml') where name = 'VIH-1 Charge Virale';

update clinlims.test set uom_id = null, lastupdated = now() 
where name = 'VIH Western Blot';

delete from clinlims.unit_of_measure where name = 'Bandes présentes';

UPDATE clinlims.dictionary set dict_entry = 'Rare (1-19 BAAR/40ch)' where dict_entry like 'Rare (1-9 BAAR/40ch)';

insert into clinlims.dictionary (id, is_active, dict_entry, lastupdated, dictionary_category_id, sort_order)
	values (nextval('dictionary_seq'), 'Y', 'Influenza A Positif', now(), 38, 106750);

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche du Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) ,
         'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A Positif' )  , now() , 1695);
         
update clinlims.dictionary set dict_entry = 'Positif pour Clostridium difficile toxin A/B' where dict_entry = 'Positif pour Clostridim difficile toxin A/B';         

update clinlims.test_result set value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Indetermine' ) as varchar) 
where test_id = (select id from clinlims.test where description = 'Salmonelle typhi IgM 09 Test Rapide (Sérum)') and value = cast( (select max(id) from clinlims.dictionary where dict_entry = 'Borderline' ) as varchar);


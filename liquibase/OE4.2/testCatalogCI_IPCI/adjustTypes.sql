--remove reflexes for Test encre chine
delete from clinlims.test_reflex where test_id = ( select id from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' );

--change type for Test encre chine, Test ag soluble to varies
update clinlims.sampletype_test set sample_type_id=(select id from type_of_sample where local_abbrev = 'Variable')
where test_id= (select id from clinlims.test where guid='957e1fad-3a0e-4408-b686-124da04c395c');

update clinlims.sampletype_test set sample_type_id=(select id from type_of_sample where local_abbrev = 'Variable')
where test_id= (select id from clinlims.test where guid='e7d2d95a-2692-42dd-8e96-db527a1ef3d1');

--make orderable
update clinlims.test set orderable = true where guid='e7d2d95a-2692-42dd-8e96-db527a1ef3d1';

--add to panel
INSERT INTO clinlims.panel_item(  id, lastupdated, panel_id, test_id)
VALUES ( nextval( 'panel_item_seq' ), now(),
         (select id from panel where name = 'Mycoses Profondes'),
         (select id from clinlims.test where guid='e7d2d95a-2692-42dd-8e96-db527a1ef3d1'));

--add variable sample types
INSERT INTO clinlims.test_dictionary(id, test_id, dictionary_category_id, context, qualifiable_entry_id )
VALUES ( nextval( 'test_dictionary_seq' ) , (select id from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' ),
         (select id from clinlims.dictionary_category where name = 'LCR set 2') , 'Variable sample type',
         ( select id from clinlims.dictionary where dict_entry = 'Autres' and dictionary_category_id = (select id from clinlims.dictionary_category where name = 'LCR set 2') )),
  ( nextval( 'test_dictionary_seq' ) , (select id from clinlims.test where guid = 'e7d2d95a-2692-42dd-8e96-db527a1ef3d1' ),
    (select id from clinlims.dictionary_category where name = 'LCR set 2') , 'Variable sample type',
    ( select id from clinlims.dictionary where dict_entry = 'Autres' and dictionary_category_id = (select id from clinlims.dictionary_category where name = 'LCR set 2') ));

-- deactivate LCR
update clinlims.type_of_sample set is_active = 'N' where description = 'LCR';
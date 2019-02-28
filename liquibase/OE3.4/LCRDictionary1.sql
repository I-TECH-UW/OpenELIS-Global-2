insert into clinlims.dictionary(id, is_active, dict_entry, lastupdated, dictionary_category_id ) VALUES
( nextval( 'dictionary_seq'), 'Y', 'LCR', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Cheveux', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Peau', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Biopsie', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Sqasme', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Scotch test cutane', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Ongles', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Selles', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Muqueuse', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Urines', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Autres', now(), (select id from clinlims.dictionary_category where name = 'LCR set 1') );


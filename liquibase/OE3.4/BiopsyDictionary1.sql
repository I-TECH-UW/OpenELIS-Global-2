insert into clinlims.dictionary(id, is_active, dict_entry, lastupdated, dictionary_category_id ) VALUES
( nextval( 'dictionary_seq'), 'Y', 'Biopsie', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Cerosites', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Ongles cheveux', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Pue d''oreille', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Prelevement uretral', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Prelevement vaginal', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') ),
( nextval( 'dictionary_seq'), 'Y', 'Autres', now(), (select id from clinlims.dictionary_category where name = 'Biopsy set 1') );

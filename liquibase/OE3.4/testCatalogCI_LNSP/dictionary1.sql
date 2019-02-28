insert into clinlims.dictionary(id, is_active, dict_entry, lastupdated, dictionary_category_id ) VALUES
( nextval( 'dictionary_seq'), 'Y', '+/- 0.15 g/L', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif + (0.3g/L)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif ++ (1.0 g/L)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif +++ (3.0 g/L)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif ++++ (>= 5.0 g/L)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif  (50 mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif  (100  mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif + (250 mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif ++ (500 mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif +++ (1000 mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') ),
( nextval( 'dictionary_seq'), 'Y', 'Positif ++++ (2000 mg/dL)', now(), (select id from clinlims.dictionary_category where name = 'Test Result') );

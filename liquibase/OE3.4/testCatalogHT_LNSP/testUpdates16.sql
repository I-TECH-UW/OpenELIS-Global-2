

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rougeole IgM(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rougeole IgG(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rougeole IgM(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rougeole IgG(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rubeole IgM(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rubeole IgG(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rubeole IgM(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Rubeole IgG(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Dengue IgM(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Dengue IgG(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Dengue IgM(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Dengue IgG(Plasma)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Polio(Selles)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');

insert  into clinlims.result_limits (id, test_id, test_result_type_id, lastupdated, normal_dictionary_id, always_validate)
values (nextval('result_limits_seq'), (select id from clinlims.test where description = 'Chikungunya Test Rapide(Sérum)'), (select id from clinlims.type_of_test_result where test_result_type = 'D'), now(), (select max(id) from clinlims.dictionary where dict_entry = 'Negatif' ), 'f');
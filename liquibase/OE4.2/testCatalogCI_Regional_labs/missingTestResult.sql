UPDATE clinlims.test_result
SET value = ( select max(id) from clinlims.dictionary where dict_entry = 'Positif +')
WHERE test_id = ( select id from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a') and sort_order = 20 and value is null ;

UPDATE clinlims.test_result
SET value = ( select max(id) from clinlims.dictionary where dict_entry = 'Positif ++')
WHERE test_id = ( select id from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a') and sort_order = 30 and value is null ;

UPDATE clinlims.test_result
SET value = ( select max(id) from clinlims.dictionary where dict_entry = 'Positif +++')
WHERE test_id = ( select id from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a') and sort_order = 40 and value is null ;

UPDATE clinlims.test_result
SET value = ( select max(id) from clinlims.dictionary where dict_entry like 'N%gatif')
WHERE test_id = ( select id from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a') and sort_order = 50 and value is null ;

delete from clinlims.test_result WHERE test_id = ( select id from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a') and sort_order = 60 and value is null ;
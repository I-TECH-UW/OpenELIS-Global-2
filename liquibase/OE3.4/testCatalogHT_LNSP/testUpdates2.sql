--58
UPDATE clinlims.result_limits SET normal_dictionary_id = (SELECT id FROM clinlims.dictionary WHERE dict_entry = 'Non-Reactif'), lastupdated = now()
WHERE test_id = ( select id from clinlims.test where description = 'Syphilis RPR(Serum)');

--79
update clinlims.dictionary set dict_entry='Sensible a l''Isoniazid' where dict_entry='Sensible a l''Isonazid';

--general
update clinlims.test set description=replace(description,'(Serum)','(Sérum)'), lastupdated=now() where description like '%(Serum)';
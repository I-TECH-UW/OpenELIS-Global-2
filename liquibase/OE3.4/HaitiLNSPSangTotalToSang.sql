update clinlims.test set description=replace(description, '(Sang)', '(Sang Total)') where description like '%(Sang)%';
update clinlims.sampletype_test set sample_type_id=(select id from clinlims.type_of_sample where description='Sang Total') where sample_type_id=(select id from clinlims.type_of_sample where description='Sang');
update clinlims.sampletype_panel set sample_type_id=(select id from clinlims.type_of_sample where description='Sang Total') where sample_type_id=(select id from clinlims.type_of_sample where description='Sang');
update clinlims.sample_item set typeosamp_id=(select id from clinlims.type_of_sample where description='Sang Total') where typeosamp_id=(select id from clinlims.type_of_sample where description='Sang');
update clinlims.type_of_sample set is_active='N' where description='Sang';
update clinlims.type_of_sample set sort_order=5 where description='Sang Total';

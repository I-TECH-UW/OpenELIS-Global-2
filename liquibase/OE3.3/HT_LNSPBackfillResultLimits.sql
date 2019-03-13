update clinlims.result set test_result_id = (select tr.id from clinlims.test_result tr where tr.test_id = (select id from clinlims.test where name = 'Monocytes'))
where id = ( select r.id from clinlims.result r
			 join clinlims.analysis a on r.analysis_id = a.id
			 join clinlims.test t on a.test_id = t.id
			 where t.name = 'Monocytes');

update clinlims.result set test_result_id = (select tr.id from clinlims.test_result tr where tr.test_id = (select id from clinlims.test where name = 'Eosinophiles'))
where id = ( select r.id from clinlims.result r
			join clinlims.analysis a on r.analysis_id = a.id
			join clinlims.test t on a.test_id = t.id
			where t.name = 'Eosinophiles');

update clinlims.result set test_result_id = (select tr.id from clinlims.test_result tr where tr.test_id = (select id from clinlims.test where name = 'Basophiles'))
where id = ( select r.id from clinlims.result r
			join clinlims.analysis a on r.analysis_id = a.id
			join clinlims.test t on a.test_id = t.id
			where t.name = 'Basophiles');

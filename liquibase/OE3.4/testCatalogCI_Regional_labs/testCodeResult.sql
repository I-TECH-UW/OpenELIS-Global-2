INSERT INTO test_code( test_id, code_type_id, value, lastupdated) 
	VALUES
  ( (select id from clinlims.test where description = 'Stat-Pak(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() ),
  ( (select id from clinlims.test where description = 'Stat-Pak(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() ),
  ( (select id from clinlims.test where description = 'Stat-Pak(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() ),
  ( (select id from clinlims.test where description = 'Determine(Plasma)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() ),
  ( (select id from clinlims.test where description = 'Determine(Sérum)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() ),
  ( (select id from clinlims.test where description = 'Determine(Sang total)' ), (select id from clinlims.test_code_type where schema_name = 'billingCode'), 'B 65', now() );
